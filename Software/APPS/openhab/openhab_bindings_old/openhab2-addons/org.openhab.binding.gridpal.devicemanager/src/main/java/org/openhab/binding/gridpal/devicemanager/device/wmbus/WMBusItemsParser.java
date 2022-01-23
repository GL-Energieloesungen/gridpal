
package org.openhab.binding.gridpal.devicemanager.device.wmbus;

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

public class WMBusItemsParser extends ItemsParser
{
    private Logger logger = LoggerFactory.getLogger(WMBusItemsParser.class);

    public WMBusItemsParser()
    {}

    @Override
    public ArrayList<Item> Parse(JSONObject json) throws JSONException
    {
        return Parse(json.getJSONArray(WMBusConfigKey.DEVICES));
    }

    // @Override
    // public ArrayList<Item> Parse(JSONArray jarray) throws JSONException
    // {
    // ArrayList<Item> items = new ArrayList<>();
    //
    // for (int i = 0; i < jarray.length(); i++)
    // {
    // JSONObject device = jarray.getJSONObject(i);
    // JSONArray registerMap = device.getJSONArray(MBusConfigKey.REGISTER_MAP);
    //
    // for (int j = 0; j < registerMap.length(); j++)
    // {
    // Item item = new Item();
    // item.type = "Number";
    // item.name = AbstractConfigParser.GetValidSlaveName(device.getString(MBusConfigKey.NAME)) + "__"
    // + registerMap.getJSONObject(j).getString(MBusConfigKey.DESCRIPTION).replaceAll("\\s", "_");
    // item.label = "";
    // item.multiplyFactor = Double.valueOf(registerMap.getJSONObject(j).getString(MBusConfigKey.MULTIPLY));
    // item.patternModifier = String.format("%s(%.2f):", "MULTIPLY", item.multiplyFactor);
    // item.pattern = "%s";
    // item.unit = registerMap.getJSONObject(j).getString(MBusConfigKey.UNIT);
    // item.icon = "number";
    // item.groups = new ArrayList<String>(
    // Arrays.asList(AbstractConfigParser.GetValidSlaveName(device.getString(MBusConfigKey.NAME))));
    // item.tags = new ArrayList<>();
    // item.bindingName = AbstractConfigParser
    // .ParseInterfaceType(device.getString(MBusConfigKey.INTERFACE_TYPE))[0];
    // item.bindingConfig = "";
    //
    // items.add(item);
    // }
    // }
    //
    // return items;
    // }

    // Workaround for MBus Binding because it conflicts with Modbus Binding
    @Override
    public ArrayList<Item> Parse(JSONArray jarray) throws JSONException
    {
        ArrayList<Item> items = new ArrayList<>();

        for (int i = 0; i < jarray.length(); i++)
        {
            JSONObject device = jarray.getJSONObject(i);
            JSONArray registerMap = device.getJSONArray(WMBusConfigKey.REGISTER_MAP);

            for (int j = 0; j < registerMap.length(); j++)
            {
                JSONObject register = registerMap.getJSONObject(j);

                Item item = new Item();
                item.type = "Number";
                item.name = AbstractConfigParser.GetValidSlaveName(String.format("%s__%s",
                        device.getString(WMBusConfigKey.NAME), register.getString(WMBusConfigKey.DESCRIPTION)));
                item.label = "";
                item.multiplyFactor = Double.valueOf(register.getString(WMBusConfigKey.MULTIPLY));
                item.patternModifier = String.format("%s(%.2f):", "MULTIPLY", item.multiplyFactor);
                item.pattern = "%s";
                item.unit = register.getString(WMBusConfigKey.UNIT);

                String jsFilename = "pulsecounter.js";
                String offset = register.getString(WMBusConfigKey.OFFSET);
                item.stateFormat = offset.equals("")
                        ? String.format("%s(%.3f):%s%s", "MULTIPLY", item.multiplyFactor, item.pattern,
                                item.unit.equals("") ? "" : String.format(" %s", item.unit))
                        : String.format("%s(%s):%s %s %s%s", "JS", jsFilename, item.pattern, item.multiplyFactor,
                                offset, item.unit.equals("") ? "" : String.format(" %s", item.unit));

                item.icon = "";
                item.groups = new ArrayList<String>(
                        Arrays.asList(AbstractConfigParser.GetValidSlaveName(device.getString(WMBusConfigKey.NAME))));
                item.tags = new ArrayList<>();
                item.bindingName = "mqtt";
                item.bindingConfig = String.format("<[%s:%s:%s:%s]", "mosquitto",
                        String.format("%s/%s/%s", "ucm/devices", item.groups.get(0), item.name), "state",
                        "JSONPATH($.value)");

                items.add(item);
            }
        }

        return items;
    }
}
