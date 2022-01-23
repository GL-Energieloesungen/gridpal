package org.openhab.binding.gridpalvendor.rest.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VendorUtility {
    private String uri = null;
    private Connection connection = null;

    public VendorUtility(String dbPath) {
        this.uri = "jdbc:sqlite:" + dbPath;
    }

    public void Connect(String uri) throws SQLException {
        DriverManager.registerDriver(new org.sqlite.JDBC());
        connection = DriverManager.getConnection(uri);
    }

    public void Connect() throws SQLException {
        Connect(this.uri);
    }

    public void Disconnect(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
        }
    }

    public void Disconnect() {
        Disconnect(connection);
    }

    public JSONObject GetInterfaces() {
        String sqlQuery = "SELECT id, name FROM interface";

        JSONObject json = new JSONObject();

        try {
            Connect();
            ResultSet result = connection.createStatement().executeQuery(sqlQuery);

            while (result.next()) {
                json.put(result.getString("id"), result.getString("name"));
            }

            result.close();

            return json;
        } catch (SQLException | JSONException e) {
            e.printStackTrace();
        } finally {
            Disconnect();
        }

        return null;
    }

    public JSONObject GetManufacturers(String iface) {
        String sqlQuery = "SELECT manufacturer.id, manufacturer.name FROM manufacturer, device WHERE device.id_interface = "
                + iface + " AND device.id_manufacturer = manufacturer.id GROUP BY manufacturer.id";

        JSONObject json = new JSONObject();

        try {
            Connect();
            ResultSet result = connection.createStatement().executeQuery(sqlQuery);

            // System.out.println("Result : " + result.toString());

            while (result.next()) {
                json.put(result.getString("id"), result.getString("name"));
            }

            result.close();

            return json;
        } catch (SQLException | JSONException e) {
            e.printStackTrace();
        } finally {
            Disconnect();
        }

        return null;
    }

    public JSONObject GetDevices(String manufacturer, String iface) {
        // String sqlQuery = "SELECT id, device_type FROM device where manufacturer=='"
        // + manufacturer + "'";
        String sqlQuery = "SELECT device.id, device.device_model FROM device WHERE device.id_manufacturer = "
                + manufacturer + " AND  device.id_interface = " + iface;

        JSONObject json = new JSONObject();

        try {
            Connect();
            ResultSet result = connection.createStatement().executeQuery(sqlQuery);

            while (result.next()) {
                json.put(result.getString("id"), result.getString("device_model"));
            }

            result.close();

            return json;
        } catch (SQLException | JSONException e) {
            e.printStackTrace();
        } finally {
            Disconnect();
        }

        return null;
    }

    public JSONObject GetConfig(String id) {
        // String sqlQuery = "SELECT id, config FROM device where id ='" + id + "'";
        String sqlQuery = "SELECT config FROM device WHERE id = " + id;

        JSONObject json = new JSONObject();

        try {
            Connect();
            ResultSet result = connection.createStatement().executeQuery(sqlQuery);

            result.next();
            json.put(id, result.getString("config"));

            result.close();

            return json;
        } catch (SQLException | JSONException e) {
            e.printStackTrace();
        } finally {
            Disconnect();
        }

        return null;
    }

    public JSONArray GetRegisterMap(String id) {
        // String sqlQuery = "SELECT valuetype, start, length, description FROM
        // registermap where deviceid_id ='" + id
        // + "'";
        String sqlQuery = "select valuetype, start, length, multiply, unit, description, type from registermap where id_device = "
                + id;

        JSONArray jarray = new JSONArray();

        try {
            Connect();
            ResultSet result = connection.createStatement().executeQuery(sqlQuery);

            while (result.next()) {
                JSONObject json = new JSONObject();
                json.put("valuetype", result.getString("valuetype"));
                json.put("start", result.getString("start"));
                json.put("length", result.getString("length"));
                json.put("multiply", result.getString("multiply"));
                json.put("unit", result.getString("unit"));
                json.put("type", result.getString("type"));
                json.put("description", result.getString("description"));
                jarray.put(json);
            }

            result.close();

            return jarray;
        } catch (SQLException | JSONException e) {
            e.printStackTrace();
        } finally {
            Disconnect();
        }

        return null;
    }

    public String Scan(String id_interface) {
        String str = null;

        switch (id_interface) {
            case "7":
                String filename = "/etc/openhab2/scan/wmbus-scan.json";
                try {
                    str = new String(Files.readAllBytes(Paths.get(filename)));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
        }

        return str;
    }
}
