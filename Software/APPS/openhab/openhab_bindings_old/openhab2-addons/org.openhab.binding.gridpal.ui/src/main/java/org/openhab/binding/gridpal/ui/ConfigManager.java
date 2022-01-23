package org.openhab.binding.gridpal.ui;





import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




/**
 * Manages the configuration of this binding. It creates new configuration file if none is provided.
 * @author  Md. Farhabi Helal Ayon
 * @since   0.1.0
 */
public class ConfigManager
{
    public static final Path GRIDPALUI_CFG = Paths.get("/etc/openhab2/services/gridpalui.cfg");

    public static final Pattern patternCfg = Pattern.compile("(\\w+)\\s*=\\s*\"(.*)\"");

    private static final Logger logger = LoggerFactory.getLogger(ConfigManager.class);


    static
    {
        Create();
    }

    /**
     * Reads the configuration file.
     * @return  configuration object.
     */
    public static Config GetConfig()
    {
        Config config = new Config();

        try
        {
            Create();

            ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(GRIDPALUI_CFG, StandardCharsets.UTF_8);

            for (String line : lines)
            {
                if (line.trim().startsWith("#") || line.trim().isEmpty())
                {
                    continue;
                }

                Matcher matcher = patternCfg.matcher(line);

                if (matcher.find())
                {
                    String key = matcher.group(1).toLowerCase();
                    String value = matcher.group(2).toLowerCase();

                    switch (key)
                    {
                        case ConfigKey.RESOURCES_DIR:
                            config.resourcesDir = value.isEmpty() ? Config.DEFAULT_RESOURCES_DIR : value;
                            break;

                        case ConfigKey.ROUTE_ENDPOINTS:
                            config.routeEndpoints = value.isEmpty() ? Config.DEFAULT_ROUTE_ENDPOINTS : value.split(",");
                            break;

                        default: break;
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return config;
    }

    /**
     * Writes configuration to file.
     * @param   config configuration string that needs to be written.
     */
    public static void SetConfig(String config)
    {
        try
        {
            Create();

            Files.write(GRIDPALUI_CFG, config.getBytes(StandardCharsets.UTF_8));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Only creates the configuration file with default values if it does not exist. 
     */
    private static void Create()
    {
        if (!Files.exists(GRIDPALUI_CFG))
        {
            logger.info(String.format("'%s' not found.", GRIDPALUI_CFG.getFileName()));

            try
            {
                // does not throw exception if already exists
                Files.createDirectories(GRIDPALUI_CFG.getParent());

                // throws exception if file already exists
                Files.createFile(GRIDPALUI_CFG);
                Files.write(GRIDPALUI_CFG, new Config().toString().getBytes(StandardCharsets.UTF_8));

                logger.info(String.format("'%s' created successfully", GRIDPALUI_CFG.getFileName()));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            logger.info(String.format("'%s' exists. Skipping file creation.", GRIDPALUI_CFG.getFileName()));
        }
    }
}
