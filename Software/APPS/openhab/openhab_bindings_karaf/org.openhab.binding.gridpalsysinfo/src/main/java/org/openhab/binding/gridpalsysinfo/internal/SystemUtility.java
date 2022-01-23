package org.openhab.binding.gridpalsysinfo.internal;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemUtility {
    private Logger logger = LoggerFactory.getLogger(SystemUtility.class);
    private Agent agent = new Agent();

    private Pattern patternCpuLoad = Pattern
            .compile("(load\\s*average):\\s*([+-]?(\\d*[.])?\\d+),\\s*([+-]?(\\d*[.])?\\d+),\\s*([+-]?(\\d*[.])?\\d+)");

    public SystemUtility() {

    }

    public String GetCPU() {
        Float cpu = 0.0f;

        Float[] loads = { 0.0f, 0.0f, 0.0f };
        Float cpuCores = 1.0f;
        Float multiplier = 33.33f;

        String command[] = { "/bin/bash", "-c", "uptime" };
        String result = agent.Execute(command).trim();

        Matcher matcher = patternCpuLoad.matcher(result);

        if (matcher.find()) {
            loads[0] = matcher.group(2) == null ? 0.0f : Float.valueOf(matcher.group(2));
            loads[1] = matcher.group(4) == null ? 0.0f : Float.valueOf(matcher.group(4));
            loads[2] = matcher.group(6) == null ? 0.0f : Float.valueOf(matcher.group(6));
        }

        command[2] = "nproc";
        result = agent.Execute(command).trim();

        cpuCores = Float.valueOf(result);

        cpu = (loads[0] + loads[1] + loads[2]) / cpuCores * multiplier;

        return String.format("%.2f%%", cpu);
    }

    public String GetRAM() {
        String ram = "0.0";

        ram = String.format("%.2f GB/%.2f GB", GetUsedRAM(), GetTotalRAM());

        return ram;
    }

    public double GetTotalRAM() {
        double ramTotal = 0.0;

        String command[] = { "/bin/bash", "-c", "grep MemTotal: /proc/meminfo" };
        String[] words = agent.Execute(command).trim().replaceAll("[\\s,]+", " ").split(" ");

        try {
            ramTotal = Float.parseFloat(words[1]) / Math.pow(1024, 2);
        } catch (Exception e) {

        }

        return ramTotal;
    }

    public double GetAvailableRAM() {
        double ramAvailable = 0.0;

        String command[] = { "/bin/bash", "-c", "grep MemAvailable: /proc/meminfo" };
        String[] words = agent.Execute(command).trim().replaceAll("[\\s,]+", " ").split(" ");

        try {
            ramAvailable = Float.parseFloat(words[1]) / Math.pow(1024, 2);
        } catch (Exception e) {

        }

        return ramAvailable;
    }

    public double GetFreeRAM() {
        double ramAvailable = 0.0;

        String command[] = { "/bin/bash", "-c", "grep MemFree: /proc/meminfo" };
        String[] words = agent.Execute(command).trim().replaceAll("[\\s,]+", " ").split(" ");

        try {
            ramAvailable = Float.parseFloat(words[1]) / Math.pow(1024, 2);
        } catch (Exception e) {

        }

        return ramAvailable;
    }

    public double GetUsedRAM() {
        if (GetAvailableRAM() > 0.0f) {
            return GetTotalRAM() - GetAvailableRAM();
        } else {
            return GetTotalRAM() - GetFreeRAM();
        }
    }

    public ArrayList<String> GetLANs() {
        ArrayList<String> lans = new ArrayList<>();

        String command[] = { "/bin/bash", "-c", "nmcli device status" };
        String devices = null;
        String[] device = null;

        devices = agent.Execute(command).trim();

        Scanner scanner = new Scanner(devices);

        scanner.nextLine();

        while (scanner.hasNextLine()) {
            device = scanner.nextLine().split("[\t ]+");
            logger.debug(String.join(" ", device));

            if (device[1].equals("ethernet")) {
                lans.add(device[0].trim());
            }
        }

        scanner.close();

        return lans;
    }

    public ArrayList<String> GetWiFis() {
        ArrayList<String> wifis = new ArrayList<>();

        String command[] = { "/bin/bash", "-c", "nmcli device status" };
        String devices = null;
        String[] device = null;

        devices = agent.Execute(command).trim();

        Scanner scanner = new Scanner(devices);

        scanner.nextLine();

        while (scanner.hasNextLine()) {
            device = scanner.nextLine().split("[\t ]+");
            logger.debug(String.join(" ", device));

            if (device[1].equals("wifi")) {
                wifis.add(device[0].trim());
            }
        }

        scanner.close();

        return wifis;
    }

    public String GetWiFiFrequency(String wifi) {
        String freq = "0.0";

        String[] command = { "/bin/bash", "-c", "iw dev " + wifi + " link | grep freq" };

        try {
            freq = String.format("%.2f", Float.parseFloat(agent.Execute(command).trim().split("[\\s]+")[1]) / 1000.0f);
        } catch (Exception e) {

        }

        return freq + " GHz";
    }

    public ArrayList<String> GetSerialPorts() {
        ArrayList<String> serials = new ArrayList<>();

        String[] command = { "/bin/bash", "-c", "ls /dev/tty* | grep \"[S|USB|ACM]\"" };

        String result = agent.Execute(command).trim();
        String[] words = result.split("\n");

        for (String word : words) {
            serials.add(word.trim());
        }

        return serials;
    }

    // public String GetIPAddress()
    // {
    // String[] command = { "/bin/bash", "-c", "ip route get 8.8.8.8 | head -1 | cut
    // -d' ' -f8" };
    //
    // return agent.Execute(command).trim();
    // }

    public String GetIPAddress() {
        String ip = "Disconnected";

        try {
            ip = GetIPAllAddresses()[0].trim();

            if (ip.equals("")) {
                ip = "Disconnected";
            }
        } catch (Exception e) {

        }

        return ip;
    }

    public String[] GetIPAllAddresses() {
        String[] command = { "/bin/bash", "-c", "hostname -I" };

        return agent.Execute(command).trim().split(" ");
    }

    public String GetUptime() {
        String[] command = { "/bin/bash", "-c", "uptime -p" };

        String result = agent.Execute(command).trim();

        return result.replaceFirst("up", "").trim();
    }

}