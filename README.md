# Hackathon Homeautomation
This is the repository to provide the results of the hackathon at the Hermann-Hollerith-Zentrum in Böblingen, Germany. The functions were implemented during a two day hackathon as a part of the lecture "Internet of Things".

# Task
Create MVP (Minimum Viable Product) for a Home Automation System, (Provided Hardware is listet in the [Wiki](https://github.com/glasbran/Hackathon---Homeautomation/wiki/Product-Description-and-Criticism)), publish the results on a public repository, use IBM BlueMix as IoT platform.

# Long Term Solution
We created  a architecture which connects the given devices to a Rasberry Pi. The Pi serves as a client which runs a Node.JS skript that sends the device data to the BlueMix platform. Devicemanagent is running on BlueMix as well as a WebApp whith which the devices can be triggered.

# Goals for the Hackathon
The Goal of the Hackathon is to connect at least one of the given devices to the BlueMix platform to proof the possibility of the service. This device will send event data to the platform which will display sent data. The WebApp will be able to trigger the device and turn it "on" or "off".
