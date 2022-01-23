
package org.openhab.binding.gridpal.eventlistener;



import org.openhab.binding.gridpal.mqtt.MqttFrame;





public interface MqttEventListener
{
	public void HandleEvent(MqttFrame frame);
}
