/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WMbus.app;

/**
 *
 * @author kanok
 */
import Mbus.jmbus.DataRecord;
import java.io.IOException;
import java.util.Arrays;

import Mbus.jmbus.DecodingException;
import Mbus.jmbus.SecondaryAddress;
import Mbus.jmbus.VariableDataStructure;
import Mbus.jmbus.wireless.WMBusConnection;
import Mbus.jmbus.wireless.WMBusConnection.WMBusSerialBuilder;
import Mbus.jmbus.wireless.WMBusConnection.WMBusSerialBuilder.WMBusManufacturer;
import Mbus.jmbus.wireless.WMBusListener;
import Mbus.jmbus.wireless.WMBusMessage;
import Mbus.jmbus.wireless.WMBusMode;
import Utilities.MqttPublisher;
import static WMbus.ScanResult.ScanResultService.addResult;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WirelessMBusConnection {

    public static SecondaryAddress secAddress = null;
    static MqttPublisher mqttPublisher = null;

    public static class MyWMBusListener implements WMBusListener {

        @Override
        public void newMessage(WMBusMessage message) {
            // TODO Auto-generated method stub
            System.out.println(message.toString());
            addResult(message.getSecondaryAddress());
            System.out.println("************************************");
            /*secAddress = message.getSecondaryAddress();
            if (secAddress.getManufacturerId().equals("BMT")) {
                try {
                    System.out.println(secAddress.getDeviceId());
                    System.out.println("************************************");
                    VariableDataStructure vd = message.getVariableDataResponse();
                    vd.decode();
                    //System.out.println(vd.toString());
                    List<DataRecord> dataRecords = vd.getDataRecords();
                            for (DataRecord data : dataRecords) {
                                String vibStr = bytesToHex(data.getVib());
                                String dibStr = bytesToHex(data.getDib());
                                //System.out.println(" VIB: "+vibStr+" DIB: "+dibStr);
                                if(vibStr.equals("65") && dibStr.equals("02")){//temp
                                    System.out.println("Data: "+ data.getDescription()+": "+ Math.round(data.getScaledDataValue()*1000)/1000.0d+" "+data.getUnit()+" VIB: "+vibStr+" DIB: "+dibStr);
                                    String topic = "ucm/devices/" + "wmbus" + "/" + "temp";
                                    String timeStamp = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss").format(new Date());
                                    mqttPublisher.publish(topic, " {\"value\":" + Math.round(data.getScaledDataValue()*1000)/1000.0d + ",\"timestamp\":\"" + timeStamp + "\"}");
                                }
                                else if(vibStr.equals("FB1A") && dibStr.equals("02")){//humid
                                    System.out.println("Data: "+ data.getDescription()+": "+ Math.round(data.getScaledDataValue()*1000)/1000.0d+" "+data.getUnit()+" VIB: "+vibStr+" DIB: "+dibStr);
                                    String topic = "ucm/devices/" + "wmbus" + "/" + "hum";
                                    String timeStamp = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss").format(new Date());
                                    mqttPublisher.publish(topic, " {\"value\":" + Math.round(data.getScaledDataValue()*1000)/1000.0d + ",\"timestamp\":\"" + timeStamp + "\"}");
                                }
                                else if(vibStr.equals("13") && dibStr.equals("0C")){//meter
                                    System.out.println("Data: "+ data.getDescription()+": "+ Math.round(data.getScaledDataValue()*1000)/1000.0d+" "+data.getUnit()+" VIB: "+vibStr+" DIB: "+dibStr);
                                    String topic = "ucm/devices/" + "wmbus" + "/" + "meter" + "/" + secAddress.getDeviceId();
                                    String timeStamp = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss").format(new Date());
                                    mqttPublisher.publish(topic, " {\"value\":" + Math.round(data.getScaledDataValue()*1000)/1000.0d + ",\"timestamp\":\"" + timeStamp + "\"}");
                                }
                                
                                
                            }
                    System.out.println("#####################################");
                } catch (DecodingException ex) {
                    Logger.getLogger(WirelessMBusConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }*/
        }

        @Override
        public void discardedBytes(byte[] bytes) {
            // TODO Auto-generated method stub
            //System.out.println("Discarded "+System.currentTimeMillis());
            //System.out.println(bytesToHex(bytes));
        }

        @Override
        public void stoppedListening(IOException cause) {
            // TODO Auto-generated method stub
            //System.out.println("Stoped listening "+System.currentTimeMillis());
        }

    }

    public static void newConnection(String serialPortName) throws IOException {
        // TODO set these values
        byte[] key = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};
        // tag::todoc[]
        WMBusManufacturer wmBusManufacturer = WMBusManufacturer.AMBER;
        WMBusListener listener = new MyWMBusListener();
        WMBusSerialBuilder builder = new WMBusSerialBuilder(wmBusManufacturer, listener, serialPortName)
                .setMode(WMBusMode.T);
        try (WMBusConnection wmBusConnection = builder.build()) {
            //wmBusConnection.addKey(address, key);
            mqttPublisher = new MqttPublisher("localhost");
            System.out.println("listening.......");
            while (true) {
                try {
                    Thread.sleep(1000);
                    //System.out.println("ok");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    if (wmBusConnection != null) {
                        wmBusConnection.close();
                    }
                }
            }
        }
        // end::todoc[]
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
