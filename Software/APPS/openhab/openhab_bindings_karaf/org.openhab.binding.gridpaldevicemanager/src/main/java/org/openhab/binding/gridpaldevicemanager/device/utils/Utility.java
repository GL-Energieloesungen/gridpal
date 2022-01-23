
package org.openhab.binding.gridpaldevicemanager.device.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class Utility {
    public static ArrayList<String> Read(String filename, String delim) throws IOException {
        File file = new File(filename);
        file.createNewFile();

        ArrayList<String> lines = new ArrayList<>();
        String line = null;

        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            while ((line = reader.readLine()) != null) {

                if (!(line.startsWith(delim) || line.isEmpty())) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static ArrayList<String> Read(String filename) throws IOException {
        return Read(filename, "");
    }

    public static void Write(String filename, ArrayList<String> lines) throws IOException {
        File file = new File(filename);
        file.createNewFile();

        StringBuilder content = new StringBuilder();

        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath())) {
            for (String line : lines) {
                content.append(line + "\n");
            }

            writer.write(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Write(String filename, String str) throws IOException {
        File file = new File(filename);
        file.createNewFile();

        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath())) {
            writer.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
