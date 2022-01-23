
package org.openhab.binding.gridpal.mqtt.configuration;

public class MqttFrameConfiguration
{
	public int deviceIdHigh;
	public int deviceIdLow;

	public MqttFrameConfiguration(int deviceIdHigh, int deviceIdLow)
	{
		this.deviceIdHigh = deviceIdHigh;
		this.deviceIdLow = deviceIdLow;
	}
}
