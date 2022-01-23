/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

/**
 *
 * @author kanok
 */
import Utilities.ConfClass.Conf;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.openmuc.jrxtx.DataBits;
import org.openmuc.jrxtx.Parity;
import org.openmuc.jrxtx.StopBits;

public class ConfParser {

    String cfgFileLoc = "/home/kanok/mbus.cfg";
    List<Conf> MeterConf;

    public ConfParser(String cfgLoc) {
        // TODO Auto-generated constructor stub
        this.cfgFileLoc = cfgLoc;
        try {
            MeterConf = getProperties(this.cfgFileLoc);
            System.out.println(MeterConf);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<Conf> getMeterConf() {
        return MeterConf;
    }
    
    

    public static List<Conf> getProperties(String infile) throws IOException {
        final int lhs = 0;
        final int rhs = 1;
        System.out.println(infile);
        List<Conf> MeterConfList = new ArrayList<>();
        TreeMap<String, String> map = new TreeMap<String, String>();
        BufferedReader bfr = new BufferedReader(new FileReader(new File(infile)));

        String line;
        while ((line = bfr.readLine()) != null) {
            if (!line.startsWith("#") && !line.isEmpty()) {
                String[] pair = line.trim().split("=");
                map.put(pair[lhs].trim(), pair[rhs].trim());
            }
        }
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getKey().contains(".id")) {
                    String[] sub = entry.getKey().split("\\.");
                    String ItemKeyStr = sub[0] + "." + sub[1] + ".start";
                    String UnitKeyStr = sub[0] + "." + sub[1] + ".unit";
                    String ConnectionKeyStr = sub[0] + "." + sub[1] + ".connection";
                    String NameKeyStr = sub[0] + "." + sub[1] + ".name";
                    String DescriptionKeyStr = sub[0] + "." + sub[1] + ".description";
                    if (MeterConfList.isEmpty()) {
                        Conf newMeterConf = new Conf(Integer.parseInt(entry.getValue()));
                        String[] subItem = map.get(ConnectionKeyStr).trim().split(":");
                        if (subItem.length >= 5) {
                            newMeterConf.setSerialPortName(subItem[0]);
                            newMeterConf.setBaud(Integer.parseInt(subItem[1]));
                            if (subItem[2].equals("8")) {
                                newMeterConf.setDataBits(DataBits.DATABITS_8);
                            } else if (subItem[2].equals("7")) {
                                newMeterConf.setDataBits(DataBits.DATABITS_7);
                            }
                            if (subItem[3].equals("even")) {
                                newMeterConf.setParity(Parity.EVEN);
                            } else if (subItem[3].equals("odd")) {
                                newMeterConf.setParity(Parity.ODD);
                            } else if (subItem[3].equals("none")) {
                                newMeterConf.setParity(Parity.NONE);
                            }
                            if (subItem[4].equals("1")) {
                                newMeterConf.setStopBits(StopBits.STOPBITS_1);
                            } else if (subItem[4].equals("2")) {
                                newMeterConf.setStopBits(StopBits.STOPBITS_2);
                            }
                        }
                        newMeterConf.setMeterItems(map.get(ItemKeyStr),map.get(NameKeyStr), map.get(DescriptionKeyStr), map.get(UnitKeyStr));
                        MeterConfList.add(newMeterConf);
                        //System.out.println(MeterConfList.toString());
                    } else {
                        for (Conf oldMeterConf : new ArrayList<>(MeterConfList)) {
                            if (oldMeterConf.getMeterId() == (Integer.parseInt(entry.getValue()))) {
                                MeterConfList.remove(oldMeterConf);
                                oldMeterConf.setMeterItems(map.get(ItemKeyStr),map.get(NameKeyStr), map.get(DescriptionKeyStr), map.get(UnitKeyStr));
                                MeterConfList.add(oldMeterConf);
                                //System.out.println(MeterConfList.toString());
                            } else {
                                Conf newMeterConf = new Conf(Integer.parseInt(entry.getValue()));
                                String[] subItem = map.get(ConnectionKeyStr).trim().split(":");
                                if (subItem.length >= 5) {
                                    newMeterConf.setSerialPortName(subItem[0]);
                                    newMeterConf.setBaud(Integer.parseInt(subItem[1]));
                                    if (subItem[2].equals("8")) {
                                        newMeterConf.setDataBits(DataBits.DATABITS_8);
                                    } else if (subItem[2].equals("7")) {
                                        newMeterConf.setDataBits(DataBits.DATABITS_7);
                                    }
                                    if (subItem[3].equals("even")) {
                                        newMeterConf.setParity(Parity.EVEN);
                                    } else if (subItem[3].equals("odd")) {
                                        newMeterConf.setParity(Parity.ODD);
                                    } else if (subItem[3].equals("none")) {
                                        newMeterConf.setParity(Parity.NONE);
                                    }
                                    if (subItem[4].equals("1")) {
                                        newMeterConf.setStopBits(StopBits.STOPBITS_1);
                                    } else if (subItem[4].equals("2")) {
                                        newMeterConf.setStopBits(StopBits.STOPBITS_2);
                                    }
                                }
                                newMeterConf.setMeterItems(map.get(ItemKeyStr),map.get(NameKeyStr), map.get(DescriptionKeyStr), map.get(UnitKeyStr));
                                MeterConfList.add(newMeterConf);
                                //System.out.println(MeterConfList.toString());
                            }
                        }
                    }
                }
            }

        }

        bfr.close();

        return MeterConfList;
    }
}
