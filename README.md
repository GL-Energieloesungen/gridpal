# Project Overview
GL Energy Cloud 2.0 was developed as an intelligent measurement and analysis system and offers real added value compared to conventional monitoring systems. The core competence of this solution is the collection of energy consumption data, the secure transmission of this data to a central server and the subsequent application of various, more efficient data analysis techniques to depict energy flows. The system can be used both in companies and in public buildings (e.g. administrative buildings, sports facilities, etc.). With the GL Energy Cloud 2.0 it is possible to identify energy efficiency potential or to check the success of implemented energy efficiency measures. The system includes both a hardware and a software solution.

## Data Collection 
The GL Energy Cloud 2.0 supports various wired and wireless communication protocols to allow a broad application for data logging and controlling functions. It facilitates the logging of any environmental, consumption, user or status data via connected devices such as meters and sensors (for continuous measurement), actors or programmable controllers (SPS). It also supports the control of such connected devices through its on-/offboard interfaces/protocols. Currently Supported interfaces to collect data are

- ModBus
- Mbus
- Wireless Mbus
- KNX

Bellow are the details of the hardware and interfaces

- Mainboard: Quad-Core ARM Cortex A53 Processor (64bit),Built-in Crypto-Engine, Ethernet 10/100/1000 Mbps (see Allwinner A64 documentation)
- Supplemental PCB board: Bluetooth Low Energy (BLE, 4.2 compliant), LAN min. 2x Gigabit Ethernet, Wifi 802.11n 2.4GhZ / 802.11ac 5 Ghz, 1x Modbus RS485 connector, 1x M-bus connector, 1x wireless M-bus, 1x KNX/EIB connector
- Peripherals: 4x USB (1x external, 3x internal), UART / PIN connector, SD Card slot, Smart Card slot (encryption private key)
- Connectivity (offboard, optional via USB dongles): LTE Modem, ZigBee, Enocean, Z-Wafe, Q-Wave
- Connectivity (offboard, optional via separate PCB Board on PIN Interface): PCB Board with MCU, 2x S0 analogue Input (Impulse), 4x analogue   Input for Sensor Typs KTY (2 kΩ) and PT1000, 4x analogue Input 0-10V (1 mA), 4x analogue Output (opener/ closer relais potentialfree), 4x analogue Output 0-10V (1 mA) switchable to PWM
- Operating System: LINUX full distribution (headless), support for JAVA, Pythoon


## Data Transfer
The GL Energy Cloud 2.0 uses mqtt as the transfer mechanism to transfer the collected data to a distributed mqtt broker.

## Data analysis
The GL Energy Cloud 2.0 can provide data feed to any third party data analysis tool

## Hardware
    All the schematics and pcb layout are designed in Kicad, an open source Electronic Design Automation (EDA) tool 
    https://www.kicad.org/
    There are 2 PCBA to be manufactured.
        - Com Board [https://github.com/GL-Energieloesungen/gridpal/tree/main/Hardware/Com%20Board]
        - Main Board [https://github.com/GL-Energieloesungen/gridpal/tree/main/Hardware/Main%20Board]
    To Manufacture the PCBA you have to open the respective folders in kicad tool and have to generate the gerber files for each board.
    One can easily follow kicad tool's documentation to generate the gerber. For [example](https://support.jlcpcb.com/article/44-how-to-export-kicad-pcb-to-gerber-files) 
    
## Software
    Software part consist of opensource Linux OS available at https://www.armbian.com/ and apps based on openhab platform (https://www.openhab.org/docs/)
    Graphincal User Interface is developed with angular js, Html and CS
    
