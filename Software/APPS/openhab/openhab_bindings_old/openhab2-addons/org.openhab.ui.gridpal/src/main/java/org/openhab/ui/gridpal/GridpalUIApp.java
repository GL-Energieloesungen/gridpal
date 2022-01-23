/**
 * Copyright (c) 2014,2018 Contributors to the Eclipse Foundation
 * <p>
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 * <p>
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * <p>
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.ui.gridpal;





import org.eclipse.smarthome.io.http.HttpContextFactoryService;
import org.osgi.framework.Bundle;
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
public class GridpalUIApp
{

    public static final String WEBAPP_ALIAS = "/gridpalui";
    public GridpalUIHttpContext gridpalUIHttpContext = new GridpalUIHttpContext();

    private final Logger logger = LoggerFactory.getLogger(GridpalUIApp.class);

    protected HttpService httpService;

    private HttpContextFactoryService httpContextFactoryService;

//    protected void activate(BundleContext bundleContext)
//    {
//        try
//        {
//            Bundle paperuiBundle = bundleContext.getBundle();
//            httpService.registerResources(WEBAPP_ALIAS, "/web-src",
//                    httpContextFactoryService.createDefaultHttpContext(paperuiBundle));
//            logger.info("Started Paper UI at " + WEBAPP_ALIAS);
//        }
//        catch (NamespaceException e)
//        {
//            logger.error("Error during servlet startup", e);
//        }
//    }

    protected void activate(BundleContext bundleContext)
    {
        try
        {
            Bundle paperuiBundle = bundleContext.getBundle();
            httpService.registerResources(WEBAPP_ALIAS, "/",
                    gridpalUIHttpContext);
            logger.info("Started Gridpal UI at " + WEBAPP_ALIAS);
        }
        catch (NamespaceException e)
        {
            logger.error("Error during servlet startup", e);
        }
    }

    protected void deactivate(ComponentContext componentContext)
    {
        httpService.unregister(WEBAPP_ALIAS);
        logger.info("Stopped Gridpal UI");
    }

    @Reference(policy = ReferencePolicy.STATIC)
    protected void setHttpService(HttpService httpService)
    {
        this.httpService = httpService;
    }

    protected void unsetHttpService(HttpService httpService)
    {
        this.httpService = null;
    }
}
