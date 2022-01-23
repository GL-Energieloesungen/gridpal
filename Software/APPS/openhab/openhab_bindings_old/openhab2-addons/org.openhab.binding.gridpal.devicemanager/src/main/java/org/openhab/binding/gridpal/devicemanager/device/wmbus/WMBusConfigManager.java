
package org.openhab.binding.gridpal.devicemanager.device.wmbus;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openhab.binding.gridpal.devicemanager.device.utils.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WMBusConfigManager
{
    private WMBusConfigParser parser = new WMBusConfigParser();
    private String filename = null;
    private Logger logger = LoggerFactory.getLogger(WMBusConfigManager.class);

    public WMBusConfigManager(String filename)
    {
        this.filename = filename;
    }

    public JSONArray GetDevices() throws JSONException, IOException
    {
        return parser.ToJSON(this.filename).getJSONArray(WMBusConfigKey.DEVICES);
    }

    public JSONObject GetDevice(String name) throws JSONException, IOException
    {
        JSONObject json = parser.ToJSON(this.filename);

        JSONArray devices = json.getJSONArray(WMBusConfigKey.DEVICES);

        for (int i = 0; i < devices.length(); i++)
        {
            JSONObject device = devices.getJSONObject(i);

            if (device.get(WMBusConfigKey.NAME).equals(name))
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
            if (GetDevice(params.getString(WMBusConfigKey.NAME)) == null)
            {
                return false;
            }

            JSONObject json = parser.ToJSON(this.filename);

            JSONArray slaves = json.getJSONArray(WMBusConfigKey.DEVICES);

            for (int i = 0; i < slaves.length(); i++)
            {
                JSONObject slave = slaves.getJSONObject(i);

                if (slave.getString(WMBusConfigKey.NAME).equals(params.getString(WMBusConfigKey.NAME)))
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
        } catch (JSONException | IOException e)
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
            JSONArray slaves = (JSONArray) json.get(WMBusConfigKey.DEVICES);

            for (int i = 0; i < slaves.length(); i++)
            {
                JSONObject slave = (JSONObject) slaves.get(i);

                if (slave.getString(WMBusConfigKey.NAME).equals(newSlave.getString(WMBusConfigKey.NAME)))
                {
                    slaves.remove(i--);
                }
            }

            json.append(WMBusConfigKey.DEVICES, newSlave);

            logger.debug("\n\n[ NEW JSON ] :\n{}\n\n", json.toString());

            Utility.Write(this.filename, parser.ToConfig(json));

            return true;
        } catch (JSONException | IOException e)
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
            JSONArray slaves = (JSONArray) json.get(WMBusConfigKey.DEVICES);

            for (int i = 0; i < slaves.length(); i++)
            {
                JSONObject slave = (JSONObject) slaves.get(i);

                if (slave.getString(WMBusConfigKey.NAME).equals(name))
                {
                    slaves.remove(i--);
                }
            }

            logger.debug("\n\n[ NEW JSON ] :\n{}\n\n", json.toString());

            Utility.Write(this.filename, parser.ToConfig(json));

            return true;
        } catch (JSONException | IOException e)
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
            json.put(WMBusConfigKey.DEVICES, new JSONArray());

            logger.debug("\n\n[ NEW JSON ] :\n{}\n\n", json.toString());

            Utility.Write(this.filename, parser.ToConfig(json));

            return true;
        } catch (JSONException | IOException e)
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
