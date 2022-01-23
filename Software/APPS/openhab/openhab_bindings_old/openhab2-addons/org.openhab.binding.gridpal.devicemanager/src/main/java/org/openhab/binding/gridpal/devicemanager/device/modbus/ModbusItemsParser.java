
package org.openhab.binding.gridpal.devicemanager.device.modbus;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openhab.binding.gridpal.devicemanager.device.AbstractConfigParser;
import org.openhab.binding.gridpal.devicemanager.item.Item;
import org.openhab.binding.gridpal.devicemanager.item.ItemsParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parses modbus device item.
 * @author      Md. Farhabi Helal Ayon
 * @since       1.0
 */
public class ModbusItemsParser extends ItemsParser
{
    private Logger logger = LoggerFactory.getLogger(ModbusItemsParser.class);

    public ModbusItemsParser()
    {}
    
    /**
     * Creates a list of item by from device description. This interface requires a json that contains the devices.
     * @param   json  json containing devices.
     * @return  list of items
     */
    @Override
    public ArrayList<Item> Parse(JSONObject json) throws JSONException
    {
        return Parse(json.getJSONArray(ModbusConfigKey.SLAVES));
    }

    /**
     * Creates a list of item by from device description. This interface requires an array of devices.
     * @param   jarray  array of devices
     * @return  list of items
     */
    @Override
    public ArrayList<Item> Parse(JSONArray jarray) throws JSONException
    {
        ArrayList<Item> items = new ArrayList<>();

        for (int i = 0; i < jarray.length(); i++)
        {
            JSONObject slave = jarray.getJSONObject(i);
            JSONArray registerMaps = slave.getJSONArray(ModbusConfigKey.REGISTER_MAP);

            for (int j = 0; j < registerMaps.length(); j++)
            {
                ModbusItem item = new ModbusItem();
                item.type = "Number";
                item.name = AbstractConfigParser.GetValidSlaveName(slave.getString(ModbusConfigKey.NAME)) + "__"
                        + registerMaps.getJSONObject(j).getString(ModbusConfigKey.DESCRIPTION).replaceAll("\\s", "_");
                item.label = "";
                item.multiplyFactor = Double
                        .parseDouble(registerMaps.getJSONObject(j).getString(ModbusConfigKey.MULTIPLY));

                item.pattern = "%s";
                item.unit = registerMaps.getJSONObject(j).getString(ModbusConfigKey.UNIT);

                String jsFilename = "pulsecounter.js";
                String offset = registerMaps.getJSONObject(j).getString(ModbusConfigKey.OFFSET);
                item.stateFormat = offset.equals("")
                        ? String.format("%s(%.3f):%s%s", "MULTIPLY", item.multiplyFactor, item.pattern,
                                item.unit.equals("") ? "" : String.format(" %s", item.unit))
                        : String.format("%s(%s):%s %s %s%s", "JS", jsFilename, item.pattern, item.multiplyFactor,
                                offset, item.unit.equals("") ? "" : String.format(" %s", item.unit));
                item.icon = "";
                item.groups = new ArrayList<String>(
                        Arrays.asList(AbstractConfigParser.GetValidSlaveName(slave.getString(ModbusConfigKey.NAME))));
                item.tags = new ArrayList<>();
                item.slaveName = String.format("%s_%d",
                        AbstractConfigParser.GetValidSlaveName(slave.getString(ModbusConfigKey.NAME)), j);
                item.slaveIndex = 0;
                item.bindingName = "modbus";
                item.bindingConfig = String.format("%s:%d", item.slaveName, item.slaveIndex);

                items.add(item);
            }
        }

        return items;
    }
}
