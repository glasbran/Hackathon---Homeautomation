/**
 * @author Tim Ebner
 * @date 06.06.2016
 */
package logic;

import com.ibm.iotf.client.app.Event;
import com.ibm.iotf.client.app.EventCallback;
import com.ibm.iotf.client.app.Command;

public class MyEventCallback implements EventCallback {
	public static boolean status_light_1 = false;
    public void processEvent(Event e) {
        System.out.println("Event:: " + e.getDeviceId() + ":" + e.getEvent() + ":" + e.getPayload());
        if (e.getDeviceId().equals("Test_Device_Rest")){
        	if (e.getPayload().contains("light_on")){
        		status_light_1 = true;
        	}else{
        		status_light_1 = false;
        	}
        }
    }

    public void processCommand(Command cmd) {
        System.out.println("Command " + cmd.getPayload());
    }
    
    public static String getLightStatusMessage(){
        	if (status_light_1){
        		return "The light is currently on";
        	}else{
        		return "The light is currently off";
        	}
    }
}
