
package org.openhab.binding.mbus.internal;



import org.apache.commons.lang.StringUtils;
import org.openhab.binding.mbus.MBusBindingProvider;
import org.openhab.core.items.Item;
import org.openhab.core.library.items.NumberItem;
import org.openhab.model.item.binding.AbstractGenericBindingProvider;
import org.openhab.model.item.binding.BindingConfigParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public class MBusGenericBindingProvider extends AbstractGenericBindingProvider implements MBusBindingProvider
{
    static final Logger logger = LoggerFactory.getLogger(MBusGenericBindingProvider.class);

    public static final String BINDING_TYPE = "mbus";

    @Override
    public String getBindingType()
    {
        return BINDING_TYPE;
    }

    @Override
    public void validateItemType(Item item, String bindingConfig) throws BindingConfigParseException
    {
        if (item.getClass() == NumberItem.class)
        {
            return;
        }

        throw new BindingConfigParseException("ItemTypeError: Item should be of 'Number' type.");
    }

    @Override
    public MBusBindingConfig getConfig(String itemName)
    {
        return (MBusBindingConfig) bindingConfigs.get(itemName);
    }

    // @Override
    // public void processBindingConfiguration(String context, Item item, String bindingConfig)
    // throws BindingConfigParseException
    // {
    // logger.debug("\n\n[ processBindingConfiguration ]\n\n".toUpperCase());
    // logger.debug("\n\n[ bindingConfig ] : {}", bindingConfig);
    //
    // if (bindingConfig != null)
    // {
    // super.processBindingConfiguration(context, item, bindingConfig);
    // MBusBindingConfig config = parseBindingConfiguration(item, bindingConfig);
    // addBindingConfig(item, config);
    // }
    // }

    @Override
    public void removeConfigurations(String context)
    {
        super.removeConfigurations(context);
    }

    private MBusBindingConfig parseBindingConfiguration(Item item, String bindingConfig)
            throws BindingConfigParseException
    {
        String msg = null;

        if (bindingConfig != null)
        {
            if (StringUtils.countMatches(bindingConfig, "=") == 1)
            {
                String[] chunks = bindingConfig.split("=");

                if (chunks[0] == "address")
                {
                    return new MBusBindingConfig(item, chunks[1]);
                }
                else
                {
                    msg = "The key 'address' could not be found.";
                }
            }
            else
            {
                msg = "Configuration contains multiple '='.";
            }
        }
        else
        {
            msg = "Configuration can not be null.";
        }

        throw new BindingConfigParseException("ConfigurationError : " + msg);
    }
}
