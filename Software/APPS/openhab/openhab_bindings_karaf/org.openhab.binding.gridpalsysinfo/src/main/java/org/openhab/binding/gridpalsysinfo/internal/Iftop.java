package org.openhab.binding.gridpalsysinfo.internal;

import java.net.InetAddress;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openhab.binding.gridpalsysinfo.internal.HttpClient.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Iftop {
    private HttpClient http;
    private final Logger logger = LoggerFactory.getLogger(Iftop.class);

    public Iftop() {
        http = new HttpClient();
    }

    public static boolean sameNetwork(InetAddress ip1, InetAddress ip2, String mask) throws Exception {

        byte[] a1 = ip1.getAddress();
        byte[] a2 = ip2.getAddress();
        byte[] m = InetAddress.getByName(mask).getAddress();

        for (int i = 0; i < a1.length; i++) {
            if ((a1[i] & m[i]) != (a2[i] & m[i])) {
                return false;
            }
        }

        return true;

    }

    public JSONObject getTrafficHistory(String port) throws JSONException, Exception {

        JSONObject list = new JSONObject();
        String sUrl = "http://localhost:" + port + "/iftop/history";
        Result result = http.get(sUrl);
        JSONArray jsonArrResult = new JSONArray();
        if (result.getResponseCode() == 200) {
            String historyJson = result.getBody();
            if (!historyJson.isEmpty()) {
                JSONObject jsonObj = new JSONObject(historyJson);
                if (!jsonObj.isEmpty()) {
                    JSONArray jsonArr = new JSONArray(jsonObj.get("history").toString());
                    for (int i = 0; i < jsonArr.length(); i++) {
                        JSONObject jsonObj1 = jsonArr.getJSONObject(i);
                        String destinationIp = (jsonObj1.getString("destination")).split(":")[0];
                        String sourceIp = (jsonObj1.getString("source")).split(":")[0];
                        InetAddress inetAddrSrc = InetAddress.getByName(sourceIp);
                        InetAddress inetAddrDst = InetAddress.getByName(destinationIp);
                        if (!sameNetwork(inetAddrSrc, inetAddrDst, "255.255.255.0") && !sourceIp.contains("224.0.0.")
                                && !sourceIp.equals("255.255.255.255") && !destinationIp.contains("224.0.0.")
                                && !destinationIp.equals("255.255.255.255")) {// todo:get
                            // subnet
                            // mask by
                            // code
                            // logger.error("IpaddressMatcher " + "not matched");
                            String destinationHost = inetAddrDst.getHostName() + "(" + destinationIp + ")";
                            String SourceHost = inetAddrSrc.getHostName() + "(" + sourceIp + ")";
                            JSONObject jsonObjNew = new JSONObject();
                            jsonObjNew.put("source", SourceHost);
                            jsonObjNew.put("destination", destinationHost);
                            jsonObjNew.put("tx", jsonObj1.get("sent"));
                            jsonObjNew.put("rx", jsonObj1.get("received"));
                            jsonArrResult.put(jsonObjNew);
                        } else {
                            // logger.error("IpaddressMatcher " + "matched");
                        }
                    }
                }
            }
        }
        if (!jsonArrResult.isEmpty()) {
            list.put("history", jsonArrResult);
        }
        return list;
    }
}
