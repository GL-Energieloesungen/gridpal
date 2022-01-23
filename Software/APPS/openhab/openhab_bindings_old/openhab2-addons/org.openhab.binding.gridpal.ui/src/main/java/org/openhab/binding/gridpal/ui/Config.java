package org.openhab.binding.gridpal.ui;





public class Config
{
    public static final String DEFAULT_RESOURCES_DIR = "/usr/share/openhab2/gridpalui";
    public static final String[] DEFAULT_ROUTE_ENDPOINTS = {"home", "internet", "devicemanager", "live-data"};

    public String resourcesDir;
    public String[] routeEndpoints;

    public Config()
    {
        this.resourcesDir = DEFAULT_RESOURCES_DIR;
        this.routeEndpoints = DEFAULT_ROUTE_ENDPOINTS;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s=\"%s\"\n", ConfigKey.RESOURCES_DIR, resourcesDir));
        sb.append(String.format("%s=\"%s\"\n", ConfigKey.ROUTE_ENDPOINTS, String.join(",", routeEndpoints)));

        return sb.toString();
    }
}
