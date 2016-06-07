# Hackathon Homeautomation
This is the repository to provide the results of the hackathon at the Hermann-Hollerith-Zentrum in Böblingen, Germany. The functions were implemented during a two day hackathon as a part of the lecture "Internet of Things".

# Task
The task was to create a MVP (Minimum Viable Product) for an home automation system, (Provided Hardware is listet in the [Wiki](https://github.com/glasbran/Hackathon---Homeautomation/wiki/Product-Description-and-Criticism)), publish the results on a public repository and use IBM BlueMix as IoT platform.

# Long Term Solution
We created an architecture which connects the given devices to a Rasberry Pi. The Pi serves as a client which runs a Node.JS skript that sends device data to the BlueMix platform. Device management is running on BlueMix as well as a WebApp with which the devices can be triggered.

# Goals for the Hackathon
The goal of the hackathon is to connect at least one of the given devices to the BlueMix platform to proof the possibility of the service. This device will send event data to the platform which will display this sent data. The WebApp will be able to trigger the device and turn it "on" or "off".
