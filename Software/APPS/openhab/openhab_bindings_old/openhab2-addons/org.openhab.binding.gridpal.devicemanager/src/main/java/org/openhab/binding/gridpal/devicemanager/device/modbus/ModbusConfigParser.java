
package org.openhab.binding.gridpal.devicemanager.device.modbus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openhab.binding.gridpal.devicemanager.device.AbstractConfigParser;
import org.openhab.binding.gridpal.devicemanager.device.utils.Utility;

/**
 * This class is used for parsing the configuration of modbus devices.
 * @author  Md. Farhabi Helal Ayon
 * @since   0.1.0
 */
public class ModbusConfigParser
{
    public static final String[] globalParameters = { ModbusConfigKey.POLL, ModbusConfigKey.WRITE_MULTIPLE_REGISTERS };
    public static final String[] globalParametersExtra = { ModbusConfigKey.SLAVES };

    public static final String[] slaveTypes = { ModbusConfigKey.TCP, ModbusConfigKey.UDP, ModbusConfigKey.SERIAL };

    public static final String[] slaveParametersMandatory = { ModbusConfigKey.CONNECTION, ModbusConfigKey.TYPE,
            ModbusConfigKey.LENGTH, };

    public static final String[] slaveParametersOptional = { ModbusConfigKey.ID, ModbusConfigKey.START,
            ModbusConfigKey.VALUE_TYPE, ModbusConfigKey.UPDATE_UNCHANGED_ITEMS,
            ModbusConfigKey.POST_UNDEFINED_ON_READ_ERROR };

    public static final String[] slaveParametersExtra = { ModbusConfigKey.NAME, ModbusConfigKey.MANUFACTURER,
            ModbusConfigKey.INTERFACE_TYPE, ModbusConfigKey.DEVICE_TYPE, ModbusConfigKey.DESCRIPTION,
            ModbusConfigKey.UNIT, ModbusConfigKey.MULTIPLY, ModbusConfigKey.OFFSET };

    private static final int DEFAULT_POLL_INTERVAL = 1000;

    /**
     * Reads the cfg file and converts it to json.
     * @param   filepath path of the cfg file.
     * @return  json of the configuration.
     */
    public JSONObject ToJSON(String filepath) throws JSONException, IOException
    {
        JSONObject json = null;

        ArrayList<String> lines = Utility.Read(filepath, "#");

        // Global Parameter
        ArrayList<String> globalParameters = GetGlobalParameters(lines);
        json = GetJSON(globalParameters);

        json.put(ModbusConfigKey.SLAVES, new JSONArray());

        // Slave Parameter
        for (String slaveType : slaveTypes)
        {
            ArrayList<String> parameters = GetSlavesByType(slaveType, lines);
            ArrayList<JSONObject> slaves = new ArrayList<>();

            // Create slaves
            while (parameters.size() > 0)
            {
                String name = GetSlaveName(parameters.get(0));

                ArrayList<String> slaveParameters = GetSlaveParametersByName(name, parameters);
                parameters = RemoveSlaveParametersByName(name, parameters);

                JSONObject slave = GetSlaveJSON(slaveParameters);
                slaves.add(slave);
            }

            // Merge duplicates
            Set<String> slaveNames = new HashSet<>();

            for (JSONObject slave : slaves)
            {
                slaveNames.add(slave.getString(ModbusConfigKey.NAME));
            }

            for (String slaveName : slaveNames)
            {
                ArrayList<JSONObject> sameSlaves = new ArrayList<>();

                for (JSONObject slave : slaves)
                {
                    if (slaveName.equals(slave.getString(ModbusConfigKey.NAME)))
                    {
                        sameSlaves.add(slave);
                    }
                }

                json.accumulate(ModbusConfigKey.SLAVES, Merge(sameSlaves));
            }

        }

        // Poll
        if (!json.has(ModbusConfigKey.POLL))
        {
            json.put(ModbusConfigKey.POLL, String.valueOf(DEFAULT_POLL_INTERVAL));
        }

        return json;
    }

