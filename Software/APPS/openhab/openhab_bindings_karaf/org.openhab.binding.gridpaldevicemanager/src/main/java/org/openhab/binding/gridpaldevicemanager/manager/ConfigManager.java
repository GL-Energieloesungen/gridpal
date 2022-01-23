package org.openhab.binding.gridpaldevicemanager.manager;

import java.util.logging.Logger;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.slf4j.LoggerFactory;

public final class ConfigManager {
    private static Logger logger = LoggerFactory.getLogger(ConfigManager.class);
    private static ConfigurationAdmin configurationAdmin = null;
    private static ConfigManager instance;

    static {
        instance = new ConfigManager();
    }

    {
        configurationAdmin = GetConfigurationAdmin();
    }

    private ConfigManager() {
    }

    public boolean Delete(String name) {
        try {
            Configuration config;

            try {
                config = configurationAdmin.getConfiguration(name);
            } catch (Exception e) {
                e.printStackTrace();
                configurationAdmin = GetConfigurationAdmin();
                config = configurationAdmin.getConfiguration(name);
            }

            logger.debug("Deleting configuration of bundle : " + name);
            config.delete();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private ConfigurationAdmin GetConfigurationAdmin() {
        BundleContext bc = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
        return (ConfigurationAdmin) bc.getService(bc.getServiceReference(ConfigurationAdmin.class.getName()));
    }

    public static ConfigManager GetInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }

        return instance;
    }
}
