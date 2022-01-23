//
// package org.openhab.binding.gridpal.handler;
//
//
//
// import java.util.Arrays;
//
// import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
// import org.eclipse.paho.client.mqttv3.MqttCallback;
// import org.eclipse.paho.client.mqttv3.MqttMessage;
// import org.openhab.binding.gridpal.AplMqttCommandID;
// import org.openhab.binding.gridpal.GridpalBindingConstants;
// import org.openhab.binding.gridpal.configuration.AplMqttConfiguration;
// import org.openhab.binding.gridpal.configuration.SmartSwitchConfiguration;
// import org.openhab.binding.gridpal.eventlistener.SmartSwitchEventListener;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
//
//
//
//
//
// public class SmartSwitchMqttHandler extends MqttBaseHandler
// {
// private int[] cmdTurnOn = { 255, 255, 17, 1, 1, 1, 255, 255, 255, 255, 255,
// 255 };
// private int[] cmdTurnOff = { 255, 255, 17, 1, 1, 0, 255, 255, 255, 255, 255,
// 255 };
//
// private AplMqttConfiguration config;
// private SmartSwitchEventListener listener;
//
// private final Logger logger =
// LoggerFactory.getLogger(SmartSwitchMqttHandler.class);
//
// // public SmartSwitchMqttHandler(String serverURI, String clientId,
// // SmartSwitchEventListener listener)
// // {
// // super(serverURI, clientId);
// //
// // this.listener = listener;
// // Init();
// // }
//
// public SmartSwitchMqttHandler(String serverURI, String clientId, String
// gatewayMac, int deviceIdHigh,
// int deviceIdLow, SmartSwitchEventListener listener)
// {
// super(serverURI, clientId);
// this.listener = listener;
//
// String topicRequest = "sh/command/" + gatewayMac;
// String topicFeedback = "sh/feedback/" + gatewayMac;
//
// config = new AplMqttConfiguration(topicRequest, topicFeedback, deviceIdHigh,
// deviceIdLow);
//
// Init();
// }
//
// @Override
// public void Init()
// {
// super.Init();
//
// logger.debug("[ Smart Switch Mqtt Handler ] : Init()");
//
// client.setCallback(new MqttCallback()
// {
//
// @Override
// public void messageArrived(String topic, MqttMessage message) throws
// Exception
// {
// AplMqttFrame frame = new AplMqttFrame(message.getPayload());
// logger.debug("[ MQTT ] : message = " +
// Arrays.toString(message.getPayload()));
// listener.switchState(getChannelID(frame.channelId), frame.value);
//
// }
//
// @Override
// public void deliveryComplete(IMqttDeliveryToken token)
// {
// // TODO Auto-generated method stub
//
// }
//
// @Override
// public void connectionLost(Throwable arg0)
// {
// logger.debug("[ MQTT ] : Connection lost.");
// logger.debug("[ MQTT ] : Reconnecting...");
//
// try
// {
// client.connect(options);
// client.subscribe(config.topicFeedback);
// logger.debug("[ MQTT ] : Connected.");
// }
// catch (Exception e)
// {
// e.printStackTrace();
// }
//
// }
// });
//
// try
// {
// if (client.isConnected())
// {
// client.disconnect();
// }
//
// client.connect(options);
// client.subscribe(config.topicFeedback);
//
// logger.debug("[ MQTT ] : Connected.");
// }
// catch (Exception e)
// {
// e.printStackTrace();
// }
// }
//
// public void turnOn(String channelId)
// {
// try
// {
// if (!client.isConnected())
// {
// client.connect(options);
// client.subscribe(config.topicFeedback);
// }
//
// AplMqttFrame frame = new AplMqttFrame(cmdTurnOn);
// frame.cmdId = 1;
// frame.channelId = getChannel(channelId);
// frame.value = 1;
//
// message.setPayload(frame.toByteArray());
// client.publish(config.topicRequest, message);
//
// // logger.debug("[ MQTT ] : Published ON via topic = {} and message = {}",
// // topicCmd, message.toString());
// }
// catch (Exception e)
// {
// e.printStackTrace();
// }
// }
//
// public void turnOff(String channelId)
// {
// try
// {
// if (!client.isConnected())
// {
// client.connect(options);
// client.subscribe(config.topicFeedback);
// }
//
// AplMqttFrame frame = new AplMqttFrame(cmdTurnOff);
// frame.cmdId = 1;
// frame.channelId = getChannel(channelId);
// frame.value = 0;
//
// message.setPayload(frame.toByteArray());
// client.publish(config.topicRequest, message);
//
// // logger.debug("[ MQTT ] : Published OFF via topic = {} and message = {}",
// // topicCmd, message.toString());
// }
// catch (Exception e)
// {
// e.printStackTrace();
// }
// }
//
// public void dimmingRequest(String channelId, int value)
// {
// try
// {
// if (!client.isConnected())
// {
// client.connect(options);
// client.subscribe(config.topicFeedback);
// }
//
// AplMqttFrame frame = new AplMqttFrame();
// frame.deviceIdHigh = ;
// frame.cmdId = AplMqttCommandID.REQUEST_DIMMING.getValue();
// frame.channelId = getChannel(channelId);
// frame.value = value;
//
// message.setPayload(frame.toByteArray());
// client.publish(config.topicRequest, message);
//
// // logger.debug("[ MQTT ] : Published OFF via topic = {} and message = {}",
// // topicCmd, message.toString());
// }
// catch (Exception e)
// {
// e.printStackTrace();
// }
// }
//
// private String getChannelID(int channel)
// {
// String channelId = "";
//
// switch (channel)
// {
// case 1:
// channelId = GridpalBindingConstants.CHANNEL_TYPE_ID_SMART_SWITCH_01;
// break;
//
// case 2:
// channelId = GridpalBindingConstants.CHANNEL_TYPE_ID_SMART_SWITCH_02;
// break;
//
// case 3:
// channelId = GridpalBindingConstants.CHANNEL_TYPE_ID_SMART_SWITCH_03;
// break;
//
// case 4:
// channelId = GridpalBindingConstants.CHANNEL_TYPE_ID_SMART_SWITCH_04;
// break;
//
// case 5:
// channelId = GridpalBindingConstants.CHANNEL_TYPE_ID_SMART_SWITCH_05;
// break;
//
// case 6:
// channelId = GridpalBindingConstants.CHANNEL_TYPE_ID_SMART_SWITCH_06;
// break;
// }
//
// return channelId;
// }
//
// private int getChannel(String channelId)
// {
// int channel = 0;
//
// switch (channelId)
// {
// case GridpalBindingConstants.CHANNEL_TYPE_ID_SMART_SWITCH_01:
// channel = 1;
// break;
//
// case GridpalBindingConstants.CHANNEL_TYPE_ID_SMART_SWITCH_02:
// channel = 2;
// break;
//
// case GridpalBindingConstants.CHANNEL_TYPE_ID_SMART_SWITCH_03:
// channel = 3;
// break;
//
// case GridpalBindingConstants.CHANNEL_TYPE_ID_SMART_SWITCH_04:
// channel = 4;
// break;
//
// case GridpalBindingConstants.CHANNEL_TYPE_ID_SMART_SWITCH_05:
// channel = 5;
// break;
//
// case GridpalBindingConstants.CHANNEL_TYPE_ID_SMART_SWITCH_06:
// channel = 6;
// break;
// }
//
// return channel;
// }
//
// public void refresh(String channelId)
// {
// try
// {
// if (!client.isConnected())
// {
// client.connect(options);
// client.subscribe(config.topicFeedback);
// }
//
// AplMqttFrame frame = new AplMqttFrame(cmdTurnOn);
// frame.cmdId = 1;
// frame.channelId = getChannel(channelId);
// frame.value = 0;
//
// message.setPayload(frame.toByteArray());
// client.publish(config.topicRequest, message);
// }
// catch (Exception e)
// {
// e.printStackTrace();
// }
// }
// }
