
package org.openhab.binding.gridpaldevicemanager.device.modbus;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openhab.binding.gridpaldevicemanager.item.ItemManager;

public class ModbusManager {
    private ModbusConfigManager configManager;
    private ItemManager itemManager;

    public ModbusManager() {
        configManager = new ModbusConfigManager();
        itemManager = new ItemManager(ModbusConstants.itemsPath, new ModbusItemsParser());
    }

    public ModbusManager(String configPath, String itemsPath) {
        configManager = new ModbusConfigManager(configPath);
        itemManager = new ItemManager(itemsPath, new ModbusItemsParser());
    }

    public JSONArray GetDevices() throws JSONException, IOException {
        return configManager.GetDevices();
    }

    public JSONObject GetDevice(String name) throws JSONException, IOException {
        return configManager.GetDevice(name);
    }

    public boolean AddDevice(JSONObject newSlave) {
        try {
            return configManager.AddDevice(newSlave) && itemManager.Put(configManager.GetConfig());
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean RemoveDevice(String name) {
        try {
            return configManager.RemoveDevice(name) && itemManager.Put(configManager.GetConfig());
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean RemoveDevices() {
        try {
            return configManager.RemoveDevices() && itemManager.Put(configManager.GetConfig());
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean UpdateDevice(JSONObject params) {
        try {
            return configManager.UpdateDevice(params) && itemManager.Put(configManager.GetConfig());
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
