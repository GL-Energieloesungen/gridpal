
package org.openhab.binding.mbus.internal;



import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

import org.openmuc.jmbus.DataRecord;
import org.openmuc.jmbus.DecodingException;
import org.openmuc.jmbus.MBusConnection;
import org.openmuc.jmbus.VariableDataStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public abstract class MBusSlave
{
    protected static TreeMap<String, Double> records = new TreeMap<>();
    protected static HashSet<Integer> updatedPool = new HashSet<>();

    public String name = null;

    public int id = 0;
    public String start = null;
    public String unit = null;
    public String manufacturer = null;
    public String description = null;
    public String deviceType = null;
    public String interfaceType = null;
    public Double multiply = 1.0;

    private static Logger logger = LoggerFactory.getLogger(MBusSlave.class);

    public MBusConnection mbusConnection = null;

    public MBusSlave()
    {
    }

    public abstract void update(MBusBinding binding);

    public abstract MBusConnection getConnection() throws IOException;

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s = %s\n", "Name", name));
        sb.append(String.format("%s = %s\n", "Interface type", interfaceType));
        sb.append(String.format("%s = %s\n", "Device type", deviceType));
        sb.append(String.format("%s = %s\n", "Manufacturer", manufacturer));
        sb.append(String.format("%s = %s\n", "ID", id));
        sb.append(String.format("%s = %s\n", "Start", start));
        sb.append(String.format("%s = %s\n", "Description", description));

        return sb.toString();
    }

    public static String bytesToHex(byte[] bytes)
    {
        final char[] hexArray = "0123456789ABCDEF".toCharArray();

        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++)
        {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static void read(MBusConnection connection, int primaryAddress) throws IOException
    {
        try
        {
            connection.linkReset(primaryAddress);
        }
        catch (IOException e)
        {
            connection.close();
            e.printStackTrace();
        }
        catch (Exception e)
        {
            connection.close();
            e.printStackTrace();
        }

        boolean isMoreRecordFollow = false;

        do
        {
            try
            {
                VariableDataStructure variableDataStructure = connection.read(primaryAddress);

                if (variableDataStructure == null)
                {
                    isMoreRecordFollow = false;
                }
                else
                {
                    if (variableDataStructure.moreRecordsFollow())
                    {
                        isMoreRecordFollow = true;
                    }
                    try
                    {
                        variableDataStructure.decode();

                        List<DataRecord> dataRecords = variableDataStructure.getDataRecords();

                        for (DataRecord data : dataRecords)
                        {
                            // logger.debug("\n\n{} : {}\n{} : {}\n\n", "[ ADDRESS ]",
                            // bytesToHex(data.getVib()),
                            // "[ SCALED VALUE ]", data.getScaledDataValue());

                            records.put(bytesToHex(data.getVib()), data.getScaledDataValue());
                        }
                    }
                    catch (DecodingException e)
                    {
                        e.printStackTrace();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            catch (IOException e)
            {
                isMoreRecordFollow = false;
            }
            catch (Exception e)
            {
                isMoreRecordFollow = false;
                e.printStackTrace();
            }
        }
        while (isMoreRecordFollow);

        connection.close();
    }

    public static void resetUpdatedPool()
    {
        updatedPool = new HashSet<>();
    }

    public String getItemName()
    {
        return String.format("%s__%s",
                name.replaceAll("[\\s\\&\\<\\(\\[\\{\\\\\\^\\-\\=\\$\\!\\|\\]\\}\\)\\?\\*\\+\\.\\>]+", "_")
                        .toLowerCase(),
                description.replaceAll("[\\s\\&\\<\\(\\[\\{\\\\\\^\\-\\=\\$\\!\\|\\]\\}\\)\\?\\*\\+\\.\\>]+", "_"));
    }
}
