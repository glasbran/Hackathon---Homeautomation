class IoTDevice {
    
// Constructor with configuration parameters, in order to connect with platform
    // @param config: A JSON configuration, as required by the device and platform modules.
    // @param device: The IoT device module suitable for this object.
    // @param platform: The IoT platform module suitable for this object.
    constructor(config,
                DeviceModule,
                PlatformModule) {
        this._config = config;
        this._DeviceModule = DeviceModule;
        this._PlatformModule = PlatformModule;
        this._status = { connected: 0, state: -1 };
    }

    // This function will connect the RPI with the platform first. After connecting to the platform, the device will be connected upon onPlatformConnected.
    connect() {
       this.connectPlatform(); 
    }

    connectDevice() {
        console.log("Connecting IoT device " + this._config.iotDeviceName);
        this._DeviceModule.connect(this, this._config);
    }

    onDeviceConnect(iotDevice) {
        console.log("Successfully connected IoT device " + this._config.iotDeviceName);
        this._device = iotDevice;
        this.setStatus(1, -1);
    }

    onDeviceStateChange(value) {
        console.log("Device " + this._config.iotDeviceName + " turned %s", value === '1' ? 'on' : 'off');
        this.setStatus(1, value);
    }
   
    connectPlatform() {
        console.log("Connecting device " + this._config.iotDeviceName + " to platform");
        this._PlatformModule.connect(this, this._config);
    }
 
    onPlatformConnect(iotPlatform) {
        console.log("Connected device " + this._config.iotDeviceName + " with platform!");
        this._platform = iotPlatform;
        this.connectDevice();
    }

    onPlatformCommand(command) {
        console.log("Device " + this._config.iotDeviceName + " received command " + command);
        switch(command) {
            case "toggle_state":
                this._DeviceModule.toggle(this);
                break;
            case "get_state":
                this._DeviceModule.updateState(this);
                break;
            default: 
                console.log("Unknown command received: " + command);
                break;
        }
    }

    onPlatformDisconnect() {
        console.log("Device " + this._config.iotDeviceName + " disconnected from the platform");
        delete this._platform;
    }

    // This function sets the status and publishes it to the IoT platform, if available.
    setStatus(connected, state) {
        this._status = { "connected": connected, "state": state };
        if(this._platform) {
            this._PlatformModule.publishStatus(this._platform, this._status);
        }
    }

}
module.exports = IoTDevice;
