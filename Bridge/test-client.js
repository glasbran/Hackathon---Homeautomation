var Client = require("./lib/iot-nodejs");

var appClientConfig = {
    "org" : "hazv7k",
    "id" : "FakeClient",
    "type" : "Fake_Client",
    "auth-key" : "a-hazv7k-jn6bpnrbcb",
    "auth-token" : "707aQZ1g)xo0_@yJYw"
};

var appClient = new Client.IotfApplication(appClientConfig);

appClient.connect();

appClient.on("connect", function () {
    console.log("Connected, publishing command");
    var myData={'DelaySeconds' : 10};
    myData = JSON.stringify(myData);
    appClient.publishDeviceCommand("FakeDevice","FakeDevice_1", "reboot", "json", myData);

});
