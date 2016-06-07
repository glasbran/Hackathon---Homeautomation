/**
 * @author Tim Ebner
 * @date 06.06.2016
 */
package logic;

import java.util.Properties;

import org.eclipse.paho.client.mqttv3.MqttException;

import com.google.gson.JsonObject;
import com.ibm.iotf.client.device.DeviceClient;

public class LightChanger {
    public static void changeState( String light_state )
    {

        //Provide the device specific data, as well as Auth-key and token using Properties class
        Properties options = new Properties();

        options.setProperty("org", "awwf5o");
        options.setProperty("type", "Test_Device_Rest");
        options.setProperty("id", "Test_Device_Rest");
        options.setProperty("auth-method", "token");
        options.setProperty("auth-token", "Test_Device_Rest_Token");
        
        

        DeviceClient myClient = null;
        try {
                //Instantiate the class by passing the properties file
                myClient = new DeviceClient(options);
        } catch (Exception e) {
                e.printStackTrace();
        }

        //Connect to the IBM IoT Platform
        try {
			myClient.connect();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        //Generate a JSON object of the event to be published
        JsonObject event = new JsonObject();
        event.addProperty("state", light_state);

        //Registered flow allows 0, 1 and 2 QoS
        myClient.publishEvent("light_status", event);
        System.out.println("SUCCESSFULLY POSTED......");
    }
}
