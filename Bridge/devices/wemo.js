// This module contains helper functions to connect to Belkin Wemo devices.

// Events will be emitted to "onDeviceConnect" and "onDeviceStateChange"

// This function connects the device specified by its IP to "that".
exports.connect = (that, deviceIP) => {
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
            console.log('Checking: ' + port);
            var s = new net.Socket();
        
            s.setTimeout(timeout, function() { s.destroy(); });
            s.connect(currentPort, host, function() {
                console.log('OPEN: ' + currentPort);
                // Connect to device
                wemo.load("http://" + deviceIP + ":" + currentPort + "/setup.xml", (deviceInfo) => {
                    console.log("Wemo Device found: %j", deviceInfo);

                    var client = wemo.client(deviceInfo); 
                    that.onDeviceConnect(client);
                    client.on('binaryState', that.onDeviceStateChange.bind(that));
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
