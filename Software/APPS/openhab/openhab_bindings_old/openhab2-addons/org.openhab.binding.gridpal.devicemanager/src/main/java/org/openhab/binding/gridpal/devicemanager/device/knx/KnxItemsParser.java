
package org.openhab.binding.gridpal.devicemanager.device.knx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openhab.binding.gridpal.devicemanager.device.AbstractConfigParser;
import org.openhab.binding.gridpal.devicemanager.device.mbus.MBusConfigKey;
import org.openhab.binding.gridpal.devicemanager.item.Item;
import org.openhab.binding.gridpal.devicemanager.item.ItemsParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KnxItemsParser extends ItemsParser
{
    public static final Pattern patternAutoRefresh = Pattern.compile("<\\((\\d+)\\)");
    public static final Pattern patternAddress = Pattern.compile("(\\d+\\/\\d+\\/\\d+)");
    public static final Pattern patternKnxConfig = Pattern
            .compile("(\\<\\((\\d+)\\))?(((\\d+\\.\\d+):)?(\\d+\\/\\d+\\/\\d+))");

    private Logger logger = LoggerFactory.getLogger(KnxItemsParser.class);

    public KnxItemsParser()
    {}

    public static KnxItem ParseConfig(String itemConfig)
    {
        KnxItem item = new KnxItem(ItemsParser.Parse(itemConfig));

        item.deviceName = item.name.split("__")[0];
        item.description = item.name.split("__")[1];
        item.manufacturer = "";
        item.interfaceType = "knx";
        item.autoRefresh = "0";
        item.address = "";

        Matcher matcher = patternKnxConfig.matcher(item.bindingConfig);

        if (matcher.find())
        {
            item.autoRefresh = matcher.group(2) == null ? "0" : matcher.group(2);
            item.address = matcher.group(3) == null ? "" : matcher.group(3);
        }

        return item;
    }

    public static ArrayList<KnxItem> ParseConfig(ArrayList<String> cfgs)
    {
        ArrayList<KnxItem> items = new ArrayList<>();

        for (String cfg : cfgs)
        {
            items.add(ParseConfig(cfg));
        }

        return items;
    }

    @Override
    public ArrayList<Item> Parse(JSONObject json) throws JSONException
    {
        return Parse(json.getJSONArray(MBusConfigKey.SLAVES));
    }

    @Override
    public ArrayList<Item> Parse(JSONArray jarray) throws JSONException
    {
        ArrayList<Item> items = new ArrayList<>();

        for (int i = 0; i < jarray.length(); i++)
        {
            JSONObject slave = jarray.getJSONObject(i);

            JSONArray registerMaps = slave.getJSONArray(KnxConfigKey.REGISTER_MAP);

            for (int j = 0; j < registerMaps.length(); j++)
            {
                KnxItem item = new KnxItem();

                // Knx Specific
                item.deviceName = slave.getString(KnxConfigKey.NAME);
                item.autoRefresh = registerMaps.getJSONObject(j).getString(KnxConfigKey.LENGTH);
                item.address = registerMaps.getJSONObject(j).getString(KnxConfigKey.START);
                item.description = registerMaps.getJSONObject(j).getString(KnxConfigKey.DESCRIPTION);
                item.interfaceType = slave.getString(KnxConfigKey.INTERFACE_TYPE);

                // Generic
                item.type = registerMaps.getJSONObject(j).getString(KnxConfigKey.ITEM_TYPE);
                item.name = AbstractConfigParser
                        .GetValidSlaveName(String.format("%s__%s", item.deviceName, item.description));
                item.label = "";
                item.multiplyFactor = Double.valueOf(registerMaps.getJSONObject(j).getString(MBusConfigKey.MULTIPLY));
                item.pattern = "%s";
                item.unit = registerMaps.getJSONObject(j).getString(KnxConfigKey.UNIT);

                String jsFilename = "pulsecounter.js";
                String offset = registerMaps.getJSONObject(j).getString(KnxConfigKey.OFFSET);
                item.stateFormat = offset.equals("")
                        ? String.format("%s%s", item.pattern,
                                item.unit.equals("") ? "" : String.format(" %s", item.unit))
                        : String.format("%s(%s):%s %s %s%s", "JS", jsFilename, item.pattern, item.multiplyFactor,
                                offset, item.unit.equals("") ? "" : String.format(" %s", item.unit));

                item.icon = "";
                item.groups = new ArrayList<String>(
                        Arrays.asList(new String[] { AbstractConfigParser.GetValidSlaveName(item.deviceName) }));
                item.tags = new ArrayList<>();
                item.bindingName = "knx";
                item.bindingConfig = String.format("%s%s",
                        item.autoRefresh.equals("0") || item.autoRefresh.isEmpty() ? ""
                                : String.format("<(%s)", item.autoRefresh),
                        item.address);

                items.add(item);
            }
        }

        return items;
    }

}
