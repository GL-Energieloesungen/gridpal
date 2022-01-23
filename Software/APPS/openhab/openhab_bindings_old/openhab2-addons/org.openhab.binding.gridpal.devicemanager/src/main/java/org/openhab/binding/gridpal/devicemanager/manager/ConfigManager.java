package org.openhab.binding.gridpal.devicemanager.manager;





import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/**
 * This class manipulates OSGi runtime configuration.
 * @author      Md. Farhabi Helal Ayon
 * @since       1.0
 */
public final class ConfigManager
{
    private static Logger logger = LoggerFactory.getLogger(ConfigManager.class);
    /** Manages all the configurations in the OSGi runtime. */
    private static ConfigurationAdmin configurationAdmin = null;
    private static ConfigManager instance;

    static
    {
        instance = new ConfigManager();
    }

    {
        configurationAdmin = GetConfigurationAdmin();
    }

    private ConfigManager()
    {
    }

    /**
     * Deletes the configuration of a binding. 
     * At first, The reference of the configuration admin is retrieved from the runtime.
     * It is then used to delete the configuration.
     *
     * @param   name symbolic name of the binding.
     * @return  status of the operation.
     */
    public boolean Delete(String name)
    {
        try
        {
            Configuration config;

            try
            {
                config = configurationAdmin.getConfiguration(name);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                configurationAdmin = GetConfigurationAdmin();
                config = configurationAdmin.getConfiguration(name);
            }

            logger.debug("Deleting configuration of bundle : " + name);
            config.delete();

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Fetches the reference of the configuration admin from the runtime.
     * @return  reference of the configuration admin
     */
    private ConfigurationAdmin GetConfigurationAdmin()
    {
        BundleContext bc = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
        return (ConfigurationAdmin) bc.getService(bc.getServiceReference(ConfigurationAdmin.class.getName()));
    }

    public static ConfigManager GetInstance()
    {
        if(instance == null)
        {
            instance = new ConfigManager();
        }

        return instance;
    }
}
