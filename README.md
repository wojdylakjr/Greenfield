#Greenfield#

Project for designing a distributed and pervasive system for management of cleaning robots in the smart city of Greenfield. These robots are tasked with cleaning the streets across four districts in the city. The project aims to create a system that handles robot registration, monitors air pollution levels, coordinates maintenance activities, and provides pollution statistics to the city's environmental department.

Parts of application:

##MQTT Broker## 
This component manages the communication infrastructure between the cleaning robots and the central system. It enables the robots to periodically transmit air pollution measurements to the Administrator Server via MQTT protocol.

##Cleaning Robot##
The cleaning robot application simulates individual cleaning robots in Greenfield. Each robot is equipped with a pollution sensor that gathers air pollution measurements. These measurements are periodically sent to the Administrator Server through the MQTT Broker. The robots also coordinate maintenance activities among themselves using **gRPC communication** and a distributed **Ricart-Agrawala mutual exclusion algorithm**.

##Administrator Server##
The Administrator Server is responsible for various tasks. It dynamically manages the registration and removal of cleaning robots from the network. The server collects air pollution measurements transmitted by the robots through the MQTT Broker. It processes and stores these measurements for further analysis. Additionally, the Administrator Server computes pollution statistics and offers REST interfaces for managing the robot network.

##Administrator Client##
This application provides a command-line interface for administrators to interact with the Administrator Server. Administrators can request various services, such as obtaining a list of cleaning robots currently active in Greenfield or querying pollution statistics. The Administrator Client acts as a user-friendly interface to access and utilize the information collected and processed by the Administrator Server.

