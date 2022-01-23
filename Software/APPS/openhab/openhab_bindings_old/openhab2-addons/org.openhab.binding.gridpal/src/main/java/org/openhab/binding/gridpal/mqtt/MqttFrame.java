
package org.openhab.binding.gridpal.mqtt;

public class MqttFrame
{
	public int deviceIdHigh;
	public int deviceIdLow;
	public int direction;
	public int cmdId;
	public int channelId;
	public int value;
	public int reserved1;
	public int reserved2;
	public int reserved3;
	public int reserved4;
	public int tokenId;
	public int checksum;

	public MqttFrame()
	{
		deviceIdHigh = 255;
		deviceIdLow = 255;
		direction = 255;
		cmdId = 255;
		channelId = 255;
		value = 255;
		reserved1 = 255;
		reserved2 = 255;
		reserved3 = 255;
		reserved4 = 255;
		tokenId = 255;
		checksum = 255;
	}

	public MqttFrame(int[] frame)
	{
		if (frame.length != 12)
		{

		}

		deviceIdHigh = frame[0];
		deviceIdLow = frame[1];
		direction = frame[2];
		cmdId = frame[3];
		channelId = frame[4];
		value = frame[5];
		reserved1 = frame[6];
		reserved2 = frame[7];
		reserved3 = frame[8];
		reserved4 = frame[9];
		tokenId = frame[10];
		checksum = frame[11];
	}

	public MqttFrame(byte[] frame)
	{
		if (frame.length != 12)
		{
		}

		deviceIdHigh = frame[0];
		deviceIdLow = frame[1];
		direction = frame[2];
		cmdId = frame[3];
		channelId = frame[4];
		value = frame[5];
		reserved1 = frame[6];
		reserved2 = frame[7];
		reserved3 = frame[8];
		reserved4 = frame[9];
		tokenId = frame[10];
		checksum = frame[11];
	}

	public MqttFrame(String frame)
	{

	}

	public int[] toIntArray()
	{
		int[] frame = new int[12];

		frame[0] = deviceIdHigh;
		frame[1] = deviceIdLow;
		frame[2] = direction;
		frame[3] = cmdId;
		frame[4] = channelId;
		frame[5] = value;
		frame[6] = reserved1;
		frame[7] = reserved2;
		frame[8] = reserved3;
		frame[9] = reserved4;
		frame[10] = tokenId;
		frame[11] = checksum;

		return frame;
	}

	public byte[] toByteArray()
	{
		byte[] frame = new byte[12];

		frame[0] = (byte) deviceIdHigh;
		frame[1] = (byte) deviceIdLow;
		frame[2] = (byte) direction;
		frame[3] = (byte) cmdId;
		frame[4] = (byte) channelId;
		frame[5] = (byte) value;
		frame[6] = (byte) reserved1;
		frame[7] = (byte) reserved2;
		frame[8] = (byte) reserved3;
		frame[9] = (byte) reserved4;
		frame[10] = (byte) tokenId;
		frame[11] = (byte) checksum;

		return frame;
	}

	public static int NextTokenID(int tokenId)
	{
		return ++tokenId % 256;
	}
}
