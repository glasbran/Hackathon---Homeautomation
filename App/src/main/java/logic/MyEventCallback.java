/**
 * @author Tim Ebner
 * @date 06.06.2016
 */
package logic;

import com.ibm.iotf.client.app.Event;
import com.ibm.iotf.client.app.EventCallback;
import com.ibm.iotf.client.app.Command;

public class MyEventCallback implements EventCallback {
	String deviceId;
	public MyEventCallback(String deviceId){
		this.deviceId = deviceId;
	}
	
	public boolean connected = false;
	public boolean state = false;
    public void processEvent(Event e) {
        System.out.println("Event:: " + e.getDeviceId() + ":" + e.getEvent() + ":" + e.getPayload());
        
        //Check if the device is the right one
        if (e.getDeviceId().equals(deviceId)){
        	//Check if the device is connected
        	if (e.getPayload().contains("\"connected\":\"0\"")){
        		connected = false;
        		state = false;
        	}else{
        		connected = true;
        		//Check if state is on
        		if (e.getPayload().contains("\"state\":\"1\"")){
        			state = true;
        		} else{
        			state = false;
        		}
        	}
        }
    }

    public void processCommand(Command cmd) {
        System.out.println("Command " + cmd.getPayload());
    }
    
    public String getLightStatusMessage(){
    	String statusString = "";
    	if (connected){
    		statusString = "The switch (" + deviceId + ") is connected";
    	}else{
    		statusString = "The switch (" + deviceId + ") is NOT connected";
    	}
    	if (state){
    		statusString += " and currently turned on";
    	} else{
    		statusString += " and currently turned off";
    	}
    	System.out.println("Status update: "+statusString);
        return statusString;	
    }
}
