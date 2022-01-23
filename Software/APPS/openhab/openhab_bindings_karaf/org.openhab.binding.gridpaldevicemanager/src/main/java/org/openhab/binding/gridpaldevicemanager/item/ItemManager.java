
package org.openhab.binding.gridpaldevicemanager.item;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import org.openhab.binding.gridpaldevicemanager.device.utils.Utility;

public class ItemManager {
    private ItemsParser parser;
    private String filename;

    public ItemManager(String filename, ItemsParser parser) {
        this.filename = filename;
        this.parser = parser;
    }

    private boolean Put(String filename, JSONObject json) {
        if (json == null) {
            return false;
        }

        try {
            ArrayList<Item> items = parser.Parse(json);
            ArrayList<String> lines = new ArrayList<>();

            for (Item item : items) {
                lines.add(item.toString());
            }

            Utility.Write(filename, lines);

            return true;
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean Put(JSONObject json) {
        return Put(this.filename, json);
    }
}
