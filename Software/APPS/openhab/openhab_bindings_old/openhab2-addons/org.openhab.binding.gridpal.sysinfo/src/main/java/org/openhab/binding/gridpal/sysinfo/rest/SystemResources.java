
package org.openhab.binding.gridpal.sysinfo.rest;



import java.util.ArrayList;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.eclipse.smarthome.core.auth.Role;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openhab.binding.gridpal.sysinfo.agent.Agent;
import org.openhab.binding.gridpal.sysinfo.internal.SystemUtility;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





@Path(SystemResources.PATH_SYSTEM_RESOURCES)
@RolesAllowed({ Role.ADMIN })
@Component(service = { SystemResources.class })
public class SystemResources
{
    private Agent agent = new Agent();
    private final Logger logger = LoggerFactory.getLogger(SystemResources.class);

    private SystemUtility utility = new SystemUtility();

    /** The URI path to this resource */
    public static final String PATH_SYSTEM_RESOURCES = "system";

    @Context
    private UriInfo uriInfo;

    @GET
    @Path("/resources")
    @RolesAllowed({ Role.USER, Role.ADMIN })
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetResources()
    {
        logger.debug("\n\n[ GET ] : '{}'\n", uriInfo.getPath());

        String[] command = { "/bin/bash", "-c", "" };
        JSONObject response = new JSONObject();

        try
        {
            // CPU
            String cpu = utility.GetCPU();

            // RAM
            String ram = utility.GetRAM();

            // LAN
            ArrayList<String> lans = utility.GetLANs();
            JSONArray jsonLANs = new JSONArray();

            for (String lan : lans)
            {
                JSONObject jsonLAN = new JSONObject();

                // Device
                jsonLAN.put("device", lan);
                // TX
                command[2] = "cat /sys/class/net/" + lan + "/statistics/tx_bytes";
                jsonLAN.put("tx", agent.Execute(command).trim());
                // RX
                command[2] = "cat /sys/class/net/" + lan + "/statistics/rx_bytes";
                jsonLAN.put("rx", agent.Execute(command).trim());

                jsonLANs.put(jsonLAN);
            }

            // WiFi
            ArrayList<String> wifis = utility.GetWiFis();
            JSONArray jsonWiFis = new JSONArray();

            for (String wifi : wifis)
            {
                JSONObject jsonWiFi = new JSONObject();

                // Device
                jsonWiFi.put("device", wifi);
                // TX
                command[2] = "cat /sys/class/net/" + wifi + "/statistics/tx_bytes";
                jsonWiFi.put("tx", agent.Execute(command).trim());
                // RX
                command[2] = "cat /sys/class/net/" + wifi + "/statistics/rx_bytes";
                jsonWiFi.put("rx", agent.Execute(command).trim());

                // Frequency
                String frequency = utility.GetWiFiFrequency(wifi);

                // command[2] = "iwconfig " + wifi + " | grep Frequency | awk '{print $2}' | cut
                // -f2 -d':'";
                // jsonWiFi.put("frequency", agent.Execute(command).trim() + "GHz");
                jsonWiFi.put("frequency", frequency);

                jsonWiFis.put(jsonWiFi);
            }

            response.put("cpu", cpu);
            response.put("ram", ram);
            response.put("ethernet", jsonLANs);
            response.put("wifi", jsonWiFis);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        logger.debug("\n\n[ RESPONSE ] : {}\n", response.toString());

        return Response.ok(response.toString()).build();
    }

    @GET
    @Path("/interfaces")
    @RolesAllowed({ Role.USER, Role.ADMIN })
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetInterfaces()
    {
        JSONObject json = new JSONObject();

        try
        {
            json.put("lan", true);
            json.put("wlan24", true);
            json.put("wlan50", true);
            json.put("usb1", true);
            json.put("usb2", false);
            json.put("usb3", false);
            json.put("usb4", false);
            json.put("usb5", false);
            json.put("uart", false);

            return Response.ok(json.toString()).build();
        }
        catch (Exception e)
        {
            e.printStackTrace();

            return Response.serverError().build();
        }
    }

    @GET
    @Path("/buses")
    @RolesAllowed({ Role.USER, Role.ADMIN })
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetBuses()
    {

        JSONObject json = new JSONObject();

        try
        {
            json.put("modbus-rs485", true);
            json.put("mbus", true);
            json.put("wireless-mbus", true);
            json.put("knx-eib", true);
            json.put("bluetooth", false);

            return Response.ok(json.toString()).build();
        }
        catch (Exception e)
        {
            e.printStackTrace();

            return Response.serverError().build();
        }
    }

    @GET
    @Path("/resources/cpu")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetCPU() throws JSONException
    {
        JSONObject json = new JSONObject();
        json.put("cpu", utility.GetCPU());
        return Response.ok(json.toString()).build();
    }

    @GET
    @Path("/resources/ram")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetRAM() throws JSONException
    {
        JSONObject json = new JSONObject();
        json.put("ram", utility.GetRAM());
        return Response.ok(json.toString()).build();
    }

    @GET
    @Path("/resources/lans")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetLANs() throws JSONException
    {
        JSONObject json = new JSONObject();
        json.put("lans", utility.GetLANs());
        return Response.ok(json.toString()).build();
    }

    @GET
    @Path("/resources/wifis")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetWiFis() throws JSONException
    {
        JSONObject json = new JSONObject();
        json.put("wifis", utility.GetWiFis());
        return Response.ok(json.toString()).build();
    }

    @GET
    @Path("/internet/status")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetInternetStatus()
    {
        JSONObject json = new JSONObject();

        try
        {
            // local interface
            JSONObject intface = new JSONObject();
            intface.put("id", "F8B568A");
            intface.put("ip-address", utility.GetIPAllAddresses());
            intface.put("system-version", "v0.01");

            // router interface
            JSONObject router = new JSONObject();
            router.put("active-since", utility.GetUptime());
            router.put("ddns-ip-address", "192.168.255.255");

            json.put("interface", intface);
            json.put("router", router);

            return Response.ok(json.toString()).build();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/serials")
    @RolesAllowed({ Role.USER, Role.ADMIN })
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetSerialPorts()
    {
        JSONArray jarray = new JSONArray(utility.GetSerialPorts());

        return Response.ok(jarray.toString()).build();

    }

}
