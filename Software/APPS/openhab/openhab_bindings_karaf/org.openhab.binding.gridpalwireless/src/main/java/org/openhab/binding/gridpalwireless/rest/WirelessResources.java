package org.openhab.binding.gridpalwireless.rest;

import javax.annotation.security.RolesAllowed;
import javax.security.auth.login.FailedLoginException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.smarthome.core.auth.Role;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openhab.binding.gridpalWireless.internal.WirelessUtility;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path(WirelessResources.PATH_WIRELESS_RESOURCES)
@RolesAllowed({ Role.ADMIN })
@Component(service = { WirelessResources.class })
public class WirelessResources {
    public static final String PATH_WIRELESS_RESOURCES = "wireless";

    private final Logger logger = LoggerFactory.getLogger(WirelessResources.class);

    private WirelessUtility utility = new WirelessUtility();

    @GET
    @Path("/list")
    @RolesAllowed({ Role.USER, Role.ADMIN })
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetConnections() {
        try {
            JSONArray connections = utility.GetAvailableConnections();

            return Response.ok(connections.toString()).build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Response.serverError().build();
    }

    @GET
    @Path("/status")
    @RolesAllowed({ Role.USER, Role.ADMIN })
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetStatus() {
        JSONObject json;

        try {
            json = utility.GetStatus();

            return Response.ok(json.toString()).build();
        } catch (IllegalStateException e) {
            json = new JSONObject();
            json.put("connected", "false");
            json.put("status", e.getMessage());

            return Response.ok(json.toString()).build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Response.serverError().build();
    }

    @POST
    @Path("/connect")
    @RolesAllowed({ Role.USER, Role.ADMIN })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response Connect(String json) {
        JSONObject request = new JSONObject(json);
        JSONObject response = new JSONObject();

        try {
            if (request.has("password")) {
                utility.Connect(request.getString("ssid"), request.getString("password"));
            } else {
                utility.Connect(request.getString("ssid"));
            }

            response.put("status", "success");

            return Response.ok(response.toString()).build();

        } catch (FailedLoginException | IllegalArgumentException | IllegalStateException e) {
            response.put("status", "failed");
            response.put("reason", "exception");
            response.put("exception",
                    new JSONObject().put("name", e.getClass().getName()).put("message", e.getMessage()));

            return Response.ok(response.toString()).build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Response.serverError().build();
    }

    @GET
    @Path("/disconnect")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Disconnect() throws JSONException {
        JSONObject json = new JSONObject();

        try {
            if (utility.Disconnect()) {
                json.put("status", "success");
            } else {
                json.put("status", "failed");
                json.put("reason", "unknown");
            }

            return Response.ok(json.toString()).build();
        } catch (IllegalStateException e) {
            json.put("status", "failed");
            json.put("reason", "exception");
            json.put("exception", new JSONObject().put("name", e.getClass().getName()).put("message", e.getMessage()));

            return Response.ok(json.toString()).build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Response.serverError().build();
    }

    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response Delete(String json) throws JSONException {
        JSONObject request = new JSONObject(json);
        JSONObject response = new JSONObject();

        try {
            utility.DeleteConnection(request.getString("ssid"));
            response.put("status", "success");
            return Response.ok(response.toString()).build();
        } catch (IllegalStateException e) {
            response.put("status", "failed");
            response.put("reason", "exception");
            response.put("exception",
                    new JSONObject().put("name", e.getClass().getName()).put("message", e.getMessage()));

            return Response.ok(response.toString()).build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Response.serverError().build();
    }
}
