
package org.openhab.binding.mbus.internal;



import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.openhab.binding.mbus.MBusBindingProvider;
import org.openhab.core.binding.AbstractActiveBinding;
import org.openhab.core.types.State;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public class MBusBinding extends AbstractActiveBinding<MBusBindingProvider> implements ManagedService
{

    public static final long DEFAULT_POLL_INTERVAL = 5000;

    private long pollInterval = DEFAULT_POLL_INTERVAL;

    private static final String UDP_PREFIX = "udp";
    private static final String TCP_PREFIX = "tcp";
    private static final String SERIAL_PREFIX = "serial";

    private static final String VALID_CONFIG_KEYS = "name|connection|id|start|devicetype|manufacturer|unit|description|multiply|interface-type";
    private static final Pattern EXTRACT_MODBUS_CONFIG_PATTERN = Pattern.compile(
            "^(" + TCP_PREFIX + "|" + UDP_PREFIX + "|" + SERIAL_PREFIX + "|)\\.(.*?)\\.(" + VALID_CONFIG_KEYS + ")$");

    private static Map<String, MBusSlave> mbusSlaves = new ConcurrentHashMap<>();

    private Logger logger = LoggerFactory.getLogger(MBusBinding.class);

    @Override
    public void activate()
    {
        logger.debug("\n\nMBus service has been started.\n\n");
        super.activate();
        setProperlyConfigured(true);

    }

    @Override
    public void deactivate()
    {
        logger.debug("\n\nMBus service has been stopped.\n\n");
    }

    @Override
    public void updated(Dictionary<String, ?> config) throws ConfigurationException
    {
        // logger.debug("\n\n[ UPDATING CONFIG ]\n\n");

        if (config == null)
        {
            logger.debug("\n\n[ CONFIG ] : NULL\n\n");
            return;
        }

        Enumeration<String> keys = config.keys();

        while (keys.hasMoreElements())
        {
            String key = keys.nextElement();
            String value = (String) config.get(key);

            if ("service.pid".equals(key))
            {
                continue;
            }

            Matcher matcher = EXTRACT_MODBUS_CONFIG_PATTERN.matcher(key);

            if (!matcher.matches())
            {
                if ("poll".equals(key))
                {
                    if (StringUtils.isNotBlank(value))
                    {
                        pollInterval = Integer.valueOf(value);
                    }
                }
                else
                {
                    logger.debug(
                            "given mbus-slave-config-key '{}' does not follow the expected pattern or 'serial.<slaveId>.<{}>'",
                            key, VALID_CONFIG_KEYS);
                }
                continue;
            }

            matcher.reset();
            matcher.find();

            String slave = matcher.group(2);

            MBusSlave mbusSlave = mbusSlaves.get(slave);

            if (mbusSlave == null)
            {
                if (matcher.group(1).equals(SERIAL_PREFIX))
                {
                    mbusSlave = new MBusSerialSlave();

                }
                else
                {
                    throw new ConfigurationException(slave, "the given slave type '" + slave + "' is unknown");
                }

                logger.debug("MBus Slave '{}' instanciated", slave);
                mbusSlaves.put(slave, mbusSlave);
            }

            String configKey = matcher.group(3);

            if ("name".equals(configKey))
            {
                mbusSlave.name = value;
            }
            else if ("description".equals(configKey))
            {
                mbusSlave.description = value;
            }
            else if ("manufacturer".equals(configKey))
            {
                mbusSlave.manufacturer = value;
            }
            else if ("connection".equals(configKey))
            {
                if (mbusSlave instanceof MBusSerialSlave)
                {
                    try
                    {
                        ((MBusSerialSlave) mbusSlave).setSerialParameters(value);
                    }
                    catch (NumberFormatException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            else if ("id".equals(configKey))
            {
                mbusSlave.id = Integer.valueOf(value);
            }
            else if ("start".equals(configKey))
            {
                mbusSlave.start = value;
            }
            else if ("unit".equals(configKey))
            {
                mbusSlave.unit = value;
            }
            else if ("multiply".equals(configKey))
            {
                mbusSlave.multiply = Double.valueOf(value);
            }
            else if ("devicetype".equals(configKey))
            {
                mbusSlave.deviceType = value;
            }
            else if ("interface-type".equals(configKey))
            {
                mbusSlave.interfaceType = value;
            }
            else
            {
                throw new ConfigurationException(configKey, "the given configKey '" + configKey + "' is unknown");
            }

            mbusSlaves.put(slave, mbusSlave);
        }

        logger.debug("\n\n[ CONFIG UPDATED ]\n\n");

    }

    @Override
    protected void execute()
    {
        logger.debug("\n\nUpdating MBus items.\n\n");

        MBusSlave.resetUpdatedPool();

        try
        {
            Collection<MBusSlave> slaves = new HashSet<>();

            synchronized (slaves)
            {
                slaves.addAll(mbusSlaves.values());
            }
            for (MBusSlave slave : slaves)
            {
                slave.update(this);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected String getName()
    {
        return "MBus Polling Service";
    }

    @Override
    protected long getRefreshInterval()
    {
        return pollInterval;
    }

    protected void addBindingProvider(MBusBindingProvider provider)
    {
        super.addBindingProvider(provider);
    }

    protected void removeBindingProvider(MBusBindingProvider provider)
    {
        super.removeBindingProvider(provider);
    }

    public void internalUpdateItem(String itemName, State newState)
    {
        eventPublisher.postUpdate(itemName, newState);
    }
}
