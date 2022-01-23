package org.openhab.binding.gridpal.ui;





import org.osgi.service.http.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;




/**
 * Enables HTTP service for this binding. Registers resources to the OSGi.
 * @author  Md. Farhabi Helal Ayon
 * @since   0.1.0
 */
public class GridpalUIHttpContext implements HttpContext
{
    private Logger logger = LoggerFactory.getLogger(GridpalUIHttpContext.class);


    /**
     * Handles security issues for the request.
     * @return  true if the request should be serviced, false if the request should not be serviced.
     */
    @Override
    public boolean handleSecurity(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
    {
        return true;
    }

    /**
     * Maps a resource name to a URL.
     * @param   s the name of the requested resource.
     * @return  URL that Http Service can use to read the resource or null if the resource does not exist.
     */
    @Override
    public URL getResource(String s)
    {
        Config config = GetConfig();

        s = GetRoute(s);

        URL url = null;

        try
        {
            Path path = Paths.get(config.resourcesDir + s);
            URI uri = path.toUri();
            logger.debug("GET URI : " + uri);

            url = uri.toURL();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * Maps a name to a MIME type.
     * @param   s determine the MIME type for this name.
     * @return  MIME type (e.g. text/html) of the name or null to indicate that the Http Service should determine the MIME type itself.
     */
    @Override
    public String getMimeType(String s)
    {
        return null;
    }

    private static Config GetConfig()
    {
        return ConfigManager.GetConfig();
    }

    private String GetRoute(String s)
    {
        if (s.equals("/") || s.isEmpty())
        {
            return "/index.html";
        }

        for (String tab : GetConfig().routeEndpoints)
        {
            if (s.equals("/" + tab))
            {
                return "/index.html";
            }
        }

        return s;
    }
}
