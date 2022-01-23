package org.openhab.binding.gridpalmodemapi.internal;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openhab.binding.gridpalmodemapi.internal.HttpClient.Result;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author kanok
 */
public class HStatusE3372h {

    private final Logger logger = LoggerFactory.getLogger(HStatusE3372h.class);

    private String sesInfo;
    private String tokenInfo;
    private final String ip;
    private HttpClient http = new HttpClient();

    public HStatusE3372h(String ip) {
        this.ip = ip;
        this.getToken();
    }

    private void getToken() {
        try {
            String sUrl = "http://" + ip + "/api/webserver/SesTokInfo";
            Result result = http.get(sUrl);
            String xml = result.getBody();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xml));
            Document document = builder.parse(is);
            document.getDocumentElement().normalize();
            if (document.getElementsByTagName("SesInfo").getLength() != 0) {
                this.sesInfo = document.getElementsByTagName("SesInfo").item(0).getTextContent();
            }
            if (document.getElementsByTagName("TokInfo").getLength() != 0) {
                this.tokenInfo = document.getElementsByTagName("TokInfo").item(0).getTextContent();
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            logger.debug(ex.toString());
        }
    }

    private String callApi(String endPoint) {
        String data = "";
        if (sesInfo != null && tokenInfo != null) {
            String sUrl = "http://" + ip + endPoint;
            Result result;
            try {
                Map<String, String> map = new HashMap<String, String>();
                map.put("__RequestVerificationToken", tokenInfo);
                map.put("Cookie", sesInfo);
                result = http.get(sUrl, map);
                if (result.getResponseCode() == 200) {
                    data = result.getBody();
                }
            } catch (IOException ex) {
                // TODO Auto-generated catch block
                logger.debug(ex.getMessage());
            }

        }
        return data;
    }

    public ModemInfo getModemInfo() {
        ModemInfo modemInfo = new ModemInfo();
        String xml = callApi("/api/monitoring/status");
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xml));
            Document document = builder.parse(is);
            document.getDocumentElement().normalize();
            if (document.getElementsByTagName("ConnectionStatus").getLength() != 0) {
                modemInfo.setConnectionStatus(
                        document.getElementsByTagName("ConnectionStatus").item(0).getTextContent());
            }
            if (document.getElementsByTagName("SignalStrength").getLength() != 0) {
                modemInfo.setSignalStrength(document.getElementsByTagName("SignalStrength").item(0).getTextContent());
            }
            if (document.getElementsByTagName("SignalIcon").getLength() != 0) {
                modemInfo.setSignalIcon(document.getElementsByTagName("SignalIcon").item(0).getTextContent());
            }
            if (document.getElementsByTagName("CurrentNetworkType").getLength() != 0) {
                modemInfo.setCurrentNetworkType(
                        document.getElementsByTagName("CurrentNetworkType").item(0).getTextContent());
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            logger.debug(ex.getMessage());
        }

        xml = callApi("/api/net/current-plmn");
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xml));
            Document document = builder.parse(is);
            document.getDocumentElement().normalize();
            if (document.getElementsByTagName("FullName").getLength() != 0) {
                modemInfo.setNetOperatorName(document.getElementsByTagName("FullName").item(0).getTextContent());
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            logger.debug(ex.getMessage());
        }

        xml = callApi("/api/monitoring/traffic-statistics");
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xml));
            Document document = builder.parse(is);
            document.getDocumentElement().normalize();
            if (document.getElementsByTagName("CurrentConnectTime").getLength() != 0) {
                modemInfo.setCurrentConnectTime(
                        document.getElementsByTagName("CurrentConnectTime").item(0).getTextContent());
            }
            if (document.getElementsByTagName("CurrentUpload").getLength() != 0) {
                modemInfo.setCurrentUpload(document.getElementsByTagName("CurrentUpload").item(0).getTextContent());
            }
            if (document.getElementsByTagName("CurrentDownload").getLength() != 0) {
                modemInfo.setCurrentDownload(document.getElementsByTagName("CurrentDownload").item(0).getTextContent());
            }
            if (document.getElementsByTagName("TotalUpload").getLength() != 0) {
                modemInfo.setTotalUpload(document.getElementsByTagName("TotalUpload").item(0).getTextContent());
            }
            if (document.getElementsByTagName("TotalDownload").getLength() != 0) {
                modemInfo.setTotalDownload(document.getElementsByTagName("TotalDownload").item(0).getTextContent());
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            logger.debug(ex.getMessage());
        }
        return modemInfo;
    }

    public class ModemInfo {

        String netOperatorName;
        String ConnectionStatus;
        String SignalStrength;
        String SignalIcon;
        String CurrentNetworkType;
        String CurrentConnectTime;
        String CurrentUpload;
        String CurrentDownload;
        String TotalUpload;
        String TotalDownload;

        public ModemInfo() {
            netOperatorName = "NA";
            ConnectionStatus = "NA";
            SignalStrength = "NA";
            SignalIcon = "NA";
            CurrentNetworkType = "NA";
            CurrentConnectTime = "NA";
            CurrentUpload = "NA";
            CurrentDownload = "NA";
            TotalUpload = "NA";
            TotalDownload = "NA";
        }

        private String toSize(String Ssize) {
            String size = "NA";
            try {
                double dSize = Double.parseDouble(Ssize);
                if (dSize == 0) {
                    size = "0 Bytes";
                } else {
                    String[] size_name = { "Bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB" };
                    int i = (int) Math.floor(Math.log(dSize) / Math.log(1024));
                    Double p = Math.pow(1024, i);
                    Double s = dSize / p;
                    size = String.format("%.2f %s", s, size_name[i]);

                }

            } catch (NumberFormatException ex) {

            }
            return size;

        }

        private String timeConversion(String sTotalSeconds) {
            try {
                int totalSeconds = Integer.parseInt(sTotalSeconds);
                int hours = totalSeconds / 60 / 60;
                int minutes = (totalSeconds - (hours * 60 * 60)) / 60;
                int seconds = totalSeconds - (hours * 60 * 60) - (minutes * 60);

                return hours + ":" + minutes + ":" + seconds;
            } catch (NumberFormatException ex) {
                return "NA";
            }

        }

        public String getNetOperatorName() {
            return netOperatorName;
        }

        public void setNetOperatorName(String netOperatorName) {
            this.netOperatorName = netOperatorName;
        }

        public String getConnectionStatus() {
            return ConnectionStatus;
        }

        public void setConnectionStatus(String ConnectionStatus) {
            if (ConnectionStatus.equals("2") || ConnectionStatus.equals("3") || ConnectionStatus.equals("5")
                    || ConnectionStatus.equals("8") || ConnectionStatus.equals("20") || ConnectionStatus.equals("21")
                    || ConnectionStatus.equals("23") || ConnectionStatus.equals("27") || ConnectionStatus.equals("28")
                    || ConnectionStatus.equals("29") || ConnectionStatus.equals("30") || ConnectionStatus.equals("31")
                    || ConnectionStatus.equals("32") || ConnectionStatus.equals("33")) {
                this.ConnectionStatus = "Connection failed, the profile is invalid";
            } else if (ConnectionStatus.equals("7") || ConnectionStatus.equals("11") || ConnectionStatus.equals("14")
                    || ConnectionStatus.equals("37")) {
                this.ConnectionStatus = "Network access not allowed";
            } else if (ConnectionStatus.equals("12") || ConnectionStatus.equals("13")) {
                this.ConnectionStatus = "Connection failed, roaming not allowed";
            } else if (ConnectionStatus.equals("201")) {
                this.ConnectionStatus = "Connection failed, bandwidth exceeded";
            } else if (ConnectionStatus.equals("900")) {
                this.ConnectionStatus = "Connecting";
            } else if (ConnectionStatus.equals("901")) {
                this.ConnectionStatus = "Connected";
            } else if (ConnectionStatus.equals("902")) {
                this.ConnectionStatus = "Disconnected";
            } else if (ConnectionStatus.equals("903")) {
                this.ConnectionStatus = "Disconnecting";
            } else if (ConnectionStatus.equals("904")) {
                this.ConnectionStatus = "Connection failed || disabled";
            }
        }

        public String getSignalStrength() {
            return SignalStrength;
        }

        public void setSignalStrength(String SignalStrength) {
            this.SignalStrength = SignalStrength;
        }

        public String getSignalIcon() {
            return SignalIcon;
        }

        public void setSignalIcon(String SignalIcon) {
            this.SignalIcon = SignalIcon;
        }

        public String getCurrentNetworkType() {
            return CurrentNetworkType;
        }

        public void setCurrentNetworkType(String CurrentNetworkType) {
            if ("0".equals(CurrentNetworkType)) {
                this.CurrentNetworkType = "No Service";
            }
            if ("1".equals(CurrentNetworkType)) {
                this.CurrentNetworkType = "GSM";
            }
            if ("2".equals(CurrentNetworkType)) {
                this.CurrentNetworkType = "GPRS (2.5G)";
            }
            if ("3".equals(CurrentNetworkType)) {
                this.CurrentNetworkType = "EDGE (2.75G)";
            }
            if ("4".equals(CurrentNetworkType)) {
                this.CurrentNetworkType = "WCDMA (3G)";
            }
            if ("5".equals(CurrentNetworkType)) {
                this.CurrentNetworkType = "HSDPA (3G)";
            }
            if ("6".equals(CurrentNetworkType)) {
                this.CurrentNetworkType = "HSUPA (3G)";
            }
            if ("7".equals(CurrentNetworkType)) {
                this.CurrentNetworkType = "HSPA (3G)";
            }
            if ("8".equals(CurrentNetworkType)) {
                this.CurrentNetworkType = "TD-SCDMA (3G)";
            }
            if ("9".equals(CurrentNetworkType)) {
                this.CurrentNetworkType = "HSPA+ (4G)";
            }
            if ("10".equals(CurrentNetworkType)) {
                this.CurrentNetworkType = "EV-DO rev. 0";
            }
            if ("11".equals(CurrentNetworkType)) {
                this.CurrentNetworkType = "EV-DO rev. A";
            }
            if ("12".equals(CurrentNetworkType)) {
                this.CurrentNetworkType = "EV-DO rev. B";
            }
            if ("13".equals(CurrentNetworkType)) {
                this.CurrentNetworkType = "1xRTT";
            }
            if ("14".equals(CurrentNetworkType)) {
                this.CurrentNetworkType = "UMB";
            }
            if ("15".equals(CurrentNetworkType)) {
                this.CurrentNetworkType = "1xEVDV";
            }
            if ("16".equals(CurrentNetworkType)) {
                this.CurrentNetworkType = "3xRTT";
            }
            if ("17".equals(CurrentNetworkType)) {
                this.CurrentNetworkType = "HSPA+ 64QAM";
            }
            if ("18".equals(CurrentNetworkType)) {
                this.CurrentNetworkType = "HSPA+ MIMO";
            }
            if ("19".equals(CurrentNetworkType)) {
                this.CurrentNetworkType = "LTE (4G)";
            }
            if ("41".equals(CurrentNetworkType)) {
                this.CurrentNetworkType = "UMTS (3G)";
            }
            if ("44".equals(CurrentNetworkType)) {
                this.CurrentNetworkType = "HSPA (3G)";
            }
            if ("45".equals(CurrentNetworkType)) {
                this.CurrentNetworkType = "HSPA+ (3G)";
            }
            if ("46".equals(CurrentNetworkType)) {
                this.CurrentNetworkType = "DC-HSPA+ (3G)";
            }
            if ("64".equals(CurrentNetworkType)) {
                this.CurrentNetworkType = "HSPA (3G)";
            }
            if ("65".equals(CurrentNetworkType)) {
                this.CurrentNetworkType = "HSPA+ (3G)";
            }
            if ("101".equals(CurrentNetworkType)) {
                this.CurrentNetworkType = "LTE (4G)";
            }
        }

        public String getCurrentConnectTime() {
            return CurrentConnectTime;
        }

        public void setCurrentConnectTime(String CurrentConnectTime) {
            this.CurrentConnectTime = timeConversion(CurrentConnectTime);
        }

        public String getCurrentUpload() {
            return CurrentUpload;
        }

        public void setCurrentUpload(String CurrentUpload) {
            this.CurrentUpload = toSize(CurrentUpload);
        }

        public String getCurrentDownload() {
            return CurrentDownload;
        }

        public void setCurrentDownload(String CurrentDownload) {
            this.CurrentDownload = toSize(CurrentDownload);
        }

        public String getTotalUpload() {
            return TotalUpload;
        }

        public void setTotalUpload(String TotalUpload) {
            this.TotalUpload = toSize(TotalUpload);
        }

        public String getTotalDownload() {
            return TotalDownload;
        }

        public void setTotalDownload(String TotalDownload) {
            this.TotalDownload = toSize(TotalDownload);
        }

        @Override
        public String toString() {
            return "ModemInfo{" + "netOperatorName=" + netOperatorName + ", ConnectionStatus=" + ConnectionStatus
                    + ", SignalStrength=" + SignalStrength + ", SignalIcon=" + SignalIcon + ", CurrentNetworkType="
                    + CurrentNetworkType + ", CurrentConnectTime=" + CurrentConnectTime + ", CurrentUpload="
                    + CurrentUpload + ", CurrentDownload=" + CurrentDownload + ", TotalUpload=" + TotalUpload
                    + ", TotalDownload=" + TotalDownload + '}';
        }

    }
}
