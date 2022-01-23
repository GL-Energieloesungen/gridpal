//
// package org.openhab.binding.gridpal.mqtt.handler;
//
//
//
// import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
// import org.eclipse.paho.client.mqttv3.MqttCallback;
// import org.eclipse.paho.client.mqttv3.MqttException;
// import org.eclipse.paho.client.mqttv3.MqttMessage;
// import org.eclipse.paho.client.mqttv3.MqttSecurityException;
// import org.openhab.binding.gridpal.mqtt.MqttFrame;
// import org.openhab.binding.gridpal.mqtt.configuration.MqttConfiguration;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
//
//
//
//
//
// public class GridpalMqttHandler extends BaseMqttHandler
// {
// private MqttConfiguration config;
// private Logger logger = LoggerFactory.getLogger(GridpalMqttHandler.class);
//
// public GridpalMqttHandler(String clientId, String serverURI, String
// topicRequest, String topicFeedback)
// {
// this(clientId, serverURI, "", "", topicRequest, topicFeedback);
// }
//
// public GridpalMqttHandler(String clientId, String serverURI, String username,
// String password, String topicRequest,
// String topicFeedback)
// {
// super(clientId, serverURI, username, password);
// config = new MqttConfiguration(topicRequest, topicFeedback);
// }
//
// @Override
// public void Init()
// {
// super.Init();
//
// client.setCallback(new MqttCallback()
// {
//
// @Override
// public void messageArrived(String topic, MqttMessage message)
// {
// if (!topic.equals(config.topicFeedback))
// {
// return;
// }
// }
//
// @Override
// public void deliveryComplete(IMqttDeliveryToken token)
// {
//
// }
//
// @Override
// public void connectionLost(Throwable arg0)
// {
// logger.debug("[ MQTT ] : Connection lost.");
// logger.debug("[ MQTT ] : Reconnecting...");
//
// Connect();
// }
// });
//
// Connect();
//
// }
//
// public void Connect()
// {
// Disconnect();
//
// try
// {
// client.connect(options);
// client.subscribe(config.topicFeedback);
// logger.debug("[ MQTT ] : Connected.");
// }
// catch (MqttSecurityException e)
// {
// e.printStackTrace();
// }
// catch (MqttException e)
// {
// e.printStackTrace();
// }
//
// }
//
// public void Disconnect()
// {
// try
// {
// client.disconnect();
// logger.debug("[ MQTT ] : Disconnected.");
// }
// catch (MqttException e)
// {
// e.printStackTrace();
// }
// }
//
// public void Publish(MqttFrame frame)
// {
// if (!client.isConnected())
// {
// Connect();
// }
//
// try
// {
// client.publish(config.topicRequest, frame.toByteArray(), 2, false);
// }
// catch (MqttException e)
// {
// e.printStackTrace();
// }
// }
//
// public MqttConfiguration getConfig()
// {
// return config;
// }
//
// public void setConfig(MqttConfiguration config)
// {
// this.config = config;
// }
// }
