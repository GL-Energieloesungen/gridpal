
package org.openhab.binding.gridpal.mqtt.handler;



import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public abstract class BaseMqttHandler
{
	protected String serverURI;
	protected String clientId;
	protected String username = "kanok";
	protected String password = "kanok";

	protected MqttClient client;
	protected MqttConnectOptions options;
	protected MqttMessage message;

	private Logger logger = LoggerFactory.getLogger(BaseMqttHandler.class);


	public BaseMqttHandler(String serverURI, String clientId)
	{
		this.serverURI = serverURI;
		this.clientId = clientId;
	}


	public BaseMqttHandler(String clientId, String serverURI, String username, String password)
	{
		this.clientId = clientId;
		this.serverURI = serverURI;
		this.username = username;
		this.password = password;
	}


	public void Init()
	{
		logger.debug("[ Mqtt Base Handler ] : Init()");

		try
		{
			client = new MqttClient(serverURI, clientId);

			options = new MqttConnectOptions();
			options.setUserName(username);
			options.setPassword(password.toCharArray());
			options.setConnectionTimeout(30);
			options.setKeepAliveInterval(3600);
			options.setCleanSession(true);

			message = new MqttMessage();
			message.setQos(2);
			message.setRetained(false);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
