package org.openhab.binding.gridpalrest;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.eclipse.smarthome.core.auth.Role;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path(RESTResources.PATH)
@RolesAllowed({ Role.ADMIN })
@Component(service = { RESTResources.class })
public class RESTResources {
    private final Logger logger = LoggerFactory.getLogger(RESTResources.class);

    /** The URI path to this resource */
    public static final String PATH = "gridpalapi";

    @Context
    private UriInfo uriInfo;

    @GET
    @Path("/get/{name}")
    @RolesAllowed({ Role.USER, Role.ADMIN })
    @Produces(MediaType.APPLICATION_JSON)
    public Response MethodGET(@PathParam("name") String name, @QueryParam("q") String query) {
        JSONObject jo = new JSONObject();
        jo.put("method", "GET");
        jo.put("name", name);

        if (!query.equals("")) {
            jo.put("query", query);
        }
        return Response.ok(jo.toString()).build();
    }

    @POST
    @Path("/post")
    @RolesAllowed({ Role.USER, Role.ADMIN })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response MethodPOST(String params) {
        JSONObject jo = new JSONObject(params);
        jo.put("method", "POST");
        return Response.ok(jo.toString()).build();
    }
}
