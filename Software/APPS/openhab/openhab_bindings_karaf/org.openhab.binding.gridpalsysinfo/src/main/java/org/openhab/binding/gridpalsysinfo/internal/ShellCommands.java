package org.openhab.binding.gridpalsysinfo.internal;

public class ShellCommands {
    public static final String CPU = "uptime";
    public static final String CPU_CORES = "nproc";
    public static final String RAM = "grep MemTotal: /proc/meminfo";
    public static final String NETWORK_DEVICES = "nmcli device status";
    public static final String WIFI_FREQUENCY = "iw dev ${interface} link | grep freq";
    public static final String TX_BYTES = "cat /sys/class/net/${interface}/statistics/tx_bytes";
    public static final String RX_BYTES = "cat /sys/class/net/${interface}/statistics/rx_bytes";
    public static final String INSTALLED_PACKAGE_LIST = "apt list --installed";
}