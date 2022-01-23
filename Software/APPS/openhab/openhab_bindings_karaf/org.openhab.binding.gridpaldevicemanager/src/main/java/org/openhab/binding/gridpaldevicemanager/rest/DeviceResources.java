
package org.openhab.binding.gridpaldevicemanager.rest;

import java.io.IOException;

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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.eclipse.smarthome.core.auth.Role;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openhab.binding.gridpaldevicemanager.manager.DeviceManager;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path(DeviceResources.PATH_DEVICE_RESOURCES)
@RolesAllowed({ Role.ADMIN })
@Component(service = { DeviceResources.class })
public class DeviceResources {
    private final Logger logger = LoggerFactory.getLogger(DeviceResources.class);

    /**
     * The URI path to this resource
     */
    public static final String PATH_DEVICE_RESOURCES = "devices";

    private JSONObject status = new JSONObject();

    private DeviceManager manager = new DeviceManager();

    @Context
    private UriInfo uriInfo;

    @GET
    @RolesAllowed({ Role.USER, Role.ADMIN })
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetDevices() {
        logger.debug("\n\n[ GET ] : '{}'\n", uriInfo.getPath());

        try {
            JSONArray jarray = manager.GetDevices();
            logger.debug("[ JSON ] : " + jarray.toString());

            return Response.ok(jarray.toString()).build();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return Response.serverError().build();
    }

    @GET
    @Path("/{name: [\\w\\s&@%]+}")
    @RolesAllowed({ Role.USER, Role.ADMIN })
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetDevice(@PathParam("name") String name) {
        logger.debug("\n\n[ GET ] : '{}'\n", uriInfo.getPath());
        logger.debug("\n\n[ REQUEST ] : name={}\n", name.toString());

        try {
            JSONObject json = manager.GetDevice(name.replaceAll("\"", ""));
            logger.debug("[ JSON ] : " + json.toString());

            return Response.ok(json.toString()).build();
        } catch (JSONException | IOException | NullPointerException e) {
            e.printStackTrace();
        }

        return Response.serverError().build();
    }

    @POST
    @Path("/add")
    @RolesAllowed({ Role.USER, Role.ADMIN })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response AddDevice(String device) {
        logger.debug("\n\n[ POST ] : '{}'\n", uriInfo.getPath());
        logger.debug("\n\n[ REQUEST JSON ] :\n{}\n", device.toString());

        try {
            if (manager.AddDevice(new JSONObject(device))) {
                logger.debug("[ UPDATED JSON ] :\n{}\n\n", manager.GetDevices().toString());

                return Response.ok(status.put("status", "success").toString()).build();
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return Response.serverError().build();
    }

    @POST
    @Path("/update")
    @RolesAllowed({ Role.USER, Role.ADMIN })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response UpdateDevice(String params) {
        logger.debug("\n\n[ POST ] : '{}'\n", uriInfo.getPath());
        logger.debug("\n\n[ REQUEST JSON ] :\n{}\n", params.toString());

        try {
            if (manager.UpdateDevice(new JSONObject(params))) {
                logger.debug("[ UPDATED JSON ] :\n{}\n\n", manager.GetDevices().toString());

                return Response.ok(status.put("status", "success").toString()).build();
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return Response.serverError().build();
    }

    @GET
    @Path("remove/{name: [\\w\\s&@%]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response RemoveDevice(@PathParam("name") String name) {
        logger.debug("\n\n[ GET ] : '{}'\n", uriInfo.getPath());
        logger.debug("\n\n[ REQUEST ] : name={}\n", name.toString());

        try {
            manager.RemoveDevice(name.replaceAll("\"", ""));

            return Response.ok(status.put("status", "success").toString()).build();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return Response.serverError().build();
    }

    @GET
    @Path("/remove/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response RemoveDevices() {
        logger.debug("\n\n[ GET ] : '{}'\n", uriInfo.getPath());

        try {
            manager.RemoveDevices();

            return Response.ok(status.put("status", "success").toString()).build();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return Response.serverError().build();
    }

    @GET
    @Path("/export/items/{item: [\\w\\s&@%_]+}")
    @Produces("application/octet-stream")
    public Response ExportDevice(@PathParam("item") String item, @QueryParam("starttime") String fromDate,
            @QueryParam("endtime") String toDate) {
        logger.debug("\n\n[ GET ] : '{}'\n", uriInfo.getPath());

        String data = manager.ExportDevice(item, fromDate, toDate);

        try {
            if (data == null) {
                return Response.status(Status.NOT_FOUND).build();
            } else if (data.isEmpty()) {
                return Response.noContent().build();
            } else {
                String filename = item + ".csv";
                return Response.ok(data).header("content-disposition", "attachment; filename=\"" + filename + "\"")
                        .build();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return Response.serverError().build();
    }
}
