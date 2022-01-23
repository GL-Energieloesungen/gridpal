
package org.openhab.binding.gridpaldevicemanager.item;

import java.util.ArrayList;

public class Item {
    public String type;
    public String name;
    public String label;
    public String patternModifier;
    public String pattern;
    public String stateFormat;
    public String unit;
    public double multiplyFactor;
    public String icon;
    public ArrayList<String> groups;
    public ArrayList<String> tags;
    public String bindingName;
    public String bindingConfig;

    public Item() {

    }

    public Item(Item item) {
        this.type = item.type;
        this.name = item.name;
        this.label = item.label;
        this.patternModifier = item.patternModifier;
        this.pattern = item.pattern;
        this.unit = item.unit;
        this.multiplyFactor = item.multiplyFactor;
        this.icon = item.icon;
        this.groups = new ArrayList<>(item.groups);
        this.tags = new ArrayList<>(item.tags);
        this.bindingName = item.bindingName;
        this.bindingConfig = item.bindingConfig;
    }

    @Override
    public String toString() {
        return String.format("%s %s \"%s[%s]\" %s %s %s {%s}", type, name, label, stateFormat,
                icon.isEmpty() ? "" : String.format("<%s>", icon),
                groups.isEmpty() ? "" : String.format("(%s)", String.join(",", groups)),
                tags.isEmpty() ? "" : String.format("[%s]", String.join(",", tags)),
                String.format("%s=\"%s\"", bindingName, bindingConfig));
    }
}
