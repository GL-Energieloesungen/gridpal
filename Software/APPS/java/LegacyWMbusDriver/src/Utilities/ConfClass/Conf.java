/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities.ConfClass;

import java.util.ArrayList;
import java.util.List;
import org.openmuc.jrxtx.DataBits;
import org.openmuc.jrxtx.Parity;
import org.openmuc.jrxtx.StopBits;

/**
 *
 * @author kanok
 */

public class Conf {
    String SerialPortName;
    int meterId;
    int baud;
    DataBits dataBits;
    Parity parity;
    StopBits stopBits;
    List<MeterItem> meterItems;

    public Conf(int id) {
        meterItems = new ArrayList<>();
        meterId = id;
        SerialPortName = "/dev/ttyMbus";
        baud = 2400;
        dataBits = DataBits.DATABITS_8;
        parity = Parity.NONE;
        stopBits = StopBits.STOPBITS_1;
    } 


    public String getSerialPortName() {
        return SerialPortName;
    }

    public void setSerialPortName(String SerialPortName) {
        this.SerialPortName = SerialPortName;
    }
    
    public List<MeterItem> getMeterItems() {
        return meterItems;
    }

    public void setMeterItems(String itemCode, String meterName, String description, String unit) { 
        description = description.replace(" ", "_");
        this.meterItems.add(new MeterItem(itemCode, meterName.toLowerCase(), description, unit));
    }    

    public int getMeterId() {
        return meterId;
    }

    public void setMeterId(int meterId) {
        this.meterId = meterId;
    }

    public int getBaud() {
        return baud;
    }

    public void setBaud(int baud) {
        this.baud = baud;
    }

    public DataBits getDataBits() {
        return dataBits;
    }

    public void setDataBits(DataBits dataBits) {
        this.dataBits = dataBits;
    }

    public Parity getParity() {
        return parity;
    }

    public void setParity(Parity parity) {
        this.parity = parity;
    }

    public StopBits getStopBits() {
        return stopBits;
    }

    public void setStopBits(StopBits stopBits) {
        this.stopBits = stopBits;
    }

    @Override
    public String toString() {
        return "Conf{" + "SerialPortName=" + SerialPortName + ", meterId=" + meterId + ", baud=" + baud + ", dataBits=" + dataBits + ", parity=" + parity + ", stopBits=" + stopBits + ", meterItems=" + meterItems + '}';
    }





    
    public class MeterItem {
        public String itemCode;
        public String meterName;
        public String description;
        public String unit;

        public MeterItem(String itemCode, String meterName, String description, String unit) {
            this.itemCode = itemCode;
            this.meterName = meterName;
            this.description = description;
            this.unit = unit;
        }

        @Override
        public String toString() {
            return "MeterItem{" + "itemCode=" + itemCode + ", meterName=" + meterName + ", description=" + description + ", unit=" + unit + '}';
        }


        
        
    }
    
}
