
package org.openhab.binding.gridpaldevicemanager.rest;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.eclipse.smarthome.core.auth.Role;
import org.json.JSONObject;
import org.openhab.binding.gridpaldevicemanager.manager.ConfigManager;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path(ConfigResources.PATH_CONFIG_RESOURCES)
@RolesAllowed({ Role.ADMIN })
@Component(service = { ConfigResources.class })
public class ConfigResources {
    private final Logger logger = LoggerFactory.getLogger(ConfigResources.class);

    private JSONObject status = new JSONObject();

    /**
     * The URI path to this resource
     */
    public static final String PATH_CONFIG_RESOURCES = "config";

    @Context
    private UriInfo uriInfo;

    @GET
    @Path("/delete/{name : [\\w\\s\\.]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response DeleteConfig(@PathParam("name") String name) {
        logger.debug("\n\n[ GET ] : '{}'\n", uriInfo.getPath());

        try {
            ConfigManager.GetInstance().Delete(name);

            return Response.ok(status.put("status", "success").toString()).build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Response.serverError().build();
    }
}
