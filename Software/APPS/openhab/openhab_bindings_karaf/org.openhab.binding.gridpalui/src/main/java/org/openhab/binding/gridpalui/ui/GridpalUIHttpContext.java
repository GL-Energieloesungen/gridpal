package org.openhab.binding.gridpalui.ui;

import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.http.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GridpalUIHttpContext implements HttpContext {
    Logger logger = LoggerFactory.getLogger(GridpalUIHttpContext.class);

    @Override
    public boolean handleSecurity(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return true;
    }

    @Override
    public URL getResource(String s) {

        if (s.equals("/home")) {
            s = "/index.html";
        } else if (s.equals("/internet")) {
            s = "/index.html";
        } else if (s.equals("/devicemanager")) {
            s = "/index.html";
        } else if (s.equals("/live-data")) {
            s = "/index.html";
        } else if (s.equals("/wifi")) {
            s = "/index.html";
        } else if (s.equals("/")) {
            s = "/index.html";
        }
        URL url = null;

        try {
            Path path = Paths.get("/usr/share/openhab2/gridpal.ui-1.0.0/dist" + s);
            URI uri = path.toUri();
            logger.debug("GET URI : " + uri);

            url = uri.toURL();
            // logger.debug("GET URL : " + url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return url;
    }

    @Override
    public String getMimeType(String s) {
        return null;
    }
}