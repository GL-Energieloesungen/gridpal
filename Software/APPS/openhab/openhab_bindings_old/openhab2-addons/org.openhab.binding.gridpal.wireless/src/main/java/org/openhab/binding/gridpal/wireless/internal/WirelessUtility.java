
package org.openhab.binding.gridpal.wireless.internal;





import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openhab.binding.gridpal.wireless.WirelessConstants;
import org.openhab.binding.gridpal.wireless.agent.Agent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.FailedLoginException;
import java.util.Arrays;




/**
 * This class provides interface to manipulate wireless connectivity of the system.
 * @author  Md. Farhabi Helal Ayon
 * @since   0.1.0
 */
public class WirelessUtility
{
    private Agent agent = new Agent();

    private Logger logger = LoggerFactory.getLogger(WirelessUtility.class);


    public WirelessUtility()
    {

    }

    /**
     * Connects to a previously connected connection. In this case, password is not required. Also, properly handles error conditions.
     * @param   ssid ssid of the access point
     */
    public void Connect(String ssid) throws FailedLoginException, IllegalArgumentException, IllegalStateException
    {
        // reject empty ssids
        if (ssid.isEmpty())
        {
            throw new IllegalArgumentException("SSID and Password must be non-empty.");
        }

        // check if wifi is disabled
        JSONObject status = GetWirelessStatus();

        if (status.getString("wifi-hw").equals("disabled"))
        {
            throw new IllegalStateException("Wifi Hardware is disabled");
        }

        if (status.getString("wifi").equals("disabled"))
        {
            throw new IllegalStateException("Wifi is disabled");
        }


        // check if already connected to ssid
        JSONArray activeConnections = GetActiveConnections();

        for (int i = 0; i < activeConnections.length(); i++)
        {
            JSONObject connection = activeConnections.getJSONObject(i);

            if (connection.getString("type").equals(WirelessConstants.WIRELESS_DEVICE_TYPE) && connection.getString("name").equals(ssid))
            {
                throw new IllegalStateException(String.format("Already connected to '%s'", ssid));
            }
        }

        // check if connection already exists
        boolean found = false;
        JSONArray connections = GetConnections();

        for(int i=0; i<connections.length(); i++)
        {
            JSONObject connection = connections.getJSONObject(i);

            if(connection.getString("name").equals(ssid))
            {
                found = true;
                break;
            }
        }

        if(found)
        {
            logger.debug(String.format("Connection '%s' found", ssid));
            logger.debug(String.format("Connecting to '%s'...", ssid));
            String cmd = String.format("nmcli %s connection up '%s'", WirelessConstants.NMCLI_OPTS, ssid);
            String result = agent.Execute(cmd);

            if (result.contains(WirelessConstants.CONNECT_SUCCESS_MESSAGE))
            {
                logger.debug(String.format("Successfully connected to '%s'", ssid));
                return;
            }
            else //if(result.contains(WirelessConstants.CONNECT_WRONG_PASSWORD_MESSAGE))
            {
                throw new FailedLoginException("Invalid credentials");
            }
        }

        // connection not found so throw exception
        throw new IllegalStateException("Connection does not exist.");
    }

    /**
     * Connects to a wireless connection. Also, properly handles error conditions.
     * @param   ssid ssid of the access point
     * @param   password password of the connection
     */
    public void Connect(String ssid, String password) throws FailedLoginException, IllegalArgumentException, IllegalStateException
    {
        // reject empty ssids or passwords
        if (ssid.isEmpty() || password.isEmpty())
        {
            throw new IllegalArgumentException("SSID and Password must be non-empty.");
        }

        // check if wifi is disabled
        JSONObject status = GetWirelessStatus();

        if (status.getString("wifi-hw").equals("disabled"))
        {
            throw new IllegalStateException("Wifi Hardware is disabled");
        }

        if (status.getString("wifi").equals("disabled"))
        {
            throw new IllegalStateException("Wifi is disabled");
        }


        // check if already connected to ssid
        JSONArray activeConnections = GetActiveConnections();

        for (int i = 0; i < activeConnections.length(); i++)
        {
            JSONObject connection = activeConnections.getJSONObject(i);

            if (connection.getString("type").equals(WirelessConstants.WIRELESS_DEVICE_TYPE) && connection.getString("name").equals(ssid))
            {
                throw new IllegalStateException(String.format("Already connected to '%s'", ssid));
            }
        }


        // reject invalid ssid
        JSONArray availableConnections = GetAvailableConnections();
        boolean valid = false;

        for (int i = 0; i < availableConnections.length(); i++)
        {
            JSONObject connection = availableConnections.getJSONObject(i);

            if (connection.getString("ssid").equals(ssid))
            {
                valid = true;
                break;
            }
        }

        if (!valid)
        {
            throw new IllegalArgumentException("Invalid SSID");
        }

        // remove existing connection
        try
        {
            DeleteConnection(ssid);
        }
        catch (IllegalStateException e)
        {

        }

        logger.debug("New connection will be created.");
        logger.debug(String.format("Connecting to '%s'...", ssid));

        String cmd = String.format("nmcli %s device wifi connect '%s' password '%s'", WirelessConstants.NMCLI_OPTS, ssid, password);
        String result = agent.Execute(cmd);

        if (result.contains(WirelessConstants.CONNECT_SUCCESS_MESSAGE))
        {
            logger.debug(String.format("Successfully connected to '%s'", ssid));
            return;
        }

        // in case of failure, delete the connection so that it does not appear as saved connection
        try
        {
            DeleteConnection(ssid);
        }
        catch (IllegalStateException e)
        {

        }

        throw new FailedLoginException("Invalid credentials");
    }

