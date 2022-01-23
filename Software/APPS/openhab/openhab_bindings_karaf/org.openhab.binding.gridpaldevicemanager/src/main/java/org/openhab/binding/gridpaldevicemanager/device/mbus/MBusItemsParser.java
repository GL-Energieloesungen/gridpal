
package org.openhab.binding.gridpaldevicemanager.device.mbus;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openhab.binding.gridpaldevicemanager.device.AbstractConfigParser;
import org.openhab.binding.gridpaldevicemanager.item.Item;
import org.openhab.binding.gridpaldevicemanager.item.ItemsParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MBusItemsParser extends ItemsParser {
    private Logger logger = LoggerFactory.getLogger(MBusItemsParser.class);

    public MBusItemsParser() {
    }

    @Override
    public ArrayList<Item> Parse(JSONObject json) throws JSONException {
        return Parse(json.getJSONArray(MBusConfigKey.SLAVES));
    }

    // @Override
    // public ArrayList<Item> Parse(JSONArray jarray) throws JSONException
    // {
    // ArrayList<Item> items = new ArrayList<>();
    //
    // for (int i = 0; i < jarray.length(); i++)
    // {
    // JSONObject slave = jarray.getJSONObject(i);
    // JSONArray registerMaps = slave.getJSONArray(MBusConfigKey.REGISTER_MAP);
    //
    // for (int j = 0; j < registerMaps.length(); j++)
    // {
    // Item item = new Item();
    // item.type = "Number";
    // item.name = AbstractConfigParser.GetValidSlaveName(slave.getString(MBusConfigKey.NAME)) + "__"
    // + registerMaps.getJSONObject(j).getString(MBusConfigKey.DESCRIPTION).replaceAll("\\s", "_");
    // item.label = "";
    // item.multiplyFactor = Double.valueOf(registerMaps.getJSONObject(j).getString(MBusConfigKey.MULTIPLY));
    // item.patternModifier = String.format("%s(%.2f):", "MULTIPLY", item.multiplyFactor);
    // item.pattern = "%s";
    // item.unit = registerMaps.getJSONObject(j).getString(MBusConfigKey.UNIT);
    // item.icon = "number";
    // item.groups = new ArrayList<String>(
    // Arrays.asList(AbstractConfigParser.GetValidSlaveName(slave.getString(MBusConfigKey.NAME))));
    // item.tags = new ArrayList<>();
    // item.bindingName = AbstractConfigParser
    // .ParseInterfaceType(slave.getString(MBusConfigKey.INTERFACE_TYPE))[0];
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
    public ArrayList<Item> Parse(JSONArray jarray) throws JSONException {
        ArrayList<Item> items = new ArrayList<>();

        for (int i = 0; i < jarray.length(); i++) {
            JSONObject slave = jarray.getJSONObject(i);
            JSONArray registerMaps = slave.getJSONArray(MBusConfigKey.REGISTER_MAP);

            for (int j = 0; j < registerMaps.length(); j++) {
                Item item = new Item();
                item.type = "Number";
                item.name = AbstractConfigParser.GetValidSlaveName(slave.getString(MBusConfigKey.NAME)) + "__"
                        + registerMaps.getJSONObject(j).getString(MBusConfigKey.DESCRIPTION).replaceAll("\\s", "_");
                item.label = "";
                item.multiplyFactor = Double.valueOf(registerMaps.getJSONObject(j).getString(MBusConfigKey.MULTIPLY));
                // item.patternModifier = String.format("%s(%.2f):", "MULTIPLY", item.multiplyFactor);
                item.pattern = "%s";
                item.unit = registerMaps.getJSONObject(j).getString(MBusConfigKey.UNIT);

                String jsFilename = "pulsecounter.js";
                String offset = registerMaps.getJSONObject(j).getString(MBusConfigKey.OFFSET);
                item.stateFormat = offset.equals("")
                        ? String.format("%s(%.3f):%s%s", "MULTIPLY", item.multiplyFactor, item.pattern,
                                item.unit.equals("") ? "" : String.format(" %s", item.unit))
                        : String.format("%s(%s):%s %s %s%s", "JS", jsFilename, item.pattern, item.multiplyFactor,
                                offset, item.unit.equals("") ? "" : String.format(" %s", item.unit));

                item.icon = "";
                item.groups = new ArrayList<String>(
                        Arrays.asList(AbstractConfigParser.GetValidSlaveName(slave.getString(MBusConfigKey.NAME))));
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
