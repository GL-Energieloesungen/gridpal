
package org.openhab.binding.gridpal.devicemanager.device;



import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;




/**
 * This class is the base class of the config parser classes.
 * @author  Md. Farhabi Helal Ayon
 * @since   0.1.0
 */
public abstract class AbstractConfigParser
{
    public abstract JSONObject ToJSON(String filepath) throws JSONException, IOException;

    public abstract String ToConfig(JSONObject json);

    public static String GetValidSlaveName(String name)
    {
        return name.replaceAll("[\\s\\&\\<\\(\\[\\{\\\\\\^\\-\\=\\$\\!\\|\\]\\}\\)\\?\\*\\+\\.\\>]+", "_")
                .toLowerCase();
    }

    public static String[] ParseInterfaceType(String str)
    {
        return str.replaceAll(" ", "").toLowerCase().split("-");
    }

}
