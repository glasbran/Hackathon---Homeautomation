var IoTDevice = require("./IoTDevice.js");

var device = new IoTDevice("FakeDevice", "FakeDevice_1", "n(1lx5sFsd3v1N4HJO", "192.168.10.50");
device.connectPlatform();
device.connectDevice();
