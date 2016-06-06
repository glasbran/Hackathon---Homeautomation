var IoTDevice = require("./IoTDevice.js");
var fs = require('fs');

var devices = [];

// Read the file and send to the callback
fs.readFile('config.json', (err, data) => {
    console.log("Reading configuration...");

    if (err) {
        console.log("Error while reading config");
        throw err;
    }

    const config = JSON.parse(data);

    for (var deviceConfig of config) {
        console.log("Found configuration for device " + deviceConfig.iotDeviceName + " of class " + deviceConfig.iotDeviceClass + " using " + deviceConfig.platformClass + " platform");
        var PlatformModule,
            DeviceModule;
        
        switch(deviceConfig.iotDeviceClass) {
            case "WEMO":
                DeviceModule = require("./devices/wemo.js");
                break;
            default:
                console.log("Unknown device class: " + deviceConfig.iotDeviceClass);
                break;
        }

        switch(deviceConfig.platformClass) {
            case "BLUEMIX":
                PlatformModule = require("./platform/bluemix.js");
                break;
            default:
                console.log("Unknown platform class: " + deviceConfig.platformClass);
                break;
        }
        
        devices.push(new IoTDevice(deviceConfig, DeviceModule, PlatformModule));
    }
    workerThread(devices);
});

function workerThread(iotDevices) {
    console.log("Finished setup, starting worker...");
    for(var device of iotDevices) {
        device.connect();
    }
}
