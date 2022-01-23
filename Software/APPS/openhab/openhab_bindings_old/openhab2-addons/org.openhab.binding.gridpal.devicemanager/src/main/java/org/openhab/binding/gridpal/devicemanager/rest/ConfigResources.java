
package org.openhab.binding.gridpal.devicemanager.rest;





import org.eclipse.smarthome.core.auth.Role;
import org.json.JSONObject;
import org.openhab.binding.gridpal.devicemanager.manager.ConfigManager;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;




/**
 * This class provides the interfaces to manipulate OSGi runtime configuration via REST API.
 * @author      Md. Farhabi Helal Ayon
 * @since       1.0
 */
@Path(ConfigResources.PATH_CONFIG_RESOURCES)
@RolesAllowed({Role.ADMIN})
@Component(service = {ConfigResources.class})
public class ConfigResources
{
    private final Logger logger = LoggerFactory.getLogger(ConfigResources.class);

    private JSONObject status = new JSONObject();

    /**
     * The URI path to this resource
     */
    public static final String PATH_CONFIG_RESOURCES = "config";

    @Context
    private UriInfo uriInfo;

    /**
     * Deletes the configuration of a binding from the OSGi runtime.
     * @param   name symbolic name of the binding
     * @return  the status of the request. In case of errors, produces server error message.
     */
    @GET
    @Path("/delete/{name : [\\w\\s\\.]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response DeleteConfig(@PathParam("name") String name)
    {
        logger.debug("\n\n[ GET ] : '{}'\n", uriInfo.getPath());

        try
        {
            ConfigManager.GetInstance().Delete(name);

            return Response.ok(status.put("status", "success").toString()).build();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return Response.serverError().build();
    }
}
