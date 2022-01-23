
package org.openhab.binding.gridpal.devicemanager.device.mbus;



import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openhab.binding.gridpal.devicemanager.device.utils.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public class MBusConfigManager
{
    private MBusConfigParser parser = new MBusConfigParser();
    private String filename = null;
    private Logger logger = LoggerFactory.getLogger(MBusConfigManager.class);

    public MBusConfigManager(String filename)
    {
        this.filename = filename;
    }

    public JSONArray GetDevices() throws JSONException, IOException
    {
        return parser.ToJSON(this.filename).getJSONArray(MBusConfigKey.SLAVES);
    }

    public JSONObject GetDevice(String name) throws JSONException, IOException
    {
        JSONObject json = parser.ToJSON(this.filename);

        JSONArray devices = json.getJSONArray(MBusConfigKey.SLAVES);

        for (int i = 0; i < devices.length(); i++)
        {
            JSONObject device = devices.getJSONObject(i);

            if (device.get(MBusConfigKey.NAME).equals(name))
            {
                return device;
            }
        }

        return null;
    }

    public boolean UpdateDevice(JSONObject params)
    {
        try
        {
            if (GetDevice(params.getString(MBusConfigKey.NAME)) == null)
            {
                return false;
            }

            JSONObject json = parser.ToJSON(this.filename);

            JSONArray slaves = json.getJSONArray(MBusConfigKey.SLAVES);

            for (int i = 0; i < slaves.length(); i++)
            {
                JSONObject slave = slaves.getJSONObject(i);

                if (slave.getString(MBusConfigKey.NAME).equals(params.getString(MBusConfigKey.NAME)))
                {
                    for (String key : JSONObject.getNames(params))
                    {
                        slave.put(key, params.get(key));
                    }

                    break;
                }
            }

            Utility.Write(this.filename, parser.ToConfig(json));

            return true;
        }
        catch (JSONException | IOException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public boolean AddDevice(JSONObject newSlave)
    {
        try
        {
            JSONObject json = parser.ToJSON(this.filename);
            JSONArray slaves = (JSONArray) json.get(MBusConfigKey.SLAVES);

            for (int i = 0; i < slaves.length(); i++)
            {
                JSONObject slave = (JSONObject) slaves.get(i);

                if (slave.getString(MBusConfigKey.NAME).equals(newSlave.getString(MBusConfigKey.NAME)))
                {
                    slaves.remove(i--);
                }
            }

            // removing mbus keys
            JSONArray registerMap = newSlave.getJSONArray("register-map");

            for (int i = 0; i < registerMap.length(); i++)
            {
                JSONObject register = registerMap.getJSONObject(i);

                register.remove("valuetype");
                register.remove("type");
                register.remove("length");
            }

            json.append(MBusConfigKey.SLAVES, newSlave);

            logger.debug("\n\n[ NEW JSON ] :\n{}\n\n", json.toString());

            Utility.Write(this.filename, parser.ToConfig(json));

            return true;
        }
        catch (JSONException | IOException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public boolean RemoveDevice(String name)
    {
        try
        {
            if (GetDevice(name) == null)
            {
                return false;
            }

            JSONObject json = parser.ToJSON(this.filename);
            JSONArray slaves = (JSONArray) json.get(MBusConfigKey.SLAVES);

            for (int i = 0; i < slaves.length(); i++)
            {
                JSONObject slave = (JSONObject) slaves.get(i);

                if (slave.getString(MBusConfigKey.NAME).equals(name))
                {
                    slaves.remove(i--);
                }
            }

            logger.debug("\n\n[ NEW JSON ] :\n{}\n\n", json.toString());

            Utility.Write(this.filename, parser.ToConfig(json));

            return true;
        }
        catch (JSONException | IOException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public boolean RemoveDevices()
    {
        try
        {
            JSONObject json = parser.ToJSON(this.filename);
            json.put(MBusConfigKey.SLAVES, new JSONArray());

            logger.debug("\n\n[ NEW JSON ] :\n{}\n\n", json.toString());

            Utility.Write(this.filename, parser.ToConfig(json));

            return true;
        }
        catch (JSONException | IOException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public JSONObject GetConfig() throws JSONException, IOException
    {
        return parser.ToJSON(this.filename);
    }
}
