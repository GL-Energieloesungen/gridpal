package org.openhab.binding.gridpalmodemapi.internal;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.eclipse.smarthome.core.auth.Role;
import org.json.JSONObject;
import org.openhab.binding.gridpalmodemapi.internal.HStatusE3372h.ModemInfo;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path(RESTResources.PATH)
@RolesAllowed({ Role.ADMIN })
@Component(service = { RESTResources.class })
public class RESTResources {
    private final Logger logger = LoggerFactory.getLogger(RESTResources.class);

    /** The URI path to this resource */
    public static final String PATH = "modeminfo";

    @Context
    private UriInfo uriInfo;

    @GET
    @Path("/all")
    @RolesAllowed({ Role.USER, Role.ADMIN })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        JSONObject jo = new JSONObject();
        HStatusE3372h modem = new HStatusE3372h("192.168.8.1");
        ModemInfo modemInfo = modem.getModemInfo();
        jo.put("modem", modemInfo);
        return Response.ok(jo.toString()).build();
    }
}
