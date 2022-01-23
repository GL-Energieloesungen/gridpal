
package org.openhab.binding.gridpaldevicemanager.device.knx;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openhab.binding.gridpaldevicemanager.device.utils.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KnxConfigManager {
    private KnxConfigParser parser = new KnxConfigParser();
    private String filename = null;
    private Logger logger = LoggerFactory.getLogger(KnxConfigManager.class);

    public KnxConfigManager(String filename) {
        this.filename = filename;
    }

    public JSONArray GetDevices() throws JSONException, IOException {
        return parser.ToJSON(this.filename).getJSONArray(KnxConfigKey.SLAVES);
    }

    public JSONObject GetDevice(String name) throws JSONException, IOException {
        JSONObject json = parser.ToJSON(this.filename);

        JSONArray devices = json.getJSONArray(KnxConfigKey.SLAVES);

        for (int i = 0; i < devices.length(); i++) {
            JSONObject device = devices.getJSONObject(i);

            if (device.get(KnxConfigKey.NAME).equals(name)) {
                return device;
            }
        }

        return null;
    }

    public boolean UpdateDevice(JSONObject params) {
        try {
            if (GetDevice(params.getString(KnxConfigKey.NAME)) == null) {
                return false;
            }

            JSONObject json = parser.ToJSON(this.filename);

            JSONArray slaves = json.getJSONArray(KnxConfigKey.SLAVES);

            for (int i = 0; i < slaves.length(); i++) {
                JSONObject slave = slaves.getJSONObject(i);

                if (slave.getString(KnxConfigKey.NAME).equals(params.getString(KnxConfigKey.NAME))) {
                    for (String key : JSONObject.getNames(params)) {
                        slave.put(key, params.get(key));
                    }

                    break;
                }
            }

            Utility.Write(this.filename, parser.ToConfig(json));

            return true;
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean AddDevice(JSONObject newSlave) {
        try {
            JSONObject json = parser.ToJSON(this.filename);
            JSONArray slaves = (JSONArray) json.get(KnxConfigKey.SLAVES);

            for (int i = 0; i < slaves.length(); i++) {
                JSONObject slave = (JSONObject) slaves.get(i);

                if (slave.getString(KnxConfigKey.NAME).equals(newSlave.getString(KnxConfigKey.NAME))) {
                    slaves.remove(i--);
                }
            }

            json.append(KnxConfigKey.SLAVES, newSlave);

            logger.debug("\n\n[ NEW JSON ] :\n{}\n\n", json.toString());

            Utility.Write(this.filename, parser.ToConfig(json));

            return true;
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean RemoveDevice(String name) {
        try {
            if (GetDevice(name) == null) {
                return false;
            }

            JSONObject json = parser.ToJSON(this.filename);
            JSONArray slaves = (JSONArray) json.get(KnxConfigKey.SLAVES);

            for (int i = 0; i < slaves.length(); i++) {
                JSONObject slave = (JSONObject) slaves.get(i);

                if (slave.getString(KnxConfigKey.NAME).equals(name)) {
                    slaves.remove(i--);
                }
            }

            logger.debug("\n\n[ NEW JSON ] :\n{}\n\n", json.toString());

            Utility.Write(this.filename, parser.ToConfig(json));

            return true;
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean RemoveDevices() {
        try {
            JSONObject json = parser.ToJSON(this.filename);
            json.put(KnxConfigKey.SLAVES, new JSONArray());

            logger.debug("\n\n[ NEW JSON ] :\n{}\n\n", json.toString());

            Utility.Write(this.filename, parser.ToConfig(json));

            return true;
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public JSONObject GetConfig() throws JSONException, IOException {
        return parser.ToJSON(this.filename);
    }
}