    /**
     * Merges duplicates.
     * @param   jsons the list of jsons to merge.
     * @return  merged json.
     */
    public JSONObject Merge(ArrayList<JSONObject> jsons) throws JSONException
    {
        JSONObject mergedJSON = new JSONObject();
        JSONArray registerMap = new JSONArray();

        for (int i = 0; i < jsons.size(); i++)
        {
            JSONObject json = jsons.get(i);

            for (String key : JSONObject.getNames(json))
            {
                if (key.equals(ModbusConfigKey.REGISTER_MAP))
                {
                    registerMap.put(json.getJSONObject(key));
                } else
                {
                    mergedJSON.put(key, json.get(key));
                }
            }
        }

        mergedJSON.put(ModbusConfigKey.REGISTER_MAP, registerMap);

        return mergedJSON;
    }

    /**
     * Converts configuration json to cfg compatible string.
     * @param   json configurations
     * @return  cfg compatible string representation of the json.
     */
    public String ToConfig(JSONObject json)
    {
        StringBuilder config = new StringBuilder();

        try
        {
            for (String globalKey : JSONObject.getNames(json))
            {
                if (IsGlobalParameter(globalKey))
                {
                    if (globalKey.equals(ModbusConfigKey.SLAVES))
                    {
                        JSONArray slaves = json.getJSONArray(globalKey);

                        for (int i = 0; i < slaves.length(); i++)
                        {
                            JSONObject slave = slaves.getJSONObject(i);
                            JSONArray registerMap = slave.getJSONArray(ModbusConfigKey.REGISTER_MAP);

                            String slaveName = AbstractConfigParser
                                    .GetValidSlaveName(slave.getString(ModbusConfigKey.NAME));
                            String slaveType = AbstractConfigParser
                                    .ParseInterfaceType((slave.getString(ModbusConfigKey.INTERFACE_TYPE)))[1];

                            for (int j = 0; j < registerMap.length(); j++)
                            {
                                JSONObject register = registerMap.getJSONObject(j);

                                for (String param : JSONObject.getNames(slave))
                                {
                                    if (IsSlaveParameter(param))
                                    {
                                        String value = null;

                                        if (param.equals(ModbusConfigKey.CONNECTION))
                                        {
                                            JSONArray connections = slave.getJSONArray(param);
                                            String connection = connections.getString(0);

                                            for (int k = 1; k < connections.length(); k++)
                                            {
                                                connection += ":" + connections.getString(k);
                                            }

                                            value = connection;
                                        } else if (param.equals(ModbusConfigKey.REGISTER_MAP))
                                        {

                                        } else
                                        {
                                            value = slave.getString(param);
                                        }

                                        config.append(String.format("%s.%s_%d.%s=%s\n", slaveType, slaveName, j, param,
                                                value));
                                    }
                                }

                                for (String registerKey : JSONObject.getNames(register))
                                {
                                    config.append(String.format("%s.%s_%d.%s=%s\n", slaveType, slaveName, j,
                                            registerKey, register.getString(registerKey)));
                                }

                                config.append("\n\n");
                            }
                        }
                    } else
                    {
                        config.append(String.format("%s=%s\n", globalKey, json.getString(globalKey)));
                    }
                }
            }

        } catch (Exception e)
        {

        }

        return config.toString();
    }

    private boolean IsGlobalParameter(String key)
    {
        for (String _key : globalParameters)
        {
            if (key.equals(_key))
            {
                return true;
            }
        }

        for (String _key : globalParametersExtra)
        {
            if (key.equals(_key))
            {
                return true;
            }
        }

        return false;
    }

    private boolean IsSlaveParameter(String key)
    {
        for (String _key : slaveParametersMandatory)
        {
            if (key.equals(_key))
            {
                return true;
            }
        }
        for (String _key : slaveParametersOptional)
        {
            if (key.equals(_key))
            {
                return true;
            }
        }

        for (String _key : slaveParametersExtra)
        {
            if (key.equals(_key))
            {
                return true;
            }
        }

        return false;
    }

    private boolean IsValidSlaveType(String type)
    {
        for (String _type : slaveTypes)
        {
            if (type.equals(_type))
            {
                return true;
            }
        }

        return false;
    }

