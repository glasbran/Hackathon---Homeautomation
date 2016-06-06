// This module contains helper functions to connect a device to the IBM Bluemix platform

// Events will be emitted to "onPlatformConnect" "onPlatformDisconnect" and "onPlatformCommand"

// This function connects the IoT device object "that" to the platform. "type", "id" and "token" are the variables required by BlueMix
// The deviceClient object of the IBM library will be returned.
exports.connect = (that, type, id, token) => {
    const Client = require("../lib/iot-nodejs");

    const org = "hazv7k";
    const authMethod = "token";

    var config = {
        "org" : org,
        "id" : id,
        "type" : type,
        "auth-method" : authMethod,
        "auth-token" : token
    };

    var deviceClient = new Client.IotfDevice(config);
    deviceClient.connect();
    deviceClient.log.setLevel('trace');

    deviceClient.on('connect', that.onPlatformConnect.bind(that));
    deviceClient.on('command', that.onPlatformCommand.bind(that));
    deviceClient.on('disconnect', that.onPlatformDisconnect.bind(that));
    
    return deviceClient;
}
