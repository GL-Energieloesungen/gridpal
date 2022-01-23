/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

/**
 *
 * @author kanok
 */
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class MqttPublisher {
	String host="";
	public MqttPublisher(String host) {		
		// TODO Auto-generated constructor stub
		this.host = host;
	}
	
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
	private static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	public void publish(String topic, String payload) {
		int qos             = 2;
        String broker       = "tcp://"+this.host+":1883";
        System.out.println(broker);
        String clientId = "jmbus-wired-";        
		try {
			MessageDigest salt;
			salt = MessageDigest.getInstance("SHA-256");
			salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
			String digest = bytesToHex(salt.digest());
	        clientId += digest;
		} catch (NoSuchAlgorithmException|UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		 try {
	            MqttClient sampleClient = new MqttClient(broker, clientId, null);
	            MqttConnectOptions connOpts = new MqttConnectOptions();
                    connOpts.setUserName("gridpal");
                    String pass = "gridpal";
                    char[] passChar = pass.toCharArray();
                    connOpts.setPassword(passChar);
	            connOpts.setCleanSession(true);
	            System.out.println("Connecting to broker: "+broker+"Client ID: "+clientId);
	            sampleClient.connect(connOpts);
	            System.out.println("Connected");
	            System.out.println("Publishing message: "+payload);
	            MqttMessage message = new MqttMessage(payload.getBytes());
	            message.setQos(qos);
	            sampleClient.publish(topic, message);
	            System.out.println("Message published");
	            sampleClient.disconnect();
	            System.out.println("Disconnected");
	        } catch(MqttException me) {
	            System.out.println("reason "+me.getReasonCode());
	            System.out.println("msg "+me.getMessage());
	            System.out.println("loc "+me.getLocalizedMessage());
	            System.out.println("cause "+me.getCause());
	            System.out.println("excep "+me);
	            me.printStackTrace();
	        }
	}
}

