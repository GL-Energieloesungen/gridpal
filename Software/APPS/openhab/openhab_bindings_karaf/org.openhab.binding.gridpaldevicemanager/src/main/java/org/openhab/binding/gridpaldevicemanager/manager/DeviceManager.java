
package org.openhab.binding.gridpaldevicemanager.manager;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openhab.binding.gridpaldevicemanager.device.AbstractConfigParser;
import org.openhab.binding.gridpaldevicemanager.device.knx.KnxManager;
import org.openhab.binding.gridpaldevicemanager.device.mbus.MBusManager;
import org.openhab.binding.gridpaldevicemanager.device.modbus.ModbusManager;
import org.openhab.binding.gridpaldevicemanager.device.wmbus.WMBusManager;

public class DeviceManager {
    ModbusManager modbusManager = new ModbusManager();
    MBusManager mbusManager = new MBusManager();
    WMBusManager wmbusManager = new WMBusManager();
    KnxManager knxManager = new KnxManager();
    ExportManager exportManager = new ExportManager();

    public JSONArray GetDevices() throws JSONException, IOException {
        JSONArray devices = new JSONArray();

        JSONArray modbusDevices = modbusManager.GetDevices();

        for (int i = 0; i < modbusDevices.length(); i++) {
            devices.put(modbusDevices.getJSONObject(i));
        }

        JSONArray mbusDevices = mbusManager.GetDevices();

        for (int i = 0; i < mbusDevices.length(); i++) {
            devices.put(mbusDevices.getJSONObject(i));
        }

        JSONArray wmbusDevices = wmbusManager.GetDevices();

        for (int i = 0; i < wmbusDevices.length(); i++) {
            devices.put(wmbusDevices.getJSONObject(i));
        }

        JSONArray knxDevices = knxManager.GetDevices();

        for (int i = 0; i < knxDevices.length(); i++) {
            devices.put(knxDevices.getJSONObject(i));
        }

        return devices;
    }

    public JSONObject GetDevice(String slaveName) throws JSONException, IOException {
        JSONArray jarray = GetDevices();

        for (int i = 0; i < jarray.length(); i++) {
            JSONObject json = jarray.getJSONObject(i);

            if (json.getString("name").equals(slaveName)) {
                return json;
            }
        }

        return null;
    }

    public boolean AddDevice(JSONObject slave) throws JSONException, IOException {
        switch (AbstractConfigParser.ParseInterfaceType(slave.getString("interface-type"))[0]) {
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

    public boolean RemoveDevice(String slaveName) throws JSONException, IOException {
        return modbusManager.RemoveDevice(slaveName) || mbusManager.RemoveDevice(slaveName)
                || wmbusManager.RemoveDevice(slaveName) || knxManager.RemoveDevice(slaveName);
    }

    public boolean RemoveDevices() throws JSONException, IOException {
        return modbusManager.RemoveDevices() && mbusManager.RemoveDevices() && wmbusManager.RemoveDevices()
                && knxManager.RemoveDevices();
    }

    public boolean UpdateDevice(JSONObject params) throws JSONException, IOException {
        return modbusManager.UpdateDevice(params) || mbusManager.UpdateDevice(params)
                || wmbusManager.UpdateDevice(params) || knxManager.UpdateDevice(params);
    }

    public String ExportDevice(String itemname, String fromDate, String toDate) {
        try {
            return exportManager.GetItemDataFromPersistance(itemname, fromDate, toDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
