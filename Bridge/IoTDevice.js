class IoTDevice {
    
// Constructor with configuration parameters, in order to connect with platform
    constructor(type,
                id,
                token) {

        var Client = require("./lib/iot-nodejs");

        const that = this;
        const org = "hazv7k";
        const authMethod = "token";

        var config = {
            "org" : org,
            "id" : id,
            "type" : type,
            "auth-method" : authMethod,
            "auth-token" : token
        };

        this._deviceClient = new Client.IotfDevice(config);
        this._deviceClient.connect();
        this._deviceClient.log.setLevel('trace');

        this._deviceClient.on('connect', this.onConnect.bind(this));
        this._deviceClient.on('command', this.onCommand.bind(this));
    }
    
    onConnect() {
        console.log("Connected device!");
        this._deviceClient.publish("status","json",'{"d" : { "cpu" : 60, "mem" : 50 }}'); 
    }

    onCommand(commandName, format, payload, topic) {
        console.log("Received command:");
        console.log("   Name: " + commandName);
        console.log("   Format: " + format);
        console.log("   Payload: " + payload);
        console.log("   Topic: " + topic);   
    }

}
module.exports = IoTDevice;
