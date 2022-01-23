package org.openhab.binding.gridpal.devicemanager.device.knx;



import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openhab.binding.gridpal.devicemanager.item.ItemManager;





public class KnxManager
{
    private KnxConfigManager configManager;
    private ItemManager itemManager;

    public KnxManager()
    {
        configManager = new KnxConfigManager(KnxConstants.configPath);
        itemManager = new ItemManager(KnxConstants.itemsPath, new KnxItemsParser());
    }

    public KnxManager(String itemsPath)
    {
        configManager = new KnxConfigManager(itemsPath);
        itemManager = new ItemManager(itemsPath, new KnxItemsParser());
    }

    public JSONArray GetDevices() throws JSONException, IOException
    {
        return configManager.GetDevices();
    }

    public JSONObject GetDevice(String name) throws JSONException, IOException
    {
        return configManager.GetDevice(name);
    }

    public boolean AddDevice(JSONObject newSlave)
    {
        try
        {
            return configManager.AddDevice(newSlave) && itemManager.Put(configManager.GetConfig());
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
            return configManager.RemoveDevice(name) && itemManager.Put(configManager.GetConfig());
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
            return configManager.RemoveDevices() && itemManager.Put(configManager.GetConfig());
        }
        catch (JSONException | IOException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public boolean UpdateDevice(JSONObject params)
    {
        try
        {
            return configManager.UpdateDevice(params) && itemManager.Put(configManager.GetConfig());
        }
        catch (JSONException | IOException e)
        {
            e.printStackTrace();
        }

        return false;
    }
}
