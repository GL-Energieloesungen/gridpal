package org.openhab.ui.gridpal;





import org.osgi.service.http.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;





public class GridpalUIHttpContext implements HttpContext
{
    Logger logger = LoggerFactory.getLogger(GridpalUIHttpContext.class);

    @Override
    public boolean handleSecurity(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
    {
        return true;
    }

    @Override
    public URL getResource(String s)
    {
        s = s.equals("/") ? "/index.html" : s;

        URL url = null;

        try
        {
            Path path = Paths.get(ConfigManager.GetConfig().RESOURCES_DIR + s);
            URI uri = path.toUri();
            logger.debug("GET URI : " + uri);

            url = uri.toURL();
//            logger.debug("GET URL : " + url);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return url;
    }

    @Override
    public String getMimeType(String s)
    {
        return null;
    }
}