    public boolean Disconnect() throws IllegalStateException
    {
        if (!IsConnected())
        {
            throw new IllegalStateException("Not connected");
        }

        boolean disconnected = false;

        JSONArray activeConnections = GetActiveConnections();

        for (int i = 0; i < activeConnections.length(); i++)
        {
            JSONObject connection = activeConnections.getJSONObject(i);

            if (connection.getString("type").equals(WirelessConstants.WIRELESS_DEVICE_TYPE))
            {
                logger.debug(String.format("Disconnecting connection '%s'...", connection.getString("name")));
                String cmd = String.format("nmcli %s connection down '%s'", WirelessConstants.NMCLI_OPTS, connection.getString("name"));
                String result = agent.Execute(cmd);

                if (result.contains(WirelessConstants.DISCONNECT_SUCCESS_MESSAGE))
                {
                    disconnected = true;
                    logger.debug(String.format("Successfully disconnected from '%s'.", connection.getString("name")));
                }
            }
        }

        return disconnected;
    }

    /** Returns the currently saved wifi connections in the system. */
    public JSONArray GetConnections()
    {
        JSONArray jarray = new JSONArray();

        try
        {
            final String[] FIELDS = {"name", "type", "device", "uuid"};
            String cmd = String.format("nmcli %s -f %s connection show", WirelessConstants.NMCLI_OPTS, String.join(",", FIELDS));

            String result = agent.Execute(cmd);

            for (String line : result.split("\n"))
            {
                String[] fields = line.split(":", -1);

                // ignore invalids
                if (FIELDS.length != fields.length || fields[0].isEmpty())
                {
                    logger.debug("Failed to satisfy expected format. Ignoring");
                    continue;
                }

                // ignore non wireless types
                if(!fields[1].equals(WirelessConstants.WIRELESS_DEVICE_TYPE))
                {
                    logger.debug("{} is not of {} type. Ignoring", fields[0], WirelessConstants.WIRELESS_DEVICE_TYPE);
                    continue;
                }

                JSONObject json = new JSONObject();

                for (int i = 0; i < FIELDS.length; i++)
                {
                    json.put(FIELDS[i], fields[i]);
                }

                jarray.put(json);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return jarray;
    }

    /** Gets the list of available wifi connections around. Duplicates and invalid SSIDs are removed. */
    public JSONArray GetAvailableConnections()
    {
        final String[] FIELDS = {"ssid", "signal", "security"};

        JSONArray connections = new JSONArray();
        JSONObject uniqueConnections = new JSONObject();
        JSONArray savedConnections = GetConnections();

        try
        {
            String cmd = String.format("nmcli %s device wifi rescan", WirelessConstants.NMCLI_OPTS);
            agent.Execute(cmd);

            try
            {
                Thread.sleep(5000);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            cmd = String.format("nmcli %s -f %s device wifi list", WirelessConstants.NMCLI_OPTS, String.join(",", FIELDS));

            String result = agent.Execute(cmd);

            for (String line : result.split("\n"))
            {
                String[] fields = line.split(":", -1);

                logger.debug("[ FIELDS ] : {}", Arrays.toString(fields));

                if (FIELDS.length != fields.length || fields[0].isEmpty())
                {
                    logger.debug("Failed to satisfy expected format. Ignoring");
                    continue;
                }

                JSONObject json = new JSONObject();

                for (int i = 0; i < FIELDS.length; i++)
                {
                    json.put(FIELDS[i], fields[i]);
                }

                // handle duplicate
                // choose the ssid with the strongest signal among duplicates
                try
                {
                    String ssid = json.getString(FIELDS[0]);
                    int index = -1;

                    logger.debug("Checking if '{}' is already present in the array", ssid);
                    if (uniqueConnections.has(ssid))
                    {
                        index = uniqueConnections.getInt(ssid);
                    }

                    if (index > -1)
                    {
                        logger.debug("'{}' found at index {}", ssid, index);
                        JSONObject connection = connections.getJSONObject(index);

                        if (Integer.valueOf(json.getString(FIELDS[1])) > Integer.valueOf(connection.getString(FIELDS[1])))
                        {
                            logger.debug("Higher signal strength found. Replacing old value '{}' with new value '{}'.", connection.getString(FIELDS[1]), json.getString(FIELDS[1]));
                            connection.put(FIELDS[1], json.getString(FIELDS[1]));
                        }
                    }
                    else
                    {
                        logger.debug("'{}' not found. Inserting into the array.", ssid);
                        index = connections.length();
                        uniqueConnections.put(json.getString(FIELDS[0]), index);
                        connections.put(json);
                        logger.debug("'{}' successfully inserted at index {}", ssid, index);
                    }
                }
                catch (JSONException e)
                {
                    uniqueConnections.put(json.getString(FIELDS[0]), uniqueConnections.length());
                    connections.put(json);
                }

                // check if connection is already saved
                boolean saved = false;
                for(int i=0; i<savedConnections.length(); i++)
                {
                    JSONObject savedConnection = savedConnections.getJSONObject(i);

                    if(savedConnection.getString("name").equals(json.getString(FIELDS[0])))
                    {
                        saved = true;
                        break;
                    }
                }

                json.put("saved", String.valueOf(saved));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return connections;
    }

    public JSONObject GetStatus()
    {
        // check if wifi is disabled
        JSONObject status = GetWirelessStatus();

        if (status.getString("wifi-hw").equals("disabled"))
        {
            throw new IllegalStateException("Wifi Hardware is disabled");
        }

        if (status.getString("wifi").equals("disabled"))
        {
            throw new IllegalStateException("Wifi is disabled");
        }

        status = new JSONObject();

        try
        {
            status.put("connected", String.valueOf(IsConnected()));

            JSONArray activeConnections = GetActiveConnections();

            for (int i = 0; i < activeConnections.length(); i++)
            {
                JSONObject connection = activeConnections.getJSONObject(i);

                if (connection.getString("type").equals(WirelessConstants.WIRELESS_DEVICE_TYPE))
                {
                    JSONObject json = new JSONObject();

                    json.put("name", connection.getString("name"));
                    json.put("device", connection.getString("device"));

                    status.put("connection", json);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return status;
    }

    /** Gets the currently active connections */
    public JSONArray GetActiveConnections()
    {
        JSONArray jarray = new JSONArray();

        try
        {
            final String[] FIELDS = {"name", "type", "device", "uuid"};
            String cmd = String.format("nmcli %s -f %s connection show --active", WirelessConstants.NMCLI_OPTS, String.join(",", FIELDS));

            String result = agent.Execute(cmd);

            for (String line : result.split("\n"))
            {
                String[] fields = line.split(":", -1);

                if (FIELDS.length != fields.length)
                {
                    continue;
                }

                JSONObject json = new JSONObject();

                for (int i = 0; i < FIELDS.length; i++)
                {
                    json.put(FIELDS[i], fields[i]);
                }

                jarray.put(json);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return jarray;
    }

    public boolean IsConnected()
    {
        JSONArray jarray = GetActiveConnections();

        try
        {
            for (int i = 0; i < jarray.length(); i++)
            {
                if (jarray.getJSONObject(i).getString("type").equals(WirelessConstants.WIRELESS_DEVICE_TYPE))
                {
                    return true;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    /** Gets the system wireless status. Checks the status of both wireless hardware and wireless functionality. */
    public JSONObject GetWirelessStatus()
    {
        JSONObject json = new JSONObject();

        String[] FIELDS = {"wifi-hw", "wifi"};

        String cmd = String.format("nmcli %s -f %s general status", WirelessConstants.NMCLI_OPTS, String.join(",", FIELDS));

        String result = agent.Execute(cmd);
        String[] fields = result.split(":", -1);

        for (int i = 0; i < FIELDS.length; i++)
        {
            json.put(FIELDS[i], fields[i]);
        }

        return json;
    }

    public void DeleteConnection(String name)
    {
        JSONArray connections = GetConnections();

        for (int i = 0; i < connections.length(); i++)
        {
            JSONObject connection = connections.getJSONObject(i);

            if (connection.getString("type").equals(WirelessConstants.WIRELESS_DEVICE_TYPE) && connection.getString("name").equals(name))
            {
                logger.debug(String.format("Connection '%s' exists", name));
                logger.debug(String.format("Deleting existing '%s' connection", name));

                String cmd = String.format("nmcli %s connection delete '%s'", WirelessConstants.NMCLI_OPTS, name);
                String result = agent.Execute(cmd);

                if (result.contains(WirelessConstants.CONNECTION_DELETE_SUCCESS_MESSAGE))
                {
                    logger.debug(String.format("Connection '%s' deleted successfully.", name));
                    return;
                }
                else
                {
                    logger.debug(String.format("Connection '%s' could not be deleted.", name));
                    throw new IllegalStateException(String.format("Failed to delete '%s'", name));
                }
            }
        }

        throw new IllegalStateException(String.format("Connection '%s' not found", name));
    }
}
