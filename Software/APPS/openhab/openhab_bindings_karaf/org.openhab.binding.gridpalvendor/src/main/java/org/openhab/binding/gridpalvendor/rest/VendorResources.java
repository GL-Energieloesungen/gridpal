package org.openhab.binding.gridpalvendor.rest;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.smarthome.core.auth.Role;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openhab.binding.gridpalvendor.rest.utils.VendorUtility;
import org.osgi.service.component.annotations.Component;

@Path(VendorResources.PATH_VENDOR_RESOURCES)
@RolesAllowed(Role.ADMIN)
@Component(service = { VendorResources.class })
public class VendorResources {
    public static final String PATH_VENDOR_RESOURCES = "vendors";

    private final String dbPath = "/etc/openhab2/db/vendors.db";

    private VendorUtility utility = new VendorUtility(dbPath);

    public VendorResources() {
    }

    @GET
    @Path("/interface")
    @RolesAllowed({ Role.USER, Role.ADMIN })
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetInterfaces() {
        JSONObject json = utility.GetInterfaces();

        if (json != null) {
            return Response.ok(json.toString()).build();
        }

        return Response.serverError().build();
    }

    @GET
    @Path("/scan")
    @RolesAllowed({ Role.ADMIN, Role.USER })
    @Produces(MediaType.APPLICATION_JSON)
    public Response Scan(@QueryParam("id_interface") String id_interface) {
        String json = utility.Scan(id_interface);

        if (json != null) {
            return Response.ok(json).build();
        }

        return Response.serverError().build();
    }

    @GET
    @Path("/manufacturer")
    @RolesAllowed({ Role.USER, Role.ADMIN })
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetManufacturers(@QueryParam("id_interface") String iface) {
        JSONObject json = utility.GetManufacturers(iface);

        if (json != null) {
            return Response.ok(json.toString()).build();
        }

        return Response.serverError().build();
    }

    @GET
    @Path("/device")
    @RolesAllowed({ Role.USER, Role.ADMIN })
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetDevices(@QueryParam("id_interface") String iface,
            @QueryParam("id_manufacturer") String manufacturer) {
        JSONObject json = utility.GetDevices(manufacturer, iface);

        if (json != null) {
            return Response.ok(json.toString()).build();
        }

        return Response.serverError().build();
    }

    @GET
    @Path("/device/config")
    @RolesAllowed({ Role.USER, Role.ADMIN })
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetConfig(@QueryParam("id") String id) {
        JSONObject json = utility.GetConfig(id);

        if (json != null) {
            return Response.ok(json.toString()).build();
        }

        return Response.serverError().build();
    }

    @GET
    @Path("/registermap")
    @RolesAllowed({ Role.USER, Role.ADMIN })
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetRegisterMap(@QueryParam("id_device") String id) {
        JSONArray jarray = utility.GetRegisterMap(id);

        if (jarray != null) {
            return Response.ok(jarray.toString()).build();
        }

        return Response.serverError().build();
    }
}