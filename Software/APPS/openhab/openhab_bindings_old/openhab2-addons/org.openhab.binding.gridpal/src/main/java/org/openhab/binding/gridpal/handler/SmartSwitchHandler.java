
package org.openhab.binding.gridpal.handler;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.smarthome.config.core.Configuration;
import org.eclipse.smarthome.core.library.types.OnOffType;
import org.eclipse.smarthome.core.library.types.PercentType;
import org.eclipse.smarthome.core.thing.Channel;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.binding.builder.ChannelBuilder;
import org.eclipse.smarthome.core.thing.binding.builder.ThingBuilder;
import org.eclipse.smarthome.core.types.Command;
import org.eclipse.smarthome.core.types.RefreshType;
import org.eclipse.smarthome.core.types.State;
import org.openhab.binding.gridpal.GridpalBindingConstants;
import org.openhab.binding.gridpal.configuration.SmartSwitchConfiguration;
import org.openhab.binding.gridpal.eventlistener.MqttEventListener;
import org.openhab.binding.gridpal.mqtt.MqttCommandID;
import org.openhab.binding.gridpal.mqtt.MqttFrame;
import org.openhab.binding.gridpal.mqtt.MqttFrameBuilder;
import org.openhab.binding.gridpal.mqtt.configuration.MqttFrameConfiguration;
import org.openhab.binding.gridpal.mqtt.handler.MqttHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public class SmartSwitchHandler extends GridpalBaseHandler implements MqttEventListener
{

	private SmartSwitchConfiguration config;
	private MqttFrameConfiguration configMqttFrame;
	private final MqttHandler mqttHandler = MqttHandler.GetInstance();
	private final Logger logger = LoggerFactory.getLogger(SmartSwitchHandler.class);
	private int mqttTokenId = 0;

	// private SmartSwitchMqttHandler mqttHandler = new
	// SmartSwitchMqttHandler("tcp://" + config.gatewayIP + ":" + "1883",
	// MqttClient.generateClientId(), config.gatewayMac, config.deviceIDH,
	// config.deviceIDL, this);

	public SmartSwitchHandler(Thing thing)
	{
		super(thing);
	}

	@Override
	public void handleCommand(@NonNull ChannelUID channelUId, @NonNull Command command)
	{
		if (command instanceof RefreshType)
		{
			return;
		}
		else if (command instanceof OnOffType)
		{
			OnOffType state = (OnOffType) command;

			switch (state)
			{
				case ON:
					TurnOn(channelUId.getId());
					break;
				case OFF:
					TurnOff(channelUId.getId());
					break;
			}
		}
		else if (command instanceof PercentType)
		{
			PercentType value = (PercentType) command;
			Dim(channelUId.getId(), value);
		}
	}

	@Override
	public void handleConfigurationUpdate(@NonNull Map<@NonNull String, @NonNull Object> configurationParameters)
	{
		logger.debug("\n\n[ APL SMART SWITCH HANDLING CONFIG UPDATE ]\n\n");

		Configuration configuration = editConfiguration();

		for (Entry<String, Object> configurationParameter : configurationParameters.entrySet())
		{
			configuration.put(configurationParameter.getKey(), configurationParameter.getValue());
		}

		dispose();

		updateConfiguration(configuration);
		config = configuration.as(SmartSwitchConfiguration.class);

		initialize();
	}

	@Override
	public void initialize()
	{
		logger.debug("\n\n[ APL SMART SWITCH INITIALIZE ]\n\n");
		config = getConfig().as(SmartSwitchConfiguration.class);
		configMqttFrame = new MqttFrameConfiguration(config.deviceIdHigh, config.deviceIdLow);
		thingStructureChanged();
		updateStatus(ThingStatus.ONLINE);
	}

	@Override
	public void dispose()
	{
		logger.debug("\n\n[ APL SMART SWITCH DISPOSE ]\n\n");
	}

	@Override
	public void thingUpdated(Thing thing)
	{
		logger.debug("\n\n[ APL SMART SWITCH THING UPDATED ]\n\n");
	}

	@Override
	public void handleRemoval()
	{
		logger.debug("\n\n[ APL SMART SWITCH HANDLE REMOVAL ]\n\n");
		updateStatus(ThingStatus.REMOVED);
	}

	public void thingStructureChanged()
	{
		logger.debug("\n\n[ THING STRUCTURE CHANGED ]\n\n");

		ThingBuilder thingBuilder = editThing();
		ChannelBuilder channelBuilder;

		String[] switchConfigs = { config.switch01, config.switch02, config.switch03, config.switch04, config.switch05,
				config.switch06 };

		List<Channel> oldChannels = getThing().getChannels();
		List<Channel> newChannels = new ArrayList<>();

		for (int i = 0; i < oldChannels.size(); i++)
		{
			Channel oldChannel = oldChannels.get(i);

			logger.debug("Switch config " + (i + 1) + " : " + switchConfigs[i]);

			if (switchConfigs[i].equals("Light") || switchConfigs[i].equals("TubeLight")
					|| switchConfigs[i].equals("EnergySavingLight"))
			{
				channelBuilder = ChannelBuilder
						.create(new ChannelUID(getThing().getUID(), UUID.randomUUID().toString()), "Switch");
			}
			else
			{
				channelBuilder = ChannelBuilder
						.create(new ChannelUID(getThing().getUID(), UUID.randomUUID().toString()), "Dimmer");
			}

			channelBuilder.withLabel(oldChannel.getLabel()).withDescription(oldChannel.getDescription())
					.withType(oldChannel.getChannelTypeUID()).withConfiguration(oldChannel.getConfiguration())
					.withProperties(oldChannel.getProperties()).withDefaultTags(oldChannel.getDefaultTags())
					.withKind(oldChannel.getKind());

			thingBuilder.withoutChannel(oldChannel.getUID());
			newChannels.add(channelBuilder.build());
		}

		updateThing(thingBuilder.withChannels(newChannels).build());
	}

	@Override
	public void HandleEvent(MqttFrame frame)
	{
		if (frame.deviceIdHigh == config.deviceIdHigh && frame.deviceIdLow == config.deviceIdLow)
		{
			ChannelUID channelUID = getThing().getChannel(String.valueOf(GetChannelID(frame.channelId))).getUID();
			State state;

			switch (MqttCommandID.valueOf(frame.cmdId))
			{
				case FEEDBACK_ONOFF:
					state = frame.value == 1 ? OnOffType.ON : OnOffType.OFF;
					break;

				case FEEDBACK_DIMMING:
					state = new PercentType(frame.value);
					break;
			}

			updateState(channelUID, state);
		}
	}

	private String GetChannelID(int channel)
	{
		String channelId = "";

		switch (channel)
		{
			case 1:
				channelId = GridpalBindingConstants.CHANNEL_TYPE_ID_SMART_SWITCH_01;
				break;

			case 2:
				channelId = GridpalBindingConstants.CHANNEL_TYPE_ID_SMART_SWITCH_02;
				break;

			case 3:
				channelId = GridpalBindingConstants.CHANNEL_TYPE_ID_SMART_SWITCH_03;
				break;

			case 4:
				channelId = GridpalBindingConstants.CHANNEL_TYPE_ID_SMART_SWITCH_04;
				break;

			case 5:
				channelId = GridpalBindingConstants.CHANNEL_TYPE_ID_SMART_SWITCH_05;
				break;

			case 6:
				channelId = GridpalBindingConstants.CHANNEL_TYPE_ID_SMART_SWITCH_06;
				break;
		}

		return channelId;
	}

	private int GetChannel(String channelId)
	{
		int channel = 0;

		switch (channelId)
		{
			case GridpalBindingConstants.CHANNEL_TYPE_ID_SMART_SWITCH_01:
				channel = 1;
				break;

			case GridpalBindingConstants.CHANNEL_TYPE_ID_SMART_SWITCH_02:
				channel = 2;
				break;

			case GridpalBindingConstants.CHANNEL_TYPE_ID_SMART_SWITCH_03:
				channel = 3;
				break;

			case GridpalBindingConstants.CHANNEL_TYPE_ID_SMART_SWITCH_04:
				channel = 4;
				break;

			case GridpalBindingConstants.CHANNEL_TYPE_ID_SMART_SWITCH_05:
				channel = 5;
				break;

			case GridpalBindingConstants.CHANNEL_TYPE_ID_SMART_SWITCH_06:
				channel = 6;
				break;
		}

		return channel;
	}

	private void TurnOn(String channelId)
	{
		MqttFrame frame = MqttFrameBuilder.Create().WithChannelID(GetChannel((channelId)))
				.WithCommandID(MqttCommandID.REQUEST_ONOFF.getValue()).WithConfiguration(configMqttFrame)
				.WithDirection(17).WithTokenID(mqttTokenId = MqttFrame.NextTokenID(mqttTokenId)).WithValue(0).Build();

		mqttHandler.Publish(frame);
	}

	private void TurnOff(String channelId)
	{
		MqttFrame frame = MqttFrameBuilder.Create().WithChannelID(GetChannel(channelId))
				.WithCommandID(MqttCommandID.REQUEST_ONOFF.getValue()).WithConfiguration(configMqttFrame)
				.WithDirection(17).WithTokenID(MqttFrame.NextTokenID(mqttTokenId++)).WithValue(1).Build();

		mqttHandler.Publish(frame);
	}

	private void Dim(String channelId, PercentType value)
	{
		MqttFrame frame = MqttFrameBuilder.Create().WithChannelID(GetChannel(channelId))
				.WithCommandID(MqttCommandID.REQUEST_DIMMING.getValue()).WithConfiguration(configMqttFrame)
				.WithDirection(17).WithTokenID(MqttFrame.NextTokenID(mqttTokenId++)).WithValue(value.intValue())
				.Build();

		mqttHandler.Publish(frame);
	}
}
