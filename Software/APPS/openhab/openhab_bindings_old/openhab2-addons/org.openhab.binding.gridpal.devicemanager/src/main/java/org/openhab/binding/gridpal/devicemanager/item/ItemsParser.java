
package org.openhab.binding.gridpal.devicemanager.item;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/**
 * This is an abstract class that contains logic for parsing an item. Specific item parser class must
 * inherit from this class to support item parsing.
 * @author  Md. Farhabi Helal Ayon
 * @since   0.1.0
 */
public abstract class ItemsParser
{
    public static final Pattern patternBinding = Pattern.compile("\\{.*\\}");
    public static final Pattern patternBindingConfig = Pattern.compile("(\\w+)\\s*=\\s*\"(.*)\"");
    // public static final Pattern patternBindingConfig = Pattern.compile("(\\w+)\\s*=\\s*\\\"(\\w+)\\:(\\d)\\\"");
    public static final Pattern patternGroups = Pattern.compile("\\(\\s*(\\w+)(\\s*,\\s*\\w+)*\\s*\\)");
    public static final Pattern patternTags = Pattern.compile("\\[\\s*(\\\"\\w+\\\")(\\s*,\\s*\\\"\\w+\\\")*\\s*\\]");
    public static final Pattern patternIcon = Pattern.compile("\\<\\s*\\w*\\s*\\>");
    // public static final Pattern patternLabelStateFormat = Pattern.compile("\\\"\\s*(\\w*)\\s*\\[.*\\]\\s*\\\"");
    // public static final Pattern patternLabel = Pattern.compile("\\w*");
    public static final Pattern patternLabelStateFormat = Pattern
            .compile("\\\"\\s*\\[\\s*(\\w+\\((\\d*(\\.\\d+)?)\\):)?(%\\d*(\\.\\d+)?\\w+)\\s*(\\w*)\\s*\\]\\s*\\\"");

    public static final Pattern patternStateFormat = Pattern.compile("\\[.*\\]");
    public static final Pattern patternTypeName = Pattern.compile("(\\w+)\\s+(\\w+)");

    private static Logger logger = LoggerFactory.getLogger(ItemsParser.class);

    public ItemsParser()
    {
    }

    /**
     * Parses item definition
     * @param   cfg definition of the item
     * @return  an item object as defined by the definition
     */
    public static Item Parse(String cfg)
    {
        Item item = new Item();
        Matcher matcher;

        // binding config
        matcher = patternBinding.matcher(cfg);
        String bindingConfig = matcher.find() ? matcher.group().replaceAll("[\\{\\s\\}]", "") : "";
        // logger.debug("\n\n" + "bindingConfig : " + item.bindingConfig + "\n");

        // binding name
        matcher = patternBindingConfig.matcher(bindingConfig);
        item.bindingName = matcher.find() ? matcher.group(1) : "";
        item.bindingConfig = item.bindingName.isEmpty() ? "" : matcher.group(2);
        logger.debug("\n\n" + "bindingName : " + item.bindingName + "\n");
        logger.debug("\n\n" + "bindingConfig : " + item.bindingConfig + "\n");

        // tags
        matcher = patternTags.matcher(cfg);
        item.tags = matcher.find()
                ? new ArrayList<>(Arrays.asList(matcher.group().replaceAll("[\\[\"\\s\\]]", "").split(",")))
                : new ArrayList<>();
        logger.debug("\n" + "tags : " + item.tags + "\n");

        // groups
        matcher = patternGroups.matcher(cfg);
        item.groups = matcher.find()
                ? new ArrayList<>(Arrays.asList(matcher.group().replaceAll("[\\(\"\\s\\)]", "").split(",")))
                : new ArrayList<>();
        logger.debug("\n" + "groups : " + item.groups + "\n");

        // icon
        matcher = patternIcon.matcher(cfg);
        item.icon = matcher.find() ? matcher.group().replaceAll("[\\<\\s\\>]", "") : "";
        logger.debug("\n" + "icon : " + item.icon + "\n");

        // label state format
        matcher = patternLabelStateFormat.matcher(cfg);

        if (matcher.find())
        {
            item.multiplyFactor = Double.valueOf(matcher.group(2) == null ? "1" : matcher.group(2));
            item.pattern = matcher.group(4);
            item.unit = matcher.group(6);
        }

        // // label
        // matcher = patternLabel.matcher(str);
        // item.label = matcher.find() ? matcher.group() : "";
        // logger.debug("\n" + "label : " + item.label + "\n");
        //
        // // state format
        // matcher = patternStateFormat.matcher(str);
        // item.pattern = matcher.find() ? matcher.group().replaceAll("[\\[\\]]", "") : "";
        // logger.debug("\n" + "pattern : " + item.pattern + "\n");

        // type
        matcher = patternTypeName.matcher(cfg);
        item.type = matcher.find() ? matcher.group(1) : "";
        logger.debug("\n" + "type : " + item.type + "\n");

        // name
        matcher = patternTypeName.matcher(cfg);
        item.name = matcher.find() ? matcher.group(2) : "";
        logger.debug("\n" + "name : " + item.name + "\n");

        return item;
    }

    public static ArrayList<Item> Parse(ArrayList<String> cfgs)
    {
        ArrayList<Item> items = new ArrayList<>();

        for (String cfg : cfgs)
        {
            items.add(Parse(cfg));
        }

        return items;
    }

    public abstract ArrayList<Item> Parse(JSONObject json) throws JSONException;

    public abstract ArrayList<Item> Parse(JSONArray jarray) throws JSONException;
}
