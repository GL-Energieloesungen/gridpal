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
package org.openhab.binding.gridpal.ui;





import org.openhab.ui.dashboard.DashboardTile;
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
 * @author  Md. Farhabi Helal Ayon
 * @since   0.1.0
 */
@Component()
public class GridpalUIApp implements DashboardTile
{

    public static final String WEBAPP_ALIAS = "/gridpalui";
    public GridpalUIHttpContext gridpalUIHttpContext = new GridpalUIHttpContext();

    private final Logger logger = LoggerFactory.getLogger(GridpalUIApp.class);

    protected HttpService httpService;


    protected void activate(BundleContext bundleContext)
    {
        try
        {
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


    @Override
    public String getName()
    {
        return "Gridpal UI";
    }

    @Override
    public String getUrl()
    {
        return "../gridpalui/index.html";
    }

    @Override
    public String getOverlay()
    {
        return null;
    }

    @Override
    public String getImageUrl()
    {
        return "../gridpalui/assets/images/dashboardtile.png";
    }
}
