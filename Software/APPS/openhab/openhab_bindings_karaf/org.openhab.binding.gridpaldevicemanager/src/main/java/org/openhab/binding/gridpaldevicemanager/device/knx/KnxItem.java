package org.openhab.binding.gridpaldevicemanager.device.knx;

import org.openhab.binding.gridpaldevicemanager.item.Item;

public class KnxItem extends Item {
    public String deviceName;
    public String autoRefresh;
    public String address;
    public String description;
    public String manufacturer;
    public String interfaceType;

    public KnxItem() {

    }

    public KnxItem(Item item) {
        super(item);
    }
}
