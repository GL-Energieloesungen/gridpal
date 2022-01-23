
package org.openhab.binding.gridpaldevicemanager.device;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class AbstractConfigParser {
    public abstract JSONObject ToJSON(String filepath) throws JSONException, IOException;

    public abstract String ToConfig(JSONObject json);

    public static String GetValidSlaveName(String name) {
        return name.replaceAll("[\\s\\&\\<\\(\\[\\{\\\\\\^\\-\\=\\$\\!\\|\\]\\}\\)\\?\\*\\+\\.\\>]+", "_")
                .toLowerCase();
    }

    public static String[] ParseInterfaceType(String str) {
        return str.replaceAll(" ", "").toLowerCase().split("-");
    }

}
