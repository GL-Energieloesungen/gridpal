
package org.openhab.binding.gridpal.handler;



import java.util.Map;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.types.Command;
import org.openhab.binding.gridpal.configuration.GatewayConfiguration;
import org.openhab.binding.gridpal.mqtt.handler.MqttHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public class GatewayHandler extends GridpalBaseHandler
{
	private GatewayConfiguration config;
	// private MqttConfiguration configMqtt;
	private final Logger logger = LoggerFactory.getLogger(GridpalBaseHandler.class);
	private MqttHandler mqttHandler;

	public GatewayHandler(Thing thing)
	{
		super(thing);
	}

	@Override
	public void initialize()
	{
		UpdateConfigurations();

		mqttHandler = MqttHandler.CreateInstance(MqttClient.generateClientId(),
				String.format("tcp://%s:%s", config.url, config.port), config.username, config.password,
				config.topicRequest, config.topicFeedback);

		updateStatus(ThingStatus.ONLINE);
	}

	@Override
	public void dispose()
	{
		MqttHandler.ClearInstance();
		updateStatus(ThingStatus.REMOVED);
	}

	@Override
	public void handleCommand(ChannelUID channelUID, Command command)
	{

	}

	@Override
	public void handleConfigurationUpdate(Map<String, Object> configurationParameters)
	{
		super.handleConfigurationUpdate(configurationParameters);
		UpdateConfigurations();
	}

	private void UpdateConfigurations()
	{
		config = getConfig().as(GatewayConfiguration.class);
		// configMqtt.topicRequest = config.topicRequest;
		// configMqtt.topicFeedback = config.topicFeedback;
	}

}
