var Wemo = require('wemo-client');
var wemo = new Wemo();
console.log("Searching for devices...");
wemo.load("http://192.168.10.50:49152/setup.xml", function(deviceInfo) {
  console.log('Wemo Device Found: %j', deviceInfo);

  // Get the client for the found device
  var client = wemo.client(deviceInfo);

  // Handle BinaryState events
  client.on('binaryState', function(value) {
    console.log('Binary State changed to: %s', value);
  });

  // Turn the switch on
  client.setBinaryState(1);
});
