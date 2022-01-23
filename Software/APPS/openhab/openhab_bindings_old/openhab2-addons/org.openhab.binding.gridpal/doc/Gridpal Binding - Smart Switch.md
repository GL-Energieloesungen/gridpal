# Gridpal Binding

## Smart Switch Overview

__Configurations__
- _Thing Definition_ `$binding_root_dir/ESH-INF/thing/apl-smart-switch.xml`
- _Configuration Description_ `$binding_root_dir/ESH-INF/config/configs.xml`
- _Channel Definitions_ `$binding_root_dir/ESH-INF/thing/channels.xml`

__Build Dependencies__
- _MQTT_ `org.eclipse.paho.client.mqttv3`

__Third-party Library__
- _JSON_ `org.json.jar`


### Mechanism

`SmartSwtichHandler` class:

```java
public class SmartSwitchHandler extends BaseThingHandler implements MqttEventListener
{
    // contains all the necessary configurations of the thing
    private SmartSwitchConfiguration config;

    // handles MQTT related operations
    private MqttHandler mqttHandler = new MqttHandler();

    ...
}
```

`MqttEventListener` interface provides callback function.
```java
public interface MqttEventListener
{
	public void HandleEvent(MqttFrame frame);
}
```


`MqttHandler` contains all the logic for _MQTT_ communication.
```java
public class MqttHandler
{
    String topicRequest;
    String topicFeedback;
    MqttEventListener listener;

    ...

    // Constructor
    public MqttHandler(String clientId, String serverURI, String username, String password, String topicRequest, String topicFeedback);

    ...

    // MQTT client functions
    public void Init();
    public void Connect();
    public void Disconnect();
    public void Publish(MqttFrame frame);
}
```


When a message is received, `MqttCallback#messageArrived()` is called. Callback can be set in the client with `MqttClient#setCallback()`.
```java
@Override
public void messageArrived(String topic, MqttMessage message)
{
    if (!topic.equals(topicFeedback)) return;

    ...

    listener.HandleEvent(frame);
}
```


During events, _MQTT_ messages are published by the `SmartSwitchHandler#TurnOn()` and `SmartSwitchHandler#TurnOff()`.
```java
@Override
public void handleCommand(@NonNull ChannelUID channelUId, @NonNull Command command)
{
    if (command instanceof OnOffType)
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
}
```

`HandleEvent` notifies the framework  about events/changes.
```java
public void HandleEvent(MqttFrame frame)
{
    ...

    updateState(channelUID, state);
}
```