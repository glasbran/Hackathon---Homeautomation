// This module contains helper functions to connect a device to the IBM Bluemix platform

// Events will be emitted to "onPlatformConnect" "onPlatformDisconnect" and "onPlatformCommand"

// @param config: The configuration for the platform, containing at least the keys "type", "id" and "token", as defined by BlueMix.
exports.connect = (that, config) => {
    console.log("Connecting " + config.platformId + " to BlueMix platform...");

    const Client = require("../lib/iot-nodejs");

    const org = "awwf5o";
    const authMethod = "token";
//    const logLevel = "trace";
//    deviceClient.log.setLevel(logLevel);

    var platformConfig = {
        "org" : org,
        "id" : config.platformId,
        "type" : config.platformDeviceType,
        "auth-method" : authMethod,
        "auth-token" : config.platformToken
    };

    var deviceClient = new Client.IotfDevice(platformConfig);
    deviceClient.connect();

    deviceClient.on('connect', () => { that.onPlatformConnect(deviceClient); } );
    deviceClient.on('command', (commandName, format, payload, topic) =>  that.onPlatformCommand(commandName));
    deviceClient.on('disconnect', that.onPlatformDisconnect.bind(that));
}

// Using the "deviceClient", the "status" will be published to the BlueMix Platform
exports.publishStatus = (deviceClient, status) => {
    console.log("Publishing status: " + JSON.stringify(status));
    deviceClient.publish("status", 
                         "json",
                         JSON.stringify(status));
}
