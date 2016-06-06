// This module contains helper functions to connect to Belkin Wemo devices.

// Events will be emitted to "onDeviceConnect" and "onDeviceStateChange"

// @param config: The configuration, containing at least the key 'iotDeviceIP'
exports.connect = (that, config) => {
    var Wemo = require('wemo-client');
    var wemo = new Wemo();

    // The following while-loop + variables is a port scanner, determining which port is currently used by the wemo device
    var net = require('net');

    // Only documented in the 49152-5 range, but extending the range due to prototyping nature of this project
    var start = 49150;
    var end = 49160;
    var timeout = 2000;
   
    while (start <= end) {
    
        var currentPort = start;
    
        (function(currentPort) {
            console.log('Checking: ' + currentPort);
            var s = new net.Socket();
        
            s.setTimeout(timeout, function() { 
                // console.log("Timeout on port " + currentPort);
                s.destroy(); 
            });

            s.connect(currentPort, config.iotDeviceIP, function() {
                console.log('OPEN: ' + currentPort);
                // Connect to device
                console.log("Loading device...");
                wemo.load("http://" + config.iotDeviceIP + ":" + currentPort + "/setup.xml", (deviceInfo) => {
                    console.log("Wemo Device found: " + deviceInfo.friendlyName);
                        
                    var client = wemo.client(deviceInfo); 
                    that.onDeviceConnect(client);
                    client.on('binaryState', (value) => { that.onDeviceStateChange(value); });
                    client.on('error', (err) => { console.log(err); });
                });
            });
        
            s.on('error', function(e) {
                // silently catch all errors - assume the port is closed
                s.destroy();
            });
        })(currentPort);
    
        start++;
    }
}

exports.toggle = (that) => {
    if(that._device && that._status.state != -1) {
        var newState = (that._status.state == 1 ? 0 : 1);
        console.log("New state: " + newState);
        that._DeviceModule.updateState(that);
        that._device.setBinaryState(newState);
    } else {
        console.log("Unable to toggle state");
    } 
}

exports.updateState = (that) => {
    if(that._device) {
        that._device.getBinaryState((err, state) => {
            if(err) {
                console.log("Unable to update state: " + err);
            } else {
                that.onDeviceStateChange(state);
            }
        });
    } 
}
