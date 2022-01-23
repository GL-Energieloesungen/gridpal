
package org.openhab.binding.gridpaldevicemanager.device.modbus;

import org.openhab.binding.gridpaldevicemanager.item.Item;

public class ModbusItem extends Item {

    public String slaveName;
    public int slaveIndex;

    public ModbusItem() {

    }

    // @Override
    // public String toString()
    // {
    // return String.format("%s %s \"%s[%s%s %s]\" %s %s %s {%s}", type, name, label, patternModifier, pattern, unit,
    // icon.isEmpty() ? "" : String.format("<%s>", icon),
    // groups.isEmpty() ? "" : String.format("(%s)", String.join(",", groups)),
    // tags.isEmpty() ? "" : String.format("[%s]", String.join(",", tags)),
    // String.format("%s=\"%s:%d\"", bindingName, slaveName, slaveIndex));
    // }
}
