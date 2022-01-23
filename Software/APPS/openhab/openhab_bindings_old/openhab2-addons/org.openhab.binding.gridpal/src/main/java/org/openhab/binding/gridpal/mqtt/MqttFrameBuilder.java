
package org.openhab.binding.gridpal.mqtt;



import org.openhab.binding.gridpal.mqtt.configuration.MqttFrameConfiguration;





public class MqttFrameBuilder
{
	private MqttFrame frame;

	private MqttFrameBuilder()
	{
		frame = new MqttFrame();
	}

	public static MqttFrameBuilder Create()
	{
		return new MqttFrameBuilder();
	}

	public MqttFrame Build()
	{
		frame.checksum = GetChecksum();
		return frame;
	}

	public MqttFrameBuilder WithConfiguration(MqttFrameConfiguration config)
	{
		this.frame.deviceIdHigh = config.deviceIdHigh;
		this.frame.deviceIdLow = config.deviceIdLow;
		return this;
	}

	public MqttFrameBuilder WithDirection(int direction)
	{
		this.frame.direction = direction;
		return this;
	}

	public MqttFrameBuilder WithCommandID(int cmdId)
	{
		this.frame.cmdId = cmdId;
		return this;
	}

	public MqttFrameBuilder WithChannelID(int channelId)
	{
		this.frame.channelId = channelId;
		return this;
	}

	public MqttFrameBuilder WithValue(int value)
	{
		this.frame.value = value;
		return this;
	}

	public MqttFrameBuilder WithTokenID(int tokenId)
	{
		this.frame.tokenId = tokenId;
		return this;
	}

	public int GetChecksum()
	{
		int checksum = 255;

		return checksum;
	}
}
