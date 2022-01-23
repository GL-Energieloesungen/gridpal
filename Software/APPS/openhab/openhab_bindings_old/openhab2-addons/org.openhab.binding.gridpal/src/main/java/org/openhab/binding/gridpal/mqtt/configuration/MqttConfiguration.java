
package org.openhab.binding.gridpal.mqtt.configuration;

public class MqttConfiguration
{
	public String topicRequest;
	public String topicFeedback;


	public MqttConfiguration(String topicRequest, String topicFeedback)
	{
		this.topicRequest = topicRequest;
		this.topicFeedback = topicFeedback;
	}
}
