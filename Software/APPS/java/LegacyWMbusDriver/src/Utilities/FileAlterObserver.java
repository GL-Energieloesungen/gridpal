/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Mbus.app.WiredMBusConnection;
import Utilities.ConfClass.Conf;
import WMbus.app.WirelessMBusConnection;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileDeleteStrategy;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

/**
 *
 * @author kanok
 */
public class FileAlterObserver {

    /**
     * @param args the command line arguments
     */
    static List<Conf> MeterConf;
    //static MqttPublisher mqttPublisher = null;

    static void getMeterConf() {
        FileAlterObserver.MeterConf = new ConfParser("/etc/openhab2/services/mbus.cfg").getMeterConf();
    }

    public static void main(String[] args) {
        try {
            // TODO code application logic here
            //getMeterConf();
            //mqttPublisher = new MqttPublisher("localhost");
            WirelessMBusConnection.newConnection("/dev/ttyUSB0");
        } catch (IOException ex) {
            Logger.getLogger(FileAlterObserver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void usingFileAlterationObserver() throws IOException {
        //get the file object        
        File parentDirectory = FileUtils.getFile("/etc/openhab2/services/");

        FileAlterationObserver observer = new FileAlterationObserver(parentDirectory);

        observer.addListener(new FileAlterationListenerAdaptor() {

            @Override
            public void onDirectoryCreate(File file) {
                //System.out.println("Folder created: " + file.getName());
            }

            @Override
            public void onDirectoryDelete(File file) {
                //System.out.println("Folder deleted: " + file.getName());
            }

            @Override
            public void onFileChange(File file) {
                if (file.getName().equals("mbus.cfg")) {
                    System.out.println("File Changed: " + file.getName());
                    //getMeterConf();
                }
            }

            @Override
            public void onDirectoryChange(File directory) {
                //System.out.println("directory Changed: " + directory.getName());
            }

            @Override
            public void onFileDelete(File file) {
                //System.out.println("File deleted: " + file.getName());
            }
        });

        //create a monitor to check changes after every 500 ms
        FileAlterationMonitor monitor = new FileAlterationMonitor(500, observer);
        try {
            monitor.start();
            WirelessMBusConnection.newConnection("/dev/ttyWMBus");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                monitor.stop(10000);
            } catch (Exception ex) {
                Logger.getLogger(FileAlterObserver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
