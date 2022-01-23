package org.openhab.binding.gridpal.devicemanager.device.knx;



import org.openhab.binding.gridpal.devicemanager.item.Item;





public class KnxItem extends Item
{
    public String deviceName;
    public String autoRefresh;
    public String address;
    public String description;
    public String manufacturer;
    public String interfaceType;

    public KnxItem()
    {

    }

    public KnxItem(Item item)
    {
        super(item);
    }
}