    private ArrayList<String> GetSlavesByType(String type, ArrayList<String> lines)
    {
        ArrayList<String> slaves = new ArrayList<>();

        for (String line : lines)
        {
            String[] words = line.split("\\.");

            if (words[0].equals(type))
            {
                slaves.add(String.join(".", Arrays.copyOfRange(words, 1, words.length)));
            }
        }

        return slaves;
    }

    private ArrayList<String> GetSlaveParametersByName(String name, ArrayList<String> lines)
    {
        ArrayList<String> slaves = new ArrayList<>();

        for (String line : lines)
        {
            String[] words = line.split("\\.");

            if (words[0].equals(name))
            {
                slaves.add(String.join(".", Arrays.copyOfRange(words, 1, words.length)));
            }
        }

        return slaves;
    }

    // Parameter should be in the form: <name>=<value>
    private JSONObject GetSlaveJSON(ArrayList<String> parameters) throws JSONException
    {
        JSONObject json = new JSONObject();
        JSONObject register = new JSONObject();

        for (String parameter : parameters)
        {
            String[] kv = parameter.split("=");

            kv = kv.length < 2 ? new String[] { kv[0], "" } : kv;

            if (IsSlaveParameter(kv[0]))
            {
                if (kv[0].equals(ModbusConfigKey.START) || kv[0].equals(ModbusConfigKey.LENGTH)
                        || kv[0].equals(ModbusConfigKey.VALUE_TYPE) || kv[0].equals(ModbusConfigKey.DESCRIPTION)
                        || kv[0].equals(ModbusConfigKey.TYPE) || kv[0].equals(ModbusConfigKey.UNIT)
                        || kv[0].equals(ModbusConfigKey.MULTIPLY) || kv[0].equals(ModbusConfigKey.OFFSET))
                {
                    register.put(kv[0], kv[1]);
                } else if (kv[0].equals(ModbusConfigKey.CONNECTION))
                {
                    JSONArray connections = new JSONArray(kv[1].split(":"));
                    json.put(kv[0], connections);
                } else
                {
                    json.put(kv[0], kv[1]);
                }
            }
        }

        json.put(ModbusConfigKey.REGISTER_MAP, register);

        return json;
    }

    // Parameter should be in the form: <name>=<value>
    private JSONObject GetJSON(ArrayList<String> parameters) throws JSONException
    {
        JSONObject json = new JSONObject();

        for (String parameter : parameters)
        {
            String[] kv = parameter.split("=");

            if (IsGlobalParameter((kv[0])))
            {
                json.put(kv[0], kv[1]);
            }
        }

        return json;
    }

    private ArrayList<String> GetGlobalParameters(ArrayList<String> lines)
    {
        ArrayList<String> globalParameters = new ArrayList<>();

        for (String line : lines)
        {
            if (!line.contains("\\."))
            {
                globalParameters.add(line);
            }
        }

        return globalParameters;
    }

    private ArrayList<String> RemoveSlaveParametersByName(String name, ArrayList<String> lines)
    {
        ArrayList<String> newLines = new ArrayList<>();

        for (String line : lines)
        {
            String[] words = line.split("\\.");

            if (!words[0].equals(name))
            {
                newLines.add(line);
            }
        }

        return newLines;
    }

    private String GetSlaveType(String line)
    {
        String slaveType = null;

        if (line.contains("\\."))
        {
            slaveType = line.split("\\.")[0];
        } else
        {
            slaveType = "";
        }

        return slaveType;
    }

    private String GetSlaveName(String line)
    {
        String name = null;

        String[] words = line.split("=")[0].split("\\.");

        if (words.length == 2)
        {
            // <slave-name>.<param-name>=<value>
            name = words[0];
        } else if (words.length == 3)
        {
            // <slave-type>.<slave-name>.<param-name>=<value>
            name = words[1];
        } else
        {
            name = "";
        }

        return name;
    }

    private ArrayList<JSONObject> GetSlavesByName(String name, ArrayList<JSONObject> slaves) throws JSONException
    {
        ArrayList<JSONObject> sameSlaves = new ArrayList<>();

        for (JSONObject slave : slaves)
        {
            if (slave.getString(ModbusConfigKey.NAME).equals(name))
            {
                sameSlaves.add(slave);
            }
        }

        return sameSlaves;
    }
}
