
package org.openhab.binding.gridpal.devicemanager.device.wmbus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openhab.binding.gridpal.devicemanager.device.AbstractConfigParser;
import org.openhab.binding.gridpal.devicemanager.device.utils.Utility;

public class WMBusConfigParser extends AbstractConfigParser
{
    private static String[] validConfigKeys = { WMBusConfigKey.NAME, WMBusConfigKey.DESCRIPTION,
            Pattern.quote(WMBusConfigKey.INTERFACE_TYPE), WMBusConfigKey.MANUFACTURER, WMBusConfigKey.MULTIPLY,
            WMBusConfigKey.UNIT, WMBusConfigKey.LENGTH, WMBusConfigKey.START, WMBusConfigKey.DEVICE_TYPE,
            WMBusConfigKey.ID, WMBusConfigKey.CONNECTION, WMBusConfigKey.VALUE_TYPE, WMBusConfigKey.TYPE,
            WMBusConfigKey.OFFSET };

    private static Pattern patternValidConfigKey = Pattern
            .compile(String.format("(%s)", String.join("|", new HashSet<>(Arrays.asList(validConfigKeys)))));

    private static Pattern patternValidJsonKey = Pattern.compile(String.format("(%s)", WMBusConfigKey.DEVICES));

    private static Pattern patternConfigFormat = Pattern.compile("([\\w_]+)\\.([\\w\\d_]+)\\.([\\w-]+)\\=(.*)");

    @Override
    public JSONObject ToJSON(String filepath) throws JSONException, IOException
    {
        JSONObject json = new JSONObject();

        ArrayList<String> lines = Utility.Read(filepath, "#");

        HashMap<String, JSONObject> devices = new HashMap<>();

        for (String line : lines)
        {
            Matcher matcher = patternConfigFormat.matcher(line);

            if (matcher.matches())
            {
                String deviceType = matcher.group(1);
                String name = matcher.group(2);
                String key = matcher.group(3);
                String value = matcher.group(4);

                JSONObject device = devices.get(name);

                if (device == null)
                {
                    device = new JSONObject();
                    device.put(WMBusConfigKey.REGISTER_MAP, new JSONObject());
                }

                if (key.equals(WMBusConfigKey.CONNECTION))
                {
                    device.put(key, new JSONArray(Arrays.asList(value.split(":"))));
                } else if (key.equals(WMBusConfigKey.DESCRIPTION) || key.equals(WMBusConfigKey.VALUE_TYPE)
                        || key.equals(WMBusConfigKey.LENGTH) || key.equals(WMBusConfigKey.TYPE)
                        || key.equals(WMBusConfigKey.MULTIPLY) || key.equals(WMBusConfigKey.UNIT)
                        || key.equals(WMBusConfigKey.START) || key.equals(WMBusConfigKey.OFFSET))
                {

                    device.getJSONObject(WMBusConfigKey.REGISTER_MAP).put(key, value);
                } else
                {
                    device.put(key, value);
                }

                devices.put(name, device);
            }
        }

        json.put(WMBusConfigKey.DEVICES, Merge(devices));

        return json;
    }

    @Override
    public String ToConfig(JSONObject json)
    {
        StringBuilder config = new StringBuilder();

        for (String key : JSONObject.getNames(json))
        {
            if (IsValidJsonKey(key))
            {
                if (key.equals(WMBusConfigKey.DEVICES))
                {
                    JSONArray devices = json.getJSONArray(key);

                    for (int i = 0; i < devices.length(); i++)
                    {
                        JSONObject device = devices.getJSONObject(i);
                        JSONArray registersMap = device.getJSONArray(WMBusConfigKey.REGISTER_MAP);

                        String deviceName = AbstractConfigParser
                                .GetValidSlaveName(device.getString(WMBusConfigKey.NAME));
                        String deviceType = device.getString(WMBusConfigKey.INTERFACE_TYPE);

                        for (int j = 0; j < registersMap.length(); j++)
                        {
                            JSONObject registers = registersMap.getJSONObject(j);

                            for (String deviceKey : JSONObject.getNames(device))
                            {
                                if (IsValidConfigKey(deviceKey))
                                {
                                    String value = null;

                                    if (deviceKey.equals(WMBusConfigKey.CONNECTION))
                                    {
                                        JSONArray connections = device.getJSONArray(deviceKey);
                                        String connection = connections.getString(0);

                                        for (int k = 1; k < connections.length(); k++)
                                        {
                                            connection += ":" + connections.getString(k);
                                        }

                                        value = connection;
                                    } else if (deviceKey.equals(WMBusConfigKey.REGISTER_MAP))
                                    {

                                    } else
                                    {
                                        value = device.getString(deviceKey);
                                    }

                                    config.append(String.format("%s.%s_%d.%s=%s\n", deviceType, deviceName, j,
                                            deviceKey, value));
                                }
                            }

                            for (String registerKey : JSONObject.getNames(registers))
                            {
                                if (IsValidConfigKey(registerKey))
                                {
                                    config.append(String.format("%s.%s_%d.%s=%s\n", deviceType, deviceName, j,
                                            registerKey, registers.getString(registerKey)));
                                }
                            }

                            config.append("\n\n");
                        }
                    }
                } else
                {
                    config.append(String.format("%s=%s\n", key, json.getString(key)));
                }
            }
        }

        return config.toString();

    }

    private boolean IsValidJsonKey(String key)
    {
        Matcher matcher = patternValidJsonKey.matcher(key);

        return matcher.matches();
    }

    private boolean IsValidConfigKey(String key)
    {
        Matcher matcher = patternValidConfigKey.matcher(key);

        return matcher.matches();
    }

    public JSONArray Merge(HashMap<String, JSONObject> oldDevices) throws JSONException
    {

        HashMap<String, JSONObject> devices = new HashMap<>();

        for (String name : oldDevices.keySet())
        {
            String[] chunks = name.split("_");
            String key = String.join("_", Arrays.copyOfRange(chunks, 0, chunks.length - 1));

            JSONObject device = devices.get(key);
            JSONObject oldDevice = oldDevices.get(name);

            if (device == null)
            {
                device = new JSONObject(oldDevice.toMap());
                device.put(WMBusConfigKey.REGISTER_MAP, new JSONArray());
            }

            device.getJSONArray(WMBusConfigKey.REGISTER_MAP).put(oldDevice.getJSONObject(WMBusConfigKey.REGISTER_MAP));

            devices.put(key, device);
        }

        JSONArray jarray = new JSONArray();

        for (String key : devices.keySet())
        {
            jarray.put(devices.get(key));
        }

        return jarray;
    }
}
