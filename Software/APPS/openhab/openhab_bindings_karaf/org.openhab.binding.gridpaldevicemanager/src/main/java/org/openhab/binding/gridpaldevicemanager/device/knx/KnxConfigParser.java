
package org.openhab.binding.gridpaldevicemanager.device.knx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openhab.binding.gridpaldevicemanager.device.AbstractConfigParser;
import org.openhab.binding.gridpaldevicemanager.device.utils.Utility;

public class KnxConfigParser extends AbstractConfigParser {
    private Pattern patternValidConfigKey = Pattern.compile(String.format("(%s)",
            String.join("|",
                    new String[] { KnxConfigKey.NAME, KnxConfigKey.ADDRESS, Pattern.quote(KnxConfigKey.AUTO_REFRESH),
                            KnxConfigKey.DESCRIPTION, Pattern.quote(KnxConfigKey.INTERFACE_TYPE),
                            KnxConfigKey.ITEM_TYPE, KnxConfigKey.MANUFACTURER, KnxConfigKey.MULTIPLY, KnxConfigKey.UNIT,
                            KnxConfigKey.LENGTH, KnxConfigKey.START, KnxConfigKey.DEVICE_TYPE, KnxConfigKey.OFFSET })));

    private Pattern patternValidJsonlKey = Pattern.compile(String.format("(%s)", KnxConfigKey.SLAVES));

    private Pattern patternConfigFormat = Pattern.compile("([\\w_]+)\\.([\\w\\d_]+)\\.([\\w-]+)\\=(.*)");

    @Override
    public JSONObject ToJSON(String filepath) throws JSONException, IOException {
        JSONObject json = new JSONObject();

        ArrayList<String> lines = Utility.Read(filepath, "#");

        HashMap<String, JSONObject> devices = new HashMap<>();

        for (String line : lines) {
            Matcher matcher = patternConfigFormat.matcher(line);

            if (matcher.matches()) {
                String deviceType = matcher.group(1);
                String name = matcher.group(2);
                String key = matcher.group(3);
                String value = matcher.group(4);

                JSONObject device = devices.get(name);

                if (device == null) {
                    device = new JSONObject();
                    device.put(KnxConfigKey.REGISTER_MAP, new JSONObject());
                }

                if (key.equals(KnxConfigKey.ADDRESS) || key.equals(KnxConfigKey.AUTO_REFRESH)
                        || key.equals(KnxConfigKey.DESCRIPTION) || key.equals(KnxConfigKey.ITEM_TYPE)
                        || key.equals(KnxConfigKey.MULTIPLY) || key.equals(KnxConfigKey.UNIT)
                        || key.equals(KnxConfigKey.OFFSET)) {

                    if (key.equals(KnxConfigKey.AUTO_REFRESH)) {
                        key = KnxConfigKey.LENGTH;
                    } else if (key.equals(KnxConfigKey.ADDRESS)) {
                        key = KnxConfigKey.START;
                    }

                    device.getJSONObject(KnxConfigKey.REGISTER_MAP).put(key, value);
                } else {
                    device.put(key, value);
                }

                devices.put(name, device);
            }
        }

        json.put(KnxConfigKey.SLAVES, Merge(devices));

        return json;
    }

    @Override
    public String ToConfig(JSONObject json) {
        StringBuilder config = new StringBuilder();

        for (String globalKey : JSONObject.getNames(json)) {
            if (IsValidJsonKey(globalKey)) {
                if (globalKey.equals(KnxConfigKey.SLAVES)) {
                    JSONArray slaves = json.getJSONArray(globalKey);

                    for (int i = 0; i < slaves.length(); i++) {
                        JSONObject slave = slaves.getJSONObject(i);
                        JSONArray registerMap = slave.getJSONArray(KnxConfigKey.REGISTER_MAP);

                        String slaveName = AbstractConfigParser.GetValidSlaveName(slave.getString(KnxConfigKey.NAME));
                        String slaveType = slave.getString(KnxConfigKey.INTERFACE_TYPE);

                        for (int j = 0; j < registerMap.length(); j++) {
                            JSONObject register = registerMap.getJSONObject(j);

                            for (String param : JSONObject.getNames(slave)) {
                                if (IsValidConfigKey(param)) {
                                    String value = null;

                                    if (param.equals(KnxConfigKey.REGISTER_MAP)) {

                                    } else {
                                        value = slave.getString(param);
                                    }

                                    config.append(
                                            String.format("%s.%s_%d.%s=%s\n", slaveType, slaveName, j, param, value));
                                }
                            }

                            for (String registerKey : JSONObject.getNames(register)) {
                                if (IsValidConfigKey(registerKey)) {
                                    if (registerKey.equals(KnxConfigKey.START)) {
                                        config.append(String.format("%s.%s_%d.%s=%s\n", slaveType, slaveName, j,
                                                KnxConfigKey.ADDRESS, register.getString(registerKey)));
                                    } else if (registerKey.equals(KnxConfigKey.LENGTH)) {
                                        config.append(String.format("%s.%s_%d.%s=%s\n", slaveType, slaveName, j,
                                                KnxConfigKey.AUTO_REFRESH, register.getString(registerKey)));
                                    } else {
                                        config.append(String.format("%s.%s_%d.%s=%s\n", slaveType, slaveName, j,
                                                registerKey, register.getString(registerKey)));
                                    }
                                }
                            }

                            config.append("\n\n");
                        }
                    }
                } else {
                    config.append(String.format("%s=%s\n", globalKey, json.getString(globalKey)));
                }
            }
        }

        return config.toString();

    }

    private boolean IsValidJsonKey(String key) {
        Matcher matcher = patternValidJsonlKey.matcher(key);

        return matcher.matches();
    }

    private boolean IsValidConfigKey(String key) {
        Matcher matcher = patternValidConfigKey.matcher(key);

        return matcher.matches();
    }

    public JSONArray Merge(HashMap<String, JSONObject> oldDevices) throws JSONException {

        HashMap<String, JSONObject> devices = new HashMap<>();

        for (String name : oldDevices.keySet()) {
            String[] chunks = name.split("_");
            String key = String.join("_", Arrays.copyOfRange(chunks, 0, chunks.length - 1));

            JSONObject device = devices.get(key);
            JSONObject oldDevice = oldDevices.get(name);

            if (device == null) {
                device = new JSONObject(oldDevice.toMap());
                device.put(KnxConfigKey.REGISTER_MAP, new JSONArray());
            }

            device.getJSONArray(KnxConfigKey.REGISTER_MAP).put(oldDevice.getJSONObject(KnxConfigKey.REGISTER_MAP));

            devices.put(key, device);
        }

        JSONArray jarray = new JSONArray();

        for (String key : devices.keySet()) {
            jarray.put(devices.get(key));
        }

        return jarray;
    }
}
