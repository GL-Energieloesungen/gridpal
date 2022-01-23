package org.openhab.ui.gridpal;





import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;





public class ConfigManager
{
    public static final Path GRIDPALUI_CFG = Paths.get("/etc/openhab2/services/gridpalui.cfg");

    public static final Pattern patternCfg = Pattern.compile("(\\w+)\\s*\\=\\s*\\\"(.*)\\\"");

    private static final Logger logger = LoggerFactory.getLogger(ConfigManager.class);


    static
    {
        Create();
    }

    public static Config GetConfig()
    {
        Config config = new Config();

        try
        {
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
                    switch (matcher.group(1))
                    {
                        case ConfigKey.RESOURCES_DIR:
                            config.RESOURCES_DIR = matcher.group(2).isEmpty() ? Config.DEFAULT_RESOURCES_DIR : matcher.group(2);
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

    public static void Create()
    {
        if (!Files.exists(GRIDPALUI_CFG))
        {
            try
            {
                // does not throw exception if already exists
                Files.createDirectories(GRIDPALUI_CFG.getParent());

                // throws exception if file already exists
                Files.createFile(GRIDPALUI_CFG);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            logger.debug(String.format("%s exists. Skipping file creation.", GRIDPALUI_CFG.getFileName()));
        }
    }
}
