
package org.openhab.binding.gridpal.devicemanager.manager;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openhab.binding.gridpal.devicemanager.device.AbstractConfigParser;
import org.openhab.binding.gridpal.devicemanager.device.knx.KnxManager;
import org.openhab.binding.gridpal.devicemanager.device.mbus.MBusManager;
import org.openhab.binding.gridpal.devicemanager.device.modbus.ModbusManager;
import org.openhab.binding.gridpal.devicemanager.device.wmbus.WMBusManager;

/**
 * This class is responsible for managing all the devices on the system.
 * It contains the managers of each device type and delegates commands to them.
 * Also, supervises other maangers relating to devices.
 * @author  Md. Farhabi Helal Ayon
 * @since   0.1.0
 */
public class DeviceManager
{
    ModbusManager modbusManager = new ModbusManager();
    MBusManager mbusManager = new MBusManager();
    WMBusManager wmbusManager = new WMBusManager();
    KnxManager knxManager = new KnxManager();
    ExportManager exportManager = new ExportManager();

    /**
     * Fetches information about all existing devices.
     * @return  information of existing devices.
     */
    public JSONArray GetDevices() throws JSONException, IOException
    {
        JSONArray devices = new JSONArray();

        JSONArray modbusDevices = modbusManager.GetDevices();

        for (int i = 0; i < modbusDevices.length(); i++)
        {
            devices.put(modbusDevices.getJSONObject(i));
        }

        JSONArray mbusDevices = mbusManager.GetDevices();

        for (int i = 0; i < mbusDevices.length(); i++)
        {
            devices.put(mbusDevices.getJSONObject(i));
        }

        JSONArray wmbusDevices = wmbusManager.GetDevices();

        for (int i = 0; i < wmbusDevices.length(); i++)
        {
            devices.put(wmbusDevices.getJSONObject(i));
        }

        JSONArray knxDevices = knxManager.GetDevices();

        for (int i = 0; i < knxDevices.length(); i++)
        {
            devices.put(knxDevices.getJSONObject(i));
        }

        return devices;
    }

    /**
     * Fetches information about an existing device.
     * @param   slaveName name of the device.
     * @return  information of the existing device.
     */
    public JSONObject GetDevice(String slaveName) throws JSONException, IOException
    {
        JSONArray jarray = GetDevices();

        for (int i = 0; i < jarray.length(); i++)
        {
            JSONObject json = jarray.getJSONObject(i);

            if (json.getString("name").equals(slaveName))
            {
                return json;
            }
        }

        return null;
    }

    /**
     * Adds a supported device.
     * @param   slave description of the device.
     * @return  status of the request. If device type is not supported by the system, false is returned.
     * @throws  JSONException in the event of parsing error. IOException if system can not
                modify underlying files.
     */
    public boolean AddDevice(JSONObject slave) throws JSONException, IOException
    {
        switch (AbstractConfigParser.ParseInterfaceType(slave.getString("interface-type"))[0])
        {
            case "modbus":
                return modbusManager.AddDevice(slave);

            case "mbus":
                return mbusManager.AddDevice(slave);

            case "wmbus":
                return wmbusManager.AddDevice(slave);

            case "knx":
                return knxManager.AddDevice(slave);
        }

        return false;
    }

    /**
     * Removes an existing device.
     * @param   slaveName name of the device.
     * @return  status of the request. If device type is not supported by the system, false is returned.
     * @throws  JSONException in the event of parsing error. IOException if system can not
                modify underlying files.
     */
    public boolean RemoveDevice(String slaveName) throws JSONException, IOException
    {
        return modbusManager.RemoveDevice(slaveName) || mbusManager.RemoveDevice(slaveName)
                || wmbusManager.RemoveDevice(slaveName) || knxManager.RemoveDevice(slaveName);
    }

    /**
     * Removes all existing devices.
     * @return  status of the request. If any manager fails to execute the command, false is returned.
     * @throws  JSONException in the event of parsing error. IOException if system can not
                modify underlying files.
     */
    public boolean RemoveDevices() throws JSONException, IOException
    {
        return modbusManager.RemoveDevices() && mbusManager.RemoveDevices() && wmbusManager.RemoveDevices()
                && knxManager.RemoveDevices();
    }
    
    /**
     * Updates an existing device.
     * @return  status of the request. If all managers fails to execute the command, false is returned.
     * @throws  JSONException in the event of parsing error. IOException if system can not
                modify underlying files.
     */
    public boolean UpdateDevice(JSONObject params) throws JSONException, IOException
    {
        return modbusManager.UpdateDevice(params) || mbusManager.UpdateDevice(params)
                || wmbusManager.UpdateDevice(params) || knxManager.UpdateDevice(params);
    }

    /**
     * Exports item data of a device.
     * @param   itemname name of the item.
     * @param   fromDate start date.
     * @param   toDate end date.
     * @return  string of item data in csv format.
     */
    public String ExportDevice(String itemname, String fromDate, String toDate)
    {
        try
        {
            return exportManager.GetItemDataFromPersistance(itemname, fromDate, toDate);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
