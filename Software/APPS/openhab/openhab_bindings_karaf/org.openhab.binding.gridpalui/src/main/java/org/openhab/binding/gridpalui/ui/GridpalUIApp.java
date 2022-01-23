package org.openhab.binding.gridpalui.ui;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This component registers the Gridpal UI Webapp.
 *
 * @author Md. Farhabi Helal Ayon
 */
@Component()
public class GridpalUIApp {

    public static final String WEBAPP_ALIAS = "/gridpalui";
    public GridpalUIHttpContext gridpalUIHttpContext = new GridpalUIHttpContext();

    private final Logger logger = LoggerFactory.getLogger(GridpalUIApp.class);

    protected HttpService httpService;

    protected void activate(BundleContext bundleContext) {
        try {
            httpService.registerResources(WEBAPP_ALIAS, "/", gridpalUIHttpContext);
            logger.info("Started Gridpal UI at " + WEBAPP_ALIAS);
        } catch (NamespaceException e) {
            logger.error("Error during servlet startup", e);
        }
    }

    protected void deactivate(ComponentContext componentContext) {
        httpService.unregister(WEBAPP_ALIAS);
        logger.info("Stopped Gridpal UI");
    }

    @Reference(policy = ReferencePolicy.STATIC)
    protected void setHttpService(HttpService httpService) {
        this.httpService = httpService;
    }

    protected void unsetHttpService(HttpService httpService) {
        this.httpService = null;
    }
}