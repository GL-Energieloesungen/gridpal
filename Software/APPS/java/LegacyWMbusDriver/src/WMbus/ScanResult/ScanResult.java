/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WMbus.ScanResult;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author kanok
 */
public class ScanResult {
    @SerializedName("interface-name")
    String interfaceName;
    @SerializedName("register-map")
    List<RegisterMap> registerMap; 
    String[] connection;
    String id;
    String devicetype;
    String manufacturer;
    long timeStamp;//internal

    public ScanResult() {
        connection = new String[]{"/dev/ttyWMBus",
            "9600",//default for AMBER
            "8",
            "none",
            "1",
            "rtu",
            "1000",
            "5000"};
        interfaceName = "wmbus";
        registerMap = new ArrayList<>();
    }
    
    class RegisterMap{
        String valuetype;
        String unit;
        String offset;
        String start;
        String length;
        String description;
        String multiply;
        String type;

        public RegisterMap() {
            valuetype = "";
            unit = "";
            offset = "";
            start = "";
            length = "";
            description = "";
            multiply = "";
            type = "";
        }
        
    }
            
}
