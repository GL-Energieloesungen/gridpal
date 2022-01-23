
package org.openhab.binding.gridpal.mqtt;

public enum MqttCommandID
{
	INVALID(0), REQUEST_ONOFF(1), FEEDBACK_ONOFF(2),

	REQUEST_GROUP_ONOFF(3), FEEDBACK_GROUP_ONOFF(4),

	REQUEST_CURRENT_LOAD_STATUS(5), FEEDBACK_CURRENT_LOAD_STATUS(6),

	REQUEST_LOCATE_DEVICE(7), FEEDBACK_LOCATE_DEVICE(8),

	REQUEST_DIMMING(51), FEEDBACK_DIMMING(52),

	REQUEST_INDICATOR_ONOFF(53), FEEDBACK_INDICATOR_ONOFF(54);

	private int value;

	private MqttCommandID(int value)
	{
		this.value = value;
	}

	public static int valueOf(MqttCommandID cmdId)
	{
		return cmdId.value;
	}

	public static MqttCommandID valueOf(int cmdId)
	{
		switch (cmdId)
		{
			case 1:
				return MqttCommandID.REQUEST_ONOFF;
			case 2:
				return MqttCommandID.FEEDBACK_ONOFF;

			case 3:
				return MqttCommandID.REQUEST_GROUP_ONOFF;
			case 4:
				return MqttCommandID.FEEDBACK_GROUP_ONOFF;

			case 5:
				return MqttCommandID.REQUEST_CURRENT_LOAD_STATUS;
			case 6:
				return MqttCommandID.FEEDBACK_CURRENT_LOAD_STATUS;

			case 7:
				return MqttCommandID.REQUEST_LOCATE_DEVICE;
			case 8:
				return MqttCommandID.FEEDBACK_LOCATE_DEVICE;

			case 51:
				return MqttCommandID.REQUEST_DIMMING;
			case 52:
				return MqttCommandID.FEEDBACK_DIMMING;

			case 53:
				return MqttCommandID.REQUEST_INDICATOR_ONOFF;
			case 54:
				return MqttCommandID.FEEDBACK_INDICATOR_ONOFF;

			default:
				return MqttCommandID.INVALID;
		}
	}

	public int getValue()
	{
		return value;
	}
}
