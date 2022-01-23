
package org.openhab.binding.gridpal.devicemanager.item;



import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import org.openhab.binding.gridpal.devicemanager.device.utils.Utility;




/**
 * Manages item manipulation.
 * @author  Md. Farhabi Helal Ayon
 * @since   0.1.0
 */
public class ItemManager
{
    private ItemsParser parser;
    private String filename;

    public ItemManager(String filename, ItemsParser parser)
    {
        this.filename = filename;
        this.parser = parser;
    }

    /**
     * Writes item definition into the system.
     * @param   filename name of the file.
     * @param   json item data in json.
     * @return  status of the operation. 
     */
    private boolean Put(String filename, JSONObject json)
    {
        if (json == null)
        {
            return false;
        }

        try
        {
            ArrayList<Item> items = parser.Parse(json);
            ArrayList<String> lines = new ArrayList<>();

            for (Item item : items)
            {
                lines.add(item.toString());
            }

            Utility.Write(filename, lines);

            return true;
        }
        catch (JSONException | IOException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public boolean Put(JSONObject json)
    {
        return Put(this.filename, json);
    }
}
