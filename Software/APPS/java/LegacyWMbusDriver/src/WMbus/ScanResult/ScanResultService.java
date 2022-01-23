/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WMbus.ScanResult;

import Mbus.jmbus.SecondaryAddress;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kanok
 */
public class ScanResultService {

    private static final java.lang.reflect.Type SCAN_RESULT_TYPE = new TypeToken<List<ScanResult>>() {
    }.getType();

    public static void addResult(SecondaryAddress secAdd) {
        try {
            String fileName = "wmbus-scan.json";
            createIfNotExist(fileName);
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(fileName));
            List<ScanResult> scanResults = gson.fromJson(reader, SCAN_RESULT_TYPE);
            ScanResult scanResult = new ScanResult();
            scanResult.id = secAdd.getDeviceId().toString();
            scanResult.manufacturer = secAdd.getManufacturerId();
            scanResult.devicetype = secAdd.getDeviceType().toString();
            scanResult.timeStamp = System.currentTimeMillis();
            if (scanResults == null) {
                scanResults = new ArrayList<>();
            }
            Iterator<ScanResult> iter = scanResults.iterator();
            while (iter.hasNext()) {
                long timeNow = System.currentTimeMillis();
                ScanResult oldScanResult = iter.next();
                if (oldScanResult.id.equals(scanResult.id) || timeNow > oldScanResult.timeStamp+(5000*60)) {
                    iter.remove();
                }
            }
            scanResults.add(scanResult);
            try (Writer writer = new FileWriter(fileName)) {
                gson.toJson(scanResults, SCAN_RESULT_TYPE, writer);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ScanResultService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ScanResultService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void createIfNotExist(String fileName) {
        String text = "[]";
        BufferedWriter output = null;
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                output = new BufferedWriter(new FileWriter(file));
                output.write(text);
            }
        } catch (IOException e) {
            Logger.getLogger(ScanResultService.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ex) {
                    Logger.getLogger(ScanResultService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
