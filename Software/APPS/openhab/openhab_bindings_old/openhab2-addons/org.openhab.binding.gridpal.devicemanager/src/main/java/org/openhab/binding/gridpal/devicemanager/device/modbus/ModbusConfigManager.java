
package org.openhab.binding.gridpal.devicemanager.device.modbus;





import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openhab.binding.gridpal.devicemanager.device.utils.Utility;
import org.openhab.binding.gridpal.devicemanager.manager.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/**
 * Manages the configuration of modbus devices.
 * @author  Md. Farhabi Helal Ayon
 * @since   0.1.0
 */
public class ModbusConfigManager
{
    private ModbusConfigParser parser = new ModbusConfigParser();
    private String filename = null;
    private Logger logger = LoggerFactory.getLogger(ModbusConfigManager.class);


    public ModbusConfigManager()
    {
        this.filename = ModbusConstants.configPath;
    }

    public ModbusConfigManager(String filename)
    {
        this.filename = filename;
    }

    public JSONArray GetDevices() throws JSONException, IOException
    {
        return parser.ToJSON(this.filename).getJSONArray(ModbusConfigKey.SLAVES);
    }

    public JSONObject GetDevice(String name) throws JSONException, IOException
    {
        JSONObject json = parser.ToJSON(this.filename);

        JSONArray devices = json.getJSONArray(ModbusConfigKey.SLAVES);

        for (int i = 0; i < devices.length(); i++)
        {
            JSONObject device = devices.getJSONObject(i);

            if (device.get(ModbusConfigKey.NAME).equals(name))
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
            if (GetDevice(params.getString(ModbusConfigKey.NAME)) == null)
            {
                return false;
            }

            JSONObject json = parser.ToJSON(this.filename);

            JSONArray slaves = json.getJSONArray(ModbusConfigKey.SLAVES);

            for (int i = 0; i < slaves.length(); i++)
            {
                JSONObject slave = slaves.getJSONObject(i);

                if (slave.getString(ModbusConfigKey.NAME).equals(params.getString(ModbusConfigKey.NAME)))
                {
                    for (String key : JSONObject.getNames(params))
                    {
                        slave.put(key, params.get(key));
                    }

                    break;
                }
            }

            DeleteConfigFromOSGiRuntime();

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
            JSONArray slaves = (JSONArray) json.get(ModbusConfigKey.SLAVES);

            for (int i = 0; i < slaves.length(); i++)
            {
                JSONObject slave = (JSONObject) slaves.get(i);

                if (slave.getString(ModbusConfigKey.NAME).equals(newSlave.getString(ModbusConfigKey.NAME)))
                {
                    slaves.remove(i--);
                }
            }

            json.append(ModbusConfigKey.SLAVES, newSlave);

            logger.debug("\n\n[ NEW JSON ] :\n{}\n\n", json.toString());

            DeleteConfigFromOSGiRuntime();

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
            JSONArray slaves = (JSONArray) json.get(ModbusConfigKey.SLAVES);

            for (int i = 0; i < slaves.length(); i++)
            {
                JSONObject slave = (JSONObject) slaves.get(i);

                if (slave.getString(ModbusConfigKey.NAME).equals(name))
                {
                    slaves.remove(i--);
                }
            }

            logger.debug("\n\n[ NEW JSON ] :\n{}\n\n", json.toString());

            DeleteConfigFromOSGiRuntime();

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
            json.put(ModbusConfigKey.SLAVES, new JSONArray());

            logger.debug("\n\n[ NEW JSON ] :\n{}\n\n", json.toString());

            DeleteConfigFromOSGiRuntime();

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

    private void DeleteConfigFromOSGiRuntime()
    {
        ConfigManager.GetInstance().Delete(ModbusConstants.MODBUS_BINDING_SYMBOLIC_NAME);
    }
}
