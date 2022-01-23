package org.openhab.binding.gridpalui.ui;

public class Config {
    public static final String DEFAULT_RESOURCES_DIR = "/usr/share/openhab2/gridpalui";
    public String RESOURCES_DIR;

    public Config() {
        this.RESOURCES_DIR = DEFAULT_RESOURCES_DIR;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s=%s\n", ConfigKey.RESOURCES_DIR, RESOURCES_DIR));

        return sb.toString();
    }
}