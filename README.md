# Project Overview
The Main idea behind gridpal is to help customer tracking energy usage in his/her factory premises from different machieneries and then try to optimize the usage by analyzing the usage data. So Core competent of this project is the collection of energy usage data, trasnfer of this data securely to central server and later apply different efficient data analysis technique to suggest customer the necessary changes in the factory to achieve optimum enegry consumption hence save costs.

## Data Collection 
The gridpal supports various wired and wireless communication protocols to allow a broad application for data logging and controlling functions. It facilitates the logging of any environmental, consumption, user or status data via connected devices such as meters and sensors (for continuous measurement), actors or programmable controllers (SPS). It also supports the control of such connected devices through its on-/offboard interfaces/protocols. Currently Supported interfaces to collect data are as bellows

- ModBus
- Mbus
- Wireless Mbus
- KNX

Bellows are the details of the hardware and interfaces

- Mainboard: Quad-Core ARM Cortex A53 Processor (64bit),Built-in Crypto-Engine, Ethernet 10/100/1000 Mbps (see Allwinner A64 documentation)
- Supplemental PCB board: Bluetooth Low Energy (BLE, 4.2 compliant), LAN min. 2x Gigabit Ethernet, Wifi 802.11n 2.4GhZ / 802.11ac 5 Ghz, 1x Modbus RS485 connector, 1x M-bus connector, 1x wireless M-bus, 1x KNX/EIB connector
- Peripherals: 4x USB (1x external, 3x internal), UART / PIN connector, SD Card slot, Smart Card slot (encryption private key)
- Connectivity (offboard, optional via USB dongles): LTE Modem, ZigBee, Enocean, Z-Wafe, Q-Wave
- Connectivity (offboard, optional via separate PCB Board on PIN Interface): PCB Board with MCU, 2x S0 analogue Input (Impulse), 4x analogue   Input for Sensor Typs KTY (2 kΩ) and PT1000, 4x analogue Input 0-10V (1 mA), 4x analogue Output (opener/ closer relais potentialfree), 4x analogue Output 0-10V (1 mA) switchable to PWM
- Operating System: LINUX full distribution (headless), support for JAVA, Pythoon


## Data Transfer
The gridpal uses mqtt as the transfer mechanism to transfer the collected data to a distributed mqtt broker.

## Data analysis
The gridpal can provide data feed to any third party data analysis tool

## Hardware
    All the schematics and pcb layout are designed in Kicad, an open source Electronic Design Automation (EDA) tool 
    https://www.kicad.org/
## Software
    Software part consist of opensource Linux OS available at https://www.armbian.com/ and apps based on openhab platform (https://www.openhab.org/docs/)
    Graphincal User Interface is developed with angular js, Html and CS