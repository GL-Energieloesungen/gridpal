
package org.openhab.binding.gridpal.devicemanager.device.mbus;



import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openhab.binding.gridpal.devicemanager.item.ItemManager;





public class MBusManager
{
	private MBusConfigManager configManager;
	private ItemManager itemManager;

	public MBusManager()
	{
		configManager = new MBusConfigManager(MBusConstants.configPath);
		itemManager = new ItemManager(MBusConstants.itemsPath, new MBusItemsParser());
	}

	public MBusManager(String configPath, String itemsPath)
	{
		configManager = new MBusConfigManager(configPath);
		itemManager = new ItemManager(itemsPath, new MBusItemsParser());
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
