class IoTDevice {
    
// Constructor with configuration parameters, in order to connect with platform
    constructor(type,
                id,
                token,
                deviceIP) {
        this._type = type;
        this._id = id;
        this._token = token;
        this._deviceIP = deviceIP;
    }

    connectDevice() {
        const Device = require('./devices/wemo.js');
        Device.connect(this, this._deviceIP);
    }

    onDeviceConnect(iotDevice) {
        console.log("Successfully connected IoT device");
        this._iotDevice = iotDevice;
    }

    onDeviceStateChange(value) {
        console.log('Device turned %s', value === '1' ? 'on' : 'off');
    }
   
    connectPlatform() {
        const Platform = require('./platform/bluemix.js');
        this._platform = Platform.connect(this, this._type, this._id, this_token);
    }
 
    onPlatformConnect() {
        console.log("Connected device with platform!");
        //this._deviceClient.publish("status","json",'{"d" : { "cpu" : 60, "mem" : 50 }}'); 
    }

    onPlatformCommand(commandName, format, payload, topic) {
        console.log("Received command:");
        console.log("   Name: " + commandName);
        console.log("   Format: " + format);
        console.log("   Payload: " + payload);
        console.log("   Topic: " + topic);   
    }

    onPlatformDisconnect() {
        console.log("Disconnected from the platform");
    }

}
module.exports = IoTDevice;
