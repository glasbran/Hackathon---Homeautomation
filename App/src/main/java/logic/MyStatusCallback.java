/**
 * @author Tim Ebner
 * @date 06.06.2016
 */
package logic;

import com.ibm.iotf.client.app.ApplicationStatus;
import com.ibm.iotf.client.app.DeviceStatus;
import com.ibm.iotf.client.app.StatusCallback;

class MyStatusCallback implements StatusCallback {

    public void processApplicationStatus(ApplicationStatus status) {
        System.out.println("Application Status = " + status.getPayload());
    }

    public void processDeviceStatus(DeviceStatus status) {
        if(status.getAction() == "Disconnect") {
            System.out.println("device: "+status.getDeviceId()
                                + "  time: "+ status.getTime()
                                + "  action: " + status.getAction()
                                + "  reason: " + status.getReason());
        } else {
            System.out.println("device: "+status.getDeviceId()
                                + "  time: "+ status.getTime()
                                + "  action: " + status.getAction());
        }
    }
}
