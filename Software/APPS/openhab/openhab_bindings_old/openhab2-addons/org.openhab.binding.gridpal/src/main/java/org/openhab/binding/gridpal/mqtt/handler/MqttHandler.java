
package org.openhab.binding.gridpal.mqtt.handler;



import java.util.ArrayList;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.openhab.binding.gridpal.eventlistener.MqttEventListener;
import org.openhab.binding.gridpal.mqtt.MqttFrame;
import org.openhab.binding.gridpal.mqtt.configuration.MqttConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public class MqttHandler extends BaseMqttHandler
{
	private static MqttHandler instance;
	private MqttConfiguration config;
	private Logger logger = LoggerFactory.getLogger(MqttHandler.class);
	private ArrayList<MqttEventListener> subscribers = new ArrayList<>();

	private MqttHandler(String clientId, String serverURI, String topicRequest, String topicFeedback)
	{
		this(clientId, serverURI, "", "", topicRequest, topicFeedback);
	}

	private MqttHandler(String clientId, String serverURI, String username, String password, String topicRequest,
			String topicFeedback)
	{
		super(clientId, serverURI, username, password);

		config = new MqttConfiguration(topicRequest, topicFeedback);

		instance.Init();
	}

	public static MqttHandler CreateInstance(String clientId, String serverURI, String username, String password,
			String topicRequest, String topicFeedback)
	{
		instance = new MqttHandler(clientId, serverURI, username, password, topicRequest, topicFeedback);
		return instance;
	}

	public static MqttHandler CreateInstance(String clientId, String serverURI, String topicRequest,
			String topicFeedback)
	{
		instance = new MqttHandler(clientId, serverURI, topicRequest, topicFeedback);
		return instance;
	}

	public static MqttHandler GetInstance()
	{
		return instance;
	}

	public static void ClearInstance()
	{
		try
		{
			instance.Disconnect();
			instance = null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void Init()
	{
		super.Init();

		client.setCallback(new MqttCallback()
		{

			@Override
			public void messageArrived(String topic, MqttMessage message)
			{
				if (!topic.equals(config.topicFeedback))
				{
					return;
				}

				for (MqttEventListener subscriber : subscribers)
				{
					subscriber.HandleEvent(new MqttFrame(message.getPayload()));
				}
			}

			@Override
			public void deliveryComplete(IMqttDeliveryToken token)
			{

			}

			@Override
			public void connectionLost(Throwable arg0)
			{
				logger.debug("[ MQTT ] : Connection lost.");
				logger.debug("[ MQTT ] : Reconnecting...");

				Connect();
			}
		});

		Connect();

	}

	public void AddSubscriber(MqttEventListener subscriber)
	{
		if (instance == null)
		{
			return;
		}

		subscribers.add(subscriber);
	}

	public void RemoveSubscriber(MqttEventListener unsubscriber)
	{
		if (instance == null)
		{
			return;
		}

		subscribers.remove(unsubscriber);

		// for (int i = 0; i < subscribers.size(); i++)
		// {
		// if (subscribers.get(i) == unsubscriber)
		// {
		// subscribers.remove(i);
		// }
		// }

	}

	public void Connect()
	{
		Disconnect();

		try
		{
			client.connect(options);
			client.subscribe(config.topicFeedback);
			logger.debug("[ MQTT ] : Connected.");
		}
		catch (MqttSecurityException e)
		{
			e.printStackTrace();
		}
		catch (MqttException e)
		{
			e.printStackTrace();
		}

	}

	public void Disconnect()
	{
		try
		{
			client.disconnect();
			logger.debug("[ MQTT ] : Disconnected.");
		}
		catch (MqttException e)
		{
			e.printStackTrace();
		}
	}

	public void Publish(MqttFrame frame)
	{
		if (!client.isConnected())
		{
			Connect();
		}

		try
		{
			client.publish(config.topicRequest, frame.toByteArray(), 2, false);
		}
		catch (MqttException e)
		{
			e.printStackTrace();
		}
	}

	public MqttConfiguration getConfig()
	{
		return config;
	}

	public void setConfig(MqttConfiguration config)
	{
		this.config = config;
	}
}
