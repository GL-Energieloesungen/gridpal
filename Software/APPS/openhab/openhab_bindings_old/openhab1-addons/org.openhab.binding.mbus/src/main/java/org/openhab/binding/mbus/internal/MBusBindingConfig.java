
package org.openhab.binding.mbus.internal;



import org.openhab.core.binding.BindingConfig;
import org.openhab.core.items.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public class MBusBindingConfig implements BindingConfig
{
    private static final Logger logger = LoggerFactory.getLogger(MBusBindingConfig.class);

    private String itemName = null;
    private String address = null;

    public String getItemName()
    {
        return itemName;
    }

    public String getAddress()
    {
        return address;
    }

    public MBusBindingConfig(Item item, String address)
    {
        itemName = item.getName();
        this.address = address;
    }
}
