/**
 * @author Tim Ebner
 * @date 06.06.2016
 */
package logic;

import com.ibm.iotf.client.app.ApplicationClient;

import java.util.Properties;

import org.eclipse.paho.client.mqttv3.MqttException;

public class Subscriber {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		clientConnect();

	}
	
	public static void clientConnect(){
		Properties options = new Properties();
		options.put("org", "awwf5o");
		options.put("id", "app" + 1);
		options.put("Authentication-Method","apikey");
		options.put("API-Key", "a-awwf5o-nreha8bmdv");
		options.put("Authentication-Token", "05E8oP14&qm83Q)V4_");
		
		/*
 
      
      
      {
  "iotf-service": [
    {
      "name": "Internet of Things Platform-d4",
      "label": "iotf-service",
      "plan": "iotf-service-free",
      "credentials": {
        "iotCredentialsIdentifier": "a2g6k39sl6r5",
        "mqtt_host": "awwf5o.messaging.internetofthings.ibmcloud.com",
        "mqtt_u_port": 1883,
        "mqtt_s_port": 8883,
        "base_uri": "https://awwf5o.internetofthings.ibmcloud.com:443/api/v0001",
        "http_host": "awwf5o.internetofthings.ibmcloud.com",
        "org": "awwf5o",
        "apiKey": "a-awwf5o-nreha8bmdv",
        "apiToken": "05E8oP14&qm83Q)V4_"
      }
    }
  ]
}
		 */

		

		ApplicationClient myClient = null;
		try {
			myClient = new ApplicationClient(options);
			myClient.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		myClient.setStatusCallback(new MyStatusCallback());
		//myClient.subscribeToDeviceStatus("iotsample-ardunio", "10aabbccddee");
		myClient.subscribeToDeviceStatus();
		
		myClient.setEventCallback(new MyEventCallback());
		myClient.subscribeToDeviceEvents();
		try {
			myClient.connect();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
