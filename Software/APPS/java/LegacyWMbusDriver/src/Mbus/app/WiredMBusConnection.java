package Mbus.app;

import java.io.IOException;

import java.util.List;
import java.util.TreeMap;

import Mbus.jmbus.DataRecord;
import Mbus.jmbus.DecodingException;
import Mbus.jmbus.MBusConnection;
import Mbus.jmbus.MBusConnection.MBusSerialBuilder;
import Mbus.jmbus.VariableDataStructure;
import Utilities.ConfClass.Conf;
import Utilities.ConfClass.Conf.MeterItem;

public class WiredMBusConnection {

    @SuppressWarnings("unused")
    public static TreeMap<String, String> read(Conf meterConf) throws IOException {
        MBusSerialBuilder builder = MBusConnection.newSerialBuilder(meterConf.getSerialPortName()).setBaudrate(meterConf.getBaud());
        //builder.setParity(meterConf.getParity());
        //builder.setDataBits(meterConf.getDataBits());
        //builder.setStopBits(meterConf.getStopBits());
        TreeMap<String, String> map = new TreeMap<String, String>();
        try (MBusConnection mBusConnection = builder.build()) {
            VariableDataStructure variableDataStructure = null;

            try {
                mBusConnection.linkReset(meterConf.getMeterId());
            } catch (IOException e) {
                mBusConnection.close();
            }
            try {
                Thread.sleep(100); // for slow slaves
            } catch (InterruptedException e) {
            }
            boolean isMoreRecordFollow = false;
            do {
                try {
                    variableDataStructure = mBusConnection.read(meterConf.getMeterId());
                    if (variableDataStructure == null) {
                        //break;
                        isMoreRecordFollow = false;
                        mBusConnection.close();
                    } else {
                        if (variableDataStructure.moreRecordsFollow()) {
                            isMoreRecordFollow = true;
                        }
                        try {
                            variableDataStructure.decode();
                            List<DataRecord> dataRecords = variableDataStructure.getDataRecords();
                            for (DataRecord data : dataRecords) {
                                byte[] vib = data.getVib();
                                String vibStr = bytesToHex(vib);
                                //System.out.println("Data: "+ data.getDescription()+": "+ data.getScaledDataValue()+" "+data.getUnit()+"VIB: "+vibStr);
                                //key should be with this format: mbus__L1_to_N_Voltage
                                for(MeterItem item:meterConf.getMeterItems()){
                                    if(vibStr.equals(item.itemCode)){
                                        map.put(item.meterName+"__"+item.description, data.getScaledDataValue().toString());
                                        System.out.println("Data: "+ data.getDescription()+": "+ data.getScaledDataValue()+" "+data.getUnit()+"VIB: "+vibStr);
                                    }
                                }
                            }
                        } catch (DecodingException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        //System.out.println("********************************************");
                        //System.out.println(variableDataStructure.toString());
                        //System.out.println("********************************************");
                    }
                } catch (IOException e) {
                    mBusConnection.close();
                    isMoreRecordFollow = false;
                }
            } while (isMoreRecordFollow);
            //System.out.println("-----------------------------Finished------------------------");
            mBusConnection.close();
            //System.out.println("-----------------------------Closed------------------------");
        }
        return map;
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

}
