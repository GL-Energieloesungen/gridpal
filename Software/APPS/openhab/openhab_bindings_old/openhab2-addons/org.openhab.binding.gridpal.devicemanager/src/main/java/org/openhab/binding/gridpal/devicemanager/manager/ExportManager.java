package org.openhab.binding.gridpal.devicemanager.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openhab.binding.gridpal.devicemanager.device.utils.Utility;

/**
 * This class manages export of item data. It connectes to the openHAB persistance database and retrieves item data from it.
 * The data is formatted as a CSV file which is served by it's API.
 * @author  Md. Farhabi Helal Ayon
 * @since   0.1.0
 */
public class ExportManager
{
    public static final String persistenceURL = "http://localhost:8080/rest/persistence/items";
    private String url = "";
    private String user = "";
    private String password = "";
    private Connection connection = null;

    Pattern patternJdbcConfig = Pattern.compile("([\\w_]+)\\s*=\\s*(.*)");

    private static final SimpleDateFormat sqlDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat openhabPersistenceDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat openhabPersistenceTimeFormat = new SimpleDateFormat("HH:mm:ss");

    public ExportManager()
    {}

    public void Connect(String url) throws SQLException
    {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        connection = DriverManager.getConnection(url, user, password);
    }

    /** Loades connection parameters and connects to openHAB Persistance database. */
    public void Connect() throws SQLException
    {
        ParseCredentials();
        Connect(this.url);
    }

    public void Disconnect(Connection connection)
    {
        try
        {
            connection.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void Disconnect()
    {
        Disconnect(connection);
    }

    /**
     * Gets the ID associated with the item in the database.
     * @param   itemname name of the item.
     * @return  ID of item in the database.
     */
    public Integer GetItemId(String itemname)
    {
        String sqlQuery = String.format("select ItemId from items where itemname = '%s' ;", itemname);

        try
        {
            Connect();
            ResultSet result = connection.createStatement().executeQuery(sqlQuery);

            result.next();
            Integer itemId = Integer.valueOf(result.getString("ItemId"));

            result.close();

            return itemId;
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            Disconnect();
        }

        return null;
    }

    /**
     * Retrieves item data from the openHAB Persistance database.
     * @param   table name of the item.
     * @param   fromDate start date.
     * @param   toDate end date.
     * @return  string of item data in csv format.
     */
    public String GetItemData(String table, String fromDate, String toDate)
    {
        /** corrects invalid {@fromDate} with default value. */ 
        if (fromDate == null || fromDate.isEmpty())
        {
            fromDate = "1970-01-01 00:00:00.000000000";
        }
        /** corrects invalid {@toDate} with default value. */
        if (toDate == null || toDate.isEmpty())
        {
            toDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.000000000").format(Calendar.getInstance().getTime());
        }

        /** corrects invalid chronology. */
        if (fromDate.compareTo(toDate) > 0)
        {
            String temp = fromDate;
            fromDate = toDate;
            toDate = temp;
        }

        String sqlQuery = String.format("select time, value from %s where time between '%s' and '%s' ;", table,
                fromDate, toDate);

        try
        {
            Connect();
            ResultSet result = connection.createStatement().executeQuery(sqlQuery);

            StringBuilder sb = new StringBuilder();

            while (result.next())
            {
                sb.append(String.format("\"%s\", \"%s\"\n", result.getString("time"), result.getString("value")));
            }

            result.close();

            if (!sb.toString().isEmpty())
            {
                return String.format("\"%s\", \"%s\"\n%s", "time", "value", sb.toString());
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            Disconnect();
        }

        return "";
    }

    /**
     * Retrieves item data using openHAB Persistance API.
     * @param   itemname name of the item.
     * @param   fromDate start date.
     * @param   toDate end date.
     * @return  string of item data in csv format.
     */
    String GetItemDataFromPersistance(String itemname, String fromDate, String toDate)
    {

        if (fromDate == null || fromDate.isEmpty())
        {
            fromDate = "1970-01-01T00:00:00";
        }
        if (toDate == null || toDate.isEmpty())
        {
            toDate = String.format("%sT%s", openhabPersistenceDateFormat.format(Calendar.getInstance().getTime()),
                    openhabPersistenceTimeFormat.format(Calendar.getInstance().getTime()));
        }

        if (fromDate.compareTo(toDate) > 0)
        {
            String temp = fromDate;
            fromDate = toDate;
            toDate = temp;
        }

        try
        {
            URL url = new URL(String.format("%s/%s?starttime=%s&endtime=%s", ExportManager.persistenceURL, itemname,
                    URLEncoder.encode(fromDate, "UTF-8"), URLEncoder.encode(toDate, "UTF-8")));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line + '\n');
            }

            br.close();

            // System.out.println("\n\n[ RESPONSE ] :\n" + sb.toString());

            JSONObject json = new JSONObject(sb.toString());

            StringBuilder response = new StringBuilder();
            JSONArray data = json.getJSONArray("data");

            for (int i = 0; i < data.length(); i++)
            {
                response.append(String.format("\"%s\", \"%s\"\n",
                        sqlDateTimeFormat.format(new Date(data.getJSONObject(i).getLong("time"))),
                        String.valueOf(data.getJSONObject(i).get("state"))));
            }

            response.insert(0, String.format("\"%s\", \"%s\"\n", "time", "value"));

            // System.out.println("\n\n[ RESPONSE ] :\n" + response.toString());

            return response.toString();

        } catch (JSONException | IOException e)
        {
            e.printStackTrace();
        }

        return "";
    }

    /** Parses configuration and loades connection parameters. */
    private void ParseCredentials()
    {
        String filename = "/etc/openhab2/services/jdbc.cfg";

        try
        {
            ArrayList<String> lines = Utility.Read(filename, "#");

            for (String line : lines)
            {
                Matcher matcher = patternJdbcConfig.matcher(line);

                if (matcher.find())
                {
                    String key = matcher.group(1);
                    String value = matcher.group(2);

                    switch (key)
                    {
                        case "url":
                            url = value;
                            break;
                        case "user":
                            user = value;
                            break;
                        case "password":
                            password = value;
                            break;
                        default:
                            break;
                    }
                }

            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
