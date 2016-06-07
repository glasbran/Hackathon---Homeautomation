/**
 * @author Tim Ebner
 * @date 06.06.2016
 */
package logic;

import java.util.Properties;

import org.eclipse.paho.client.mqttv3.MqttException;

import com.google.gson.JsonObject;
import com.ibm.iotf.client.app.ApplicationClient;
import com.ibm.iotf.client.device.DeviceClient;

public class ApplicationConnector {
	ApplicationClient myClient = null;
	MyEventCallback evCallBack;
	String org;
	String deviceType;
	String deviceId;
	String apiKey;
	String apiToken;

	public ApplicationConnector(MyEventCallback evCallBack, String org, String deviceType, String deviceId, String apiKey, String apiToken){
		this.evCallBack = evCallBack;
		this.org = org;
		this.deviceType = deviceType;
		this.deviceId = deviceId;
		this.apiKey = apiKey;
		this.apiToken = apiToken;
		initClient();
	}
	
	
    public void changeState(String command)
    {
    	
    	//Generate the event to be published
    	JsonObject data = new JsonObject();
    	data.addProperty("Light", "light_state");

    	//Registered flow allows 0, 1 and 2 QoS
    	myClient.publishCommand(deviceType, deviceId, command, data);
    	
    	/*
        //Generate a JSON object of the event to be published
        JsonObject event = new JsonObject();
        event.addProperty("state", light_state);

        //Registered flow allows 0, 1 and 2 QoS
        myClient.publishEvent("light_status", event);*/
    	System.out.println("Message (DeviceType: "+deviceType+" Device ID: "+deviceId+" Command: "+command+" Data: "+data+")");
        System.out.println("SUCCESSFULLY POSTED......");
    }
    
    public void initClient(){

        //Properties to connect to the device as Application
		Properties options = new Properties();
		options.put("org", org);
		options.put("id", "Home_Automation_Control_Center");
		options.put("Authentication-Method","apikey");
		options.put("API-Key", apiKey);
		options.put("Authentication-Token", apiToken);
        

		try {
			myClient = new ApplicationClient(options);
			myClient.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//subscribe for status updates
		//myClient.setStatusCallback(new MyStatusCallback());
		//myClient.subscribeToDeviceStatus(deviceType, deviceId);
		
		//subscribe for event updates
		myClient.setEventCallback(evCallBack);
		myClient.subscribeToDeviceEvents(deviceType, deviceId);
		try {
			myClient.connect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
    }
}
