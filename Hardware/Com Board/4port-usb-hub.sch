EESchema Schematic File Version 4
LIBS:4port-usb-hub-cache
EELAYER 26 0
EELAYER END
$Descr A2 23386 16535
encoding utf-8
Sheet 1 1
Title ""
Date ""
Rev ""
Comp ""
Comment1 ""
Comment2 ""
Comment3 ""
Comment4 ""
$EndDescr
$Comp
L 4port-usb-hub-rescue:USB_A-RESCUE-4port-usb-hub P2
U 1 1 57203432
P 2200 2050
F 0 "P2" H 2400 1850 50  0000 C CNN
F 1 "USB_A" H 2150 2250 50  0000 C CNN
F 2 "UCM:USB_A_Vertical" V 2150 1950 50  0001 C CNN
F 3 "" V 2150 1950 50  0000 C CNN
	1    2200 2050
	1    0    0    -1  
$EndComp
Text GLabel 2200 2350 3    60   Input ~ 0
D4_1+
Text GLabel 2100 2350 3    60   Input ~ 0
D4_1-
$Comp
L 4port-usb-hub-rescue:GND #PWR01
U 1 1 57254FCE
P 2300 2850
F 0 "#PWR01" H 2300 2600 50  0001 C CNN
F 1 "GND" H 2300 2700 50  0000 C CNN
F 2 "" H 2300 2850 50  0000 C CNN
F 3 "" H 2300 2850 50  0000 C CNN
	1    2300 2850
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:GND #PWR02
U 1 1 57267B3C
P 1500 3100
F 0 "#PWR02" H 1500 2850 50  0001 C CNN
F 1 "GND" H 1500 2950 50  0000 C CNN
F 2 "" H 1500 3100 50  0000 C CNN
F 3 "" H 1500 3100 50  0000 C CNN
	1    1500 3100
	0    1    1    0   
$EndComp
$Comp
L 4port-usb-hub-rescue:GND #PWR03
U 1 1 5727727D
P 4650 1150
F 0 "#PWR03" H 4650 900 50  0001 C CNN
F 1 "GND" H 4650 1000 50  0000 C CNN
F 2 "" H 4650 1150 50  0000 C CNN
F 3 "" H 4650 1150 50  0000 C CNN
	1    4650 1150
	-1   0    0    1   
$EndComp
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C13
U 1 1 5727C918
P 1750 2400
F 0 "C13" H 1775 2500 50  0000 L CNN
F 1 "0.1uF" H 1775 2300 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603" H 1788 2250 50  0001 C CNN
F 3 "" H 1750 2400 50  0000 C CNN
	1    1750 2400
	0    -1   -1   0   
$EndComp
$Comp
L 4port-usb-hub-rescue:GND #PWR04
U 1 1 5727CAAA
P 1500 2400
F 0 "#PWR04" H 1500 2150 50  0001 C CNN
F 1 "GND" H 1500 2250 50  0000 C CNN
F 2 "" H 1500 2400 50  0000 C CNN
F 3 "" H 1500 2400 50  0000 C CNN
	1    1500 2400
	0    1    1    0   
$EndComp
$Comp
L 4port-usb-hub-rescue:CP C2
U 1 1 57C0F8AF
P 1700 3100
F 0 "C2" H 1725 3200 50  0000 L CNN
F 1 "100uF/16V/LOWESR/105C" H 1200 3000 50  0000 L CNN
F 2 "Capacitors_SMD:CP_Elec_6.3x5.3" H 1738 2950 50  0001 C CNN
F 3 "" H 1700 3100 50  0000 C CNN
	1    1700 3100
	0    1    1    0   
$EndComp
$Comp
L 4port-usb-hub-rescue:USB_A-RESCUE-4port-usb-hub P1
U 1 1 57C1169C
P 3700 2050
F 0 "P1" H 3900 1850 50  0000 C CNN
F 1 "USB_A" H 3650 2250 50  0000 C CNN
F 2 "UCM:USB_A_Vertical" V 3650 1950 50  0001 C CNN
F 3 "" V 3650 1950 50  0000 C CNN
	1    3700 2050
	1    0    0    -1  
$EndComp
Text GLabel 3700 2350 3    60   Input ~ 0
D2_1+
Text GLabel 3600 2350 3    60   Input ~ 0
D2_1-
$Comp
L 4port-usb-hub-rescue:GND #PWR05
U 1 1 57C116A5
P 3800 2850
F 0 "#PWR05" H 3800 2600 50  0001 C CNN
F 1 "GND" H 3800 2700 50  0000 C CNN
F 2 "" H 3800 2850 50  0000 C CNN
F 3 "" H 3800 2850 50  0000 C CNN
	1    3800 2850
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:GND #PWR06
U 1 1 57C116AE
P 3000 3100
F 0 "#PWR06" H 3000 2850 50  0001 C CNN
F 1 "GND" H 3000 2950 50  0000 C CNN
F 2 "" H 3000 3100 50  0000 C CNN
F 3 "" H 3000 3100 50  0000 C CNN
	1    3000 3100
	0    1    1    0   
$EndComp
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C5
U 1 1 57C116BF
P 3250 2400
F 0 "C5" H 3275 2500 50  0000 L CNN
F 1 "0.1uF" H 3275 2300 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603" H 3288 2250 50  0001 C CNN
F 3 "" H 3250 2400 50  0000 C CNN
	1    3250 2400
	0    -1   -1   0   
$EndComp
$Comp
L 4port-usb-hub-rescue:GND #PWR07
U 1 1 57C116C7
P 3000 2400
F 0 "#PWR07" H 3000 2150 50  0001 C CNN
F 1 "GND" H 3000 2250 50  0000 C CNN
F 2 "" H 3000 2400 50  0000 C CNN
F 3 "" H 3000 2400 50  0000 C CNN
	1    3000 2400
	0    1    1    0   
$EndComp
$Comp
L 4port-usb-hub-rescue:CP C3
U 1 1 57C116CE
P 3200 3100
F 0 "C3" H 3225 3200 50  0000 L CNN
F 1 "100uF/16V/LOWESR/105C" H 2700 3000 50  0000 L CNN
F 2 "Capacitors_SMD:CP_Elec_6.3x5.3" H 3238 2950 50  0001 C CNN
F 3 "" H 3200 3100 50  0000 C CNN
	1    3200 3100
	0    1    1    0   
$EndComp
$Comp
L 4port-usb-hub-rescue:USB_A-RESCUE-4port-usb-hub P3
U 1 1 57C120F9
P 5100 2050
F 0 "P3" H 5300 1850 50  0000 C CNN
F 1 "USB_A" H 5050 2250 50  0000 C CNN
F 2 "UCM:USB_A_Vertical" V 5050 1950 50  0001 C CNN
F 3 "" V 5050 1950 50  0000 C CNN
	1    5100 2050
	1    0    0    -1  
$EndComp
Text GLabel 5100 2350 3    60   Input ~ 0
D3_1+
Text GLabel 5000 2350 3    60   Input ~ 0
D3_1-
$Comp
L 4port-usb-hub-rescue:GND #PWR08
U 1 1 57C12102
P 5200 2850
F 0 "#PWR08" H 5200 2600 50  0001 C CNN
F 1 "GND" H 5200 2700 50  0000 C CNN
F 2 "" H 5200 2850 50  0000 C CNN
F 3 "" H 5200 2850 50  0000 C CNN
	1    5200 2850
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:GND #PWR09
U 1 1 57C1210B
P 4400 3100
F 0 "#PWR09" H 4400 2850 50  0001 C CNN
F 1 "GND" H 4400 2950 50  0000 C CNN
F 2 "" H 4400 3100 50  0000 C CNN
F 3 "" H 4400 3100 50  0000 C CNN
	1    4400 3100
	0    1    1    0   
$EndComp
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C7
U 1 1 57C1211C
P 4650 2400
F 0 "C7" H 4675 2500 50  0000 L CNN
F 1 "0.1uF" H 4675 2300 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603" H 4688 2250 50  0001 C CNN
F 3 "" H 4650 2400 50  0000 C CNN
	1    4650 2400
	0    -1   -1   0   
$EndComp
$Comp
L 4port-usb-hub-rescue:GND #PWR010
U 1 1 57C12124
P 4400 2400
F 0 "#PWR010" H 4400 2150 50  0001 C CNN
F 1 "GND" H 4400 2250 50  0000 C CNN
F 2 "" H 4400 2400 50  0000 C CNN
F 3 "" H 4400 2400 50  0000 C CNN
	1    4400 2400
	0    1    1    0   
$EndComp
$Comp
L 4port-usb-hub-rescue:CP C6
U 1 1 57C1212B
P 4600 3100
F 0 "C6" H 4625 3200 50  0000 L CNN
F 1 "100uF/16V/LOWESR/105C" H 4100 3000 50  0000 L CNN
F 2 "Capacitors_SMD:CP_Elec_6.3x5.3" H 4638 2950 50  0001 C CNN
F 3 "" H 4600 3100 50  0000 C CNN
	1    4600 3100
	0    1    1    0   
$EndComp
$Comp
L 4port-usb-hub-rescue:GL850G U2
U 1 1 57C13BF8
P 5000 5350
F 0 "U2" H 5000 6450 60  0000 C CNN
F 1 "GL850G" H 5050 4200 60  0000 C CNN
F 2 "Housings_SSOP:SSOP-28_5.3x10.2mm_Pitch0.65mm" H 5150 4450 60  0001 C CNN
F 3 "" H 5150 4450 60  0001 C CNN
	1    5000 5350
	1    0    0    -1  
$EndComp
Text GLabel 5700 4400 2    60   Input ~ 0
D1_1+
Text GLabel 5700 4550 2    60   Input ~ 0
D1_1-
Text GLabel 4300 4700 0    60   Input ~ 0
D2_1+
Text GLabel 4300 4550 0    60   Input ~ 0
D2_1-
Text GLabel 4300 5450 0    60   Input ~ 0
D3_1-
Text GLabel 4300 5600 0    60   Input ~ 0
D3_1+
Text GLabel 4300 5900 0    60   Input ~ 0
D4_1-
Text GLabel 4300 6050 0    60   Input ~ 0
D4_1+
$Comp
L 4port-usb-hub-rescue:GND #PWR011
U 1 1 57C151E2
P 4200 6350
F 0 "#PWR011" H 4200 6100 50  0001 C CNN
F 1 "GND" H 4200 6200 50  0000 C CNN
F 2 "" H 4200 6350 50  0000 C CNN
F 3 "" H 4200 6350 50  0000 C CNN
	1    4200 6350
	0    1    1    0   
$EndComp
$Comp
L 4port-usb-hub-rescue:GND #PWR012
U 1 1 57C152E9
P 5800 6350
F 0 "#PWR012" H 5800 6100 50  0001 C CNN
F 1 "GND" H 5800 6200 50  0000 C CNN
F 2 "" H 5800 6350 50  0000 C CNN
F 3 "" H 5800 6350 50  0000 C CNN
	1    5800 6350
	0    -1   -1   0   
$EndComp
NoConn ~ 5700 5300
NoConn ~ 5700 5600
NoConn ~ 5700 5750
$Comp
L Device:Crystal_GND24 Y1
U 1 1 57C15F45
P 3600 5200
F 0 "Y1" H 3600 5350 50  0000 C CNN
F 1 "12Mhz" H 3600 5050 50  0000 C CNN
F 2 "Crystals:Crystal_SMD_3225-4pin_3.2x2.5mm" H 3600 5200 50  0001 C CNN
F 3 "" H 3600 5200 50  0000 C CNN
	1    3600 5200
	0    1    1    0   
$EndComp
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C10
U 1 1 57C160B2
P 3100 5050
F 0 "C10" H 3125 5150 50  0000 L CNN
F 1 "20pF" H 3125 4950 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603" H 3138 4900 50  0001 C CNN
F 3 "" H 3100 5050 50  0000 C CNN
	1    3100 5050
	0    1    1    0   
$EndComp
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C14
U 1 1 57C16171
P 3100 5350
F 0 "C14" H 3125 5450 50  0000 L CNN
F 1 "20pF" H 3125 5250 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603" H 3138 5200 50  0001 C CNN
F 3 "" H 3100 5350 50  0000 C CNN
	1    3100 5350
	0    1    1    0   
$EndComp
$Comp
L 4port-usb-hub-rescue:GND #PWR013
U 1 1 57C165FB
P 2650 5200
F 0 "#PWR013" H 2650 4950 50  0001 C CNN
F 1 "GND" H 2650 5050 50  0000 C CNN
F 2 "" H 2650 5200 50  0000 C CNN
F 3 "" H 2650 5200 50  0000 C CNN
	1    2650 5200
	0    1    1    0   
$EndComp
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R20
U 1 1 57C1787F
P 5900 5900
F 0 "R20" V 5980 5900 50  0000 C CNN
F 1 "47k" V 5900 5900 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" V 5830 5900 50  0001 C CNN
F 3 "" H 5900 5900 50  0000 C CNN
	1    5900 5900
	0    1    1    0   
$EndComp
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R21
U 1 1 57C17A3A
P 5900 6050
F 0 "R21" V 5980 6050 50  0000 C CNN
F 1 "10k" V 5900 6050 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" V 5830 6050 50  0001 C CNN
F 3 "" H 5900 6050 50  0000 C CNN
	1    5900 6050
	0    1    1    0   
$EndComp
Text Label 5700 5000 0    60   ~ 0
V33_1
Text Label 6200 6000 0    60   ~ 0
V33_1
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C22
U 1 1 57C18370
P 6650 5150
F 0 "C22" H 6675 5250 50  0000 L CNN
F 1 "10uF/6.3V" V 6675 5050 50  0000 R TNN
F 2 "Capacitors_SMD:C_0603" H 6688 5000 50  0001 C CNN
F 3 "" H 6650 5150 50  0000 C CNN
	1    6650 5150
	0    -1   -1   0   
$EndComp
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C23
U 1 1 57C187BE
P 6650 5350
F 0 "C23" H 6675 5450 50  0000 L CNN
F 1 "100nF" V 6675 5250 50  0000 R TNN
F 2 "Capacitors_SMD:C_0603" H 6688 5200 50  0001 C CNN
F 3 "" H 6650 5350 50  0000 C CNN
	1    6650 5350
	0    -1   -1   0   
$EndComp
$Comp
L 4port-usb-hub-rescue:GND #PWR014
U 1 1 57C188FC
P 7000 5150
F 0 "#PWR014" H 7000 4900 50  0001 C CNN
F 1 "GND" H 7000 5000 50  0000 C CNN
F 2 "" H 7000 5150 50  0000 C CNN
F 3 "" H 7000 5150 50  0000 C CNN
	1    7000 5150
	0    -1   -1   0   
$EndComp
Text Label 4300 5750 2    60   ~ 0
AVDD_1
Text Label 4300 5000 2    60   ~ 0
AVDD_1
Text Label 4300 4400 2    60   ~ 0
AVDD_1
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C17
U 1 1 57C19C01
P 1950 6250
F 0 "C17" H 1975 6350 50  0000 L CNN
F 1 "100nF" V 1975 6150 50  0000 R TNN
F 2 "Capacitors_SMD:C_0603" H 1988 6100 50  0001 C CNN
F 3 "" H 1950 6250 50  0000 C CNN
	1    1950 6250
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C18
U 1 1 57C19CFB
P 2200 6250
F 0 "C18" H 2225 6350 50  0000 L CNN
F 1 "100nF" V 2225 6150 50  0000 R TNN
F 2 "Capacitors_SMD:C_0603" H 2238 6100 50  0001 C CNN
F 3 "" H 2200 6250 50  0000 C CNN
	1    2200 6250
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C20
U 1 1 57C19D6A
P 2450 6250
F 0 "C20" H 2475 6350 50  0000 L CNN
F 1 "100nF" V 2475 6150 50  0000 R TNN
F 2 "Capacitors_SMD:C_0603" H 2488 6100 50  0001 C CNN
F 3 "" H 2450 6250 50  0000 C CNN
	1    2450 6250
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:Inductor-FerriteBead_EN60617_04-03-10_Date20121108-RESCUE-pirinel-alpha L7
U 1 1 57C1B0C9
P 1700 5750
F 0 "L7" H 1700 5925 50  0000 C CNN
F 1 "600R/2A" V 1725 5575 50  0000 C BNN
F 2 "Inductors_SMD:L_0805" H 1700 5750 50  0001 C CNN
F 3 "" H 1700 5750 50  0000 C CNN
	1    1700 5750
	1    0    0    -1  
$EndComp
Text Label 2600 5750 0    60   ~ 0
AVDD_1
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C15
U 1 1 57C1BB00
P 1250 6300
F 0 "C15" H 1275 6400 50  0000 L CNN
F 1 "100nF" V 1275 6200 50  0000 R TNN
F 2 "Capacitors_SMD:C_0603" H 1288 6150 50  0001 C CNN
F 3 "" H 1250 6300 50  0000 C CNN
	1    1250 6300
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C16
U 1 1 57C1BC3D
P 1450 6300
F 0 "C16" H 1475 6400 50  0000 L CNN
F 1 "10uF/6.3V" V 1475 6200 50  0000 R TNN
F 2 "Capacitors_SMD:C_0603" H 1488 6150 50  0001 C CNN
F 3 "" H 1450 6300 50  0000 C CNN
	1    1450 6300
	1    0    0    -1  
$EndComp
Text Label 1250 5751 1    60   ~ 0
V33_1
$Comp
L 4port-usb-hub-rescue:GND #PWR015
U 1 1 57C1C7B2
P 1750 6750
F 0 "#PWR015" H 1750 6500 50  0001 C CNN
F 1 "GND" H 1750 6600 50  0000 C CNN
F 2 "" H 1750 6750 50  0000 C CNN
F 3 "" H 1750 6750 50  0000 C CNN
	1    1750 6750
	1    0    0    -1  
$EndComp
Text Label 5700 6200 0    60   ~ 0
V33_1
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R25
U 1 1 57C1D3FE
P 3900 4850
F 0 "R25" V 3980 4850 50  0000 C CNN
F 1 "680R/1%" V 3900 4850 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" V 3830 4850 50  0001 C CNN
F 3 "" H 3900 4850 50  0000 C CNN
	1    3900 4850
	0    1    1    0   
$EndComp
$Comp
L 4port-usb-hub-rescue:GND #PWR016
U 1 1 57C1D5C0
P 3650 4850
F 0 "#PWR016" H 3650 4600 50  0001 C CNN
F 1 "GND" H 3650 4700 50  0000 C CNN
F 2 "" H 3650 4850 50  0000 C CNN
F 3 "" H 3650 4850 50  0000 C CNN
	1    3650 4850
	0    1    1    0   
$EndComp
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C21
U 1 1 57C1DA2C
P 3700 6650
F 0 "C21" H 3725 6750 50  0000 L CNN
F 1 "1uF" V 3725 6550 50  0000 R TNN
F 2 "Capacitors_SMD:C_0603" H 3738 6500 50  0001 C CNN
F 3 "" H 3700 6650 50  0000 C CNN
	1    3700 6650
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R24
U 1 1 57C1DBA1
P 3500 6650
F 0 "R24" V 3580 6650 50  0000 C CNN
F 1 "47k" V 3500 6650 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" V 3430 6650 50  0001 C CNN
F 3 "" H 3500 6650 50  0000 C CNN
	1    3500 6650
	-1   0    0    1   
$EndComp
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R23
U 1 1 57C1DC67
P 3300 6650
F 0 "R23" V 3380 6650 50  0000 C CNN
F 1 "10k" V 3300 6650 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" V 3230 6650 50  0001 C CNN
F 3 "" H 3300 6650 50  0000 C CNN
	1    3300 6650
	-1   0    0    1   
$EndComp
$Comp
L 4port-usb-hub-rescue:GND #PWR017
U 1 1 57C1E0D2
P 3600 7050
F 0 "#PWR017" H 3600 6800 50  0001 C CNN
F 1 "GND" H 3600 6900 50  0000 C CNN
F 2 "" H 3600 7050 50  0000 C CNN
F 3 "" H 3600 7050 50  0000 C CNN
	1    3600 7050
	1    0    0    -1  
$EndComp
Text Label 3500 3700 3    60   ~ 0
USBHUB_5V
Text Label 4900 3700 3    60   ~ 0
USBHUB_5V
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R26
U 1 1 57C227D1
P 6650 5750
F 0 "R26" V 6730 5750 50  0000 C CNN
F 1 "10k" V 6650 5750 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" V 6580 5750 50  0001 C CNN
F 3 "" H 6650 5750 50  0000 C CNN
	1    6650 5750
	0    1    1    0   
$EndComp
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R27
U 1 1 57C228DF
P 7150 5750
F 0 "R27" V 7230 5750 50  0000 C CNN
F 1 "5.1k" V 7150 5750 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" V 7080 5750 50  0001 C CNN
F 3 "" H 7150 5750 50  0000 C CNN
	1    7150 5750
	0    1    1    0   
$EndComp
$Comp
L 4port-usb-hub-rescue:GND #PWR021
U 1 1 57C22987
P 6400 5750
F 0 "#PWR021" H 6400 5500 50  0001 C CNN
F 1 "GND" H 6400 5600 50  0000 C CNN
F 2 "" H 6400 5750 50  0000 C CNN
F 3 "" H 6400 5750 50  0000 C CNN
	1    6400 5750
	0    1    1    0   
$EndComp
Text Label 7302 5750 0    60   ~ 0
USBHUB_5V
$Comp
L 4port-usb-hub-rescue:USB_B-RESCUE-4port-usb-hub P5
U 1 1 57C34230
P 6450 1850
F 0 "P5" H 6650 1650 50  0000 C CNN
F 1 "USB_B" H 6400 2050 50  0000 C CNN
F 2 "UCM:USB_DIP" V 6400 1750 50  0001 C CNN
F 3 "" V 6400 1750 50  0000 C CNN
	1    6450 1850
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:GND #PWR022
U 1 1 57C35251
P 6550 2850
F 0 "#PWR022" H 6550 2600 50  0001 C CNN
F 1 "GND" H 6550 2700 50  0000 C CNN
F 2 "" H 6550 2850 50  0000 C CNN
F 3 "" H 6550 2850 50  0000 C CNN
	1    6550 2850
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:GND #PWR023
U 1 1 57C355DD
P 7200 1750
F 0 "#PWR023" H 7200 1500 50  0001 C CNN
F 1 "GND" H 7200 1600 50  0000 C CNN
F 2 "" H 7200 1750 50  0000 C CNN
F 3 "" H 7200 1750 50  0000 C CNN
	1    7200 1750
	0    -1   -1   0   
$EndComp
Text GLabel 5700 4700 2    60   Input ~ 0
D0_1+
Text GLabel 5700 4850 2    60   Input ~ 0
D0_1-
Text GLabel 6350 2150 3    60   Input ~ 0
D0_1-
Text GLabel 6450 2150 3    60   Input ~ 0
D0_1+
Text Label 6250 2490 3    60   ~ 0
USBHUB_5V
Text Label 3300 6950 2    60   ~ 0
USBHUB_5V
$Comp
L 4port-usb-hub-rescue:FT232RL U4
U 1 1 59DB7A5C
P 15110 3300
F 0 "U4" H 14460 4200 50  0000 L CNN
F 1 "FT232RL" H 15510 4200 50  0000 L CNN
F 2 "Housings_SSOP:SSOP-28_5.3x10.2mm_Pitch0.65mm" H 15110 3300 50  0001 C CNN
F 3 "" H 15110 3300 50  0001 C CNN
	1    15110 3300
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:MAX485E U7
U 1 1 59DB8065
P 18210 3200
F 0 "U7" H 17970 3650 50  0000 C CNN
F 1 "MAX485E" H 18240 3650 50  0000 L CNN
F 2 "Housings_SOIC:SOIC-8_3.9x4.9mm_Pitch1.27mm" H 18210 2500 50  0001 C CNN
F 3 "" H 18210 3250 50  0001 C CNN
	1    18210 3200
	1    0    0    -1  
$EndComp
Text GLabel 12860 2900 0    60   Input ~ 0
D1_1+
Text GLabel 12860 3050 0    60   Input ~ 0
D1_1-
$Comp
L 4port-usb-hub-rescue:Ferrite_Bead_Small L6
U 1 1 59DCDD9B
P 15210 1700
F 0 "L6" H 15285 1750 50  0000 L CNN
F 1 "Ferrite_Bead_Small" H 15285 1650 50  0000 L CNN
F 2 "Inductors_SMD:L_0805" V 15140 1700 50  0001 C CNN
F 3 "" H 15210 1700 50  0001 C CNN
	1    15210 1700
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C43
U 1 1 59DCF30D
P 16160 1900
F 0 "C43" H 16185 2000 50  0000 L CNN
F 1 "0.1uF" H 16185 1800 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603" H 16198 1750 50  0001 C CNN
F 3 "" H 16160 1900 50  0000 C CNN
	1    16160 1900
	0    -1   -1   0   
$EndComp
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C44
U 1 1 59DCFBC9
P 16160 2150
F 0 "C44" H 16185 2250 50  0000 L CNN
F 1 "10uF" H 16185 2050 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603" H 16198 2000 50  0001 C CNN
F 3 "" H 16160 2150 50  0000 C CNN
	1    16160 2150
	0    -1   -1   0   
$EndComp
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C41
U 1 1 59DD16E1
P 14060 1900
F 0 "C41" H 14085 2000 50  0000 L CNN
F 1 "0.1uF" H 14085 1800 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603" H 14098 1750 50  0001 C CNN
F 3 "" H 14060 1900 50  0000 C CNN
	1    14060 1900
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:GND #PWR049
U 1 1 59DD20FF
P 16560 2100
F 0 "#PWR049" H 16560 1850 50  0001 C CNN
F 1 "GND" H 16560 1950 50  0000 C CNN
F 2 "" H 16560 2100 50  0001 C CNN
F 3 "" H 16560 2100 50  0001 C CNN
	1    16560 2100
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:GND #PWR050
U 1 1 59DD2535
P 13810 1850
F 0 "#PWR050" H 13810 1600 50  0001 C CNN
F 1 "GND" H 13810 1700 50  0000 C CNN
F 2 "" H 13810 1850 50  0001 C CNN
F 3 "" H 13810 1850 50  0001 C CNN
	1    13810 1850
	1    0    0    -1  
$EndComp
Text Label 16010 3800 0    60   ~ 0
TXDEN1
Text Label 17310 3250 0    60   ~ 0
TXDEN1
Text Label 16010 2600 0    60   ~ 0
TXD1
Text Label 16010 2700 0    60   ~ 0
RXD1
Text Label 17310 3100 0    60   ~ 0
RXD1
Text Label 17310 3400 0    60   ~ 0
TXD1
$Comp
L 4port-usb-hub-rescue:GND #PWR051
U 1 1 59DD9988
P 14910 4550
F 0 "#PWR051" H 14910 4300 50  0001 C CNN
F 1 "GND" H 14910 4400 50  0000 C CNN
F 2 "" H 14910 4550 50  0001 C CNN
F 3 "" H 14910 4550 50  0001 C CNN
	1    14910 4550
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:LED D3
U 1 1 59DE1826
P 16610 3250
F 0 "D3" H 16610 3350 50  0000 C CNN
F 1 "LED" H 16610 3150 50  0000 C CNN
F 2 "LEDs:LED_0603_HandSoldering" H 16610 3250 50  0001 C CNN
F 3 "" H 16610 3250 50  0001 C CNN
	1    16610 3250
	0    -1   -1   0   
$EndComp
$Comp
L 4port-usb-hub-rescue:LED D5
U 1 1 59DE1AE1
P 17010 3250
F 0 "D5" H 17010 3350 50  0000 C CNN
F 1 "LED" H 17010 3150 50  0000 C CNN
F 2 "LEDs:LED_0603_HandSoldering" H 17010 3250 50  0001 C CNN
F 3 "" H 17010 3250 50  0001 C CNN
	1    17010 3250
	0    -1   -1   0   
$EndComp
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R11
U 1 1 59DE42CF
P 17010 2850
F 0 "R11" V 17090 2850 50  0000 C CNN
F 1 "330" V 17010 2850 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" V 16940 2850 50  0001 C CNN
F 3 "" H 17010 2850 50  0000 C CNN
	1    17010 2850
	-1   0    0    1   
$EndComp
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R9
U 1 1 59DE5369
P 16610 2850
F 0 "R9" V 16690 2850 50  0000 C CNN
F 1 "330" V 16610 2850 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" V 16540 2850 50  0001 C CNN
F 3 "" H 16610 2850 50  0000 C CNN
	1    16610 2850
	-1   0    0    1   
$EndComp
Wire Wire Line
	2300 2350 2300 2850
Wire Wire Line
	1500 3100 1550 3100
Wire Wire Line
	1850 3100 2000 3100
Connection ~ 2000 3100
Wire Wire Line
	2000 2350 2000 2400
Wire Wire Line
	1900 2400 2000 2400
Connection ~ 2000 2400
Wire Wire Line
	1500 2400 1600 2400
Wire Wire Line
	4650 1150 4650 1650
Wire Wire Line
	3800 2350 3800 2850
Wire Wire Line
	3000 3100 3050 3100
Wire Wire Line
	3350 3100 3500 3100
Connection ~ 3500 3100
Wire Wire Line
	3500 2350 3500 2400
Wire Wire Line
	3400 2400 3500 2400
Connection ~ 3500 2400
Wire Wire Line
	3000 2400 3100 2400
Wire Wire Line
	5200 2350 5200 2850
Wire Wire Line
	4400 3100 4450 3100
Wire Wire Line
	4750 3100 4900 3100
Connection ~ 4900 3100
Wire Wire Line
	4900 2350 4900 2400
Wire Wire Line
	4800 2400 4900 2400
Connection ~ 4900 2400
Wire Wire Line
	4400 2400 4500 2400
Wire Wire Line
	2500 1950 2500 1650
Wire Wire Line
	4000 1650 4000 1950
Wire Wire Line
	5400 1650 5400 1950
Connection ~ 4000 1650
Connection ~ 4650 1650
Wire Wire Line
	4200 6350 4300 6350
Wire Wire Line
	5700 6350 5800 6350
Wire Wire Line
	2750 5050 2750 5200
Wire Wire Line
	2750 5050 2950 5050
Wire Wire Line
	2750 5350 2950 5350
Connection ~ 2750 5200
Wire Wire Line
	4000 5050 4000 5150
Wire Wire Line
	4000 5150 4300 5150
Wire Wire Line
	4000 5350 4000 5300
Wire Wire Line
	4000 5300 4300 5300
Wire Wire Line
	2750 5200 2650 5200
Wire Wire Line
	5700 5900 5750 5900
Wire Wire Line
	5700 6050 5750 6050
Wire Wire Line
	6050 5900 6200 5900
Wire Wire Line
	6200 5900 6200 6050
Wire Wire Line
	6200 6050 6050 6050
Wire Wire Line
	5700 5150 6100 5150
Wire Wire Line
	6800 5150 6900 5150
Wire Wire Line
	6350 5150 6350 5350
Wire Wire Line
	6350 5350 6500 5350
Connection ~ 6350 5150
Wire Wire Line
	6800 5350 6900 5350
Wire Wire Line
	6900 5350 6900 5150
Connection ~ 6900 5150
Wire Wire Line
	6100 5100 6100 5150
Connection ~ 6100 5150
Wire Wire Line
	1850 5750 1950 5750
Wire Wire Line
	1950 5750 1950 6100
Wire Wire Line
	2200 5750 2200 6100
Connection ~ 1950 5750
Wire Wire Line
	2450 5750 2450 6100
Connection ~ 2200 5750
Connection ~ 2450 5750
Wire Wire Line
	1250 5750 1450 5750
Wire Wire Line
	1250 5550 1250 5750
Wire Wire Line
	1450 6150 1450 5750
Connection ~ 1450 5750
Connection ~ 1250 5750
Wire Wire Line
	1250 6450 1250 6650
Wire Wire Line
	1250 6650 1450 6650
Wire Wire Line
	1450 6450 1450 6650
Connection ~ 1450 6650
Wire Wire Line
	1950 6650 1950 6400
Connection ~ 1750 6650
Wire Wire Line
	2200 6650 2200 6400
Connection ~ 1950 6650
Wire Wire Line
	2450 6650 2450 6400
Connection ~ 2200 6650
Wire Wire Line
	1750 6650 1750 6750
Wire Wire Line
	4050 4850 4300 4850
Wire Wire Line
	3650 4850 3750 4850
Wire Wire Line
	3300 6500 3300 6350
Wire Wire Line
	3300 6350 3500 6350
Wire Wire Line
	3500 6200 3500 6350
Wire Wire Line
	3700 6350 3700 6500
Connection ~ 3500 6350
Wire Wire Line
	4300 6200 3500 6200
Wire Wire Line
	3500 6800 3500 6950
Wire Wire Line
	3500 6950 3600 6950
Wire Wire Line
	3700 6950 3700 6800
Wire Wire Line
	3600 7050 3600 6950
Connection ~ 3600 6950
Wire Wire Line
	3300 6950 3300 6800
Wire Wire Line
	7300 5750 7400 5750
Wire Wire Line
	6800 5750 6900 5750
Wire Wire Line
	5700 5450 6900 5450
Wire Wire Line
	6900 5450 6900 5750
Connection ~ 6900 5750
Wire Wire Line
	6400 5750 6500 5750
Wire Wire Line
	6750 1750 7200 1750
Wire Wire Line
	15110 4400 15110 4300
Wire Wire Line
	14310 4400 14910 4400
Wire Wire Line
	15210 4400 15210 4300
Wire Wire Line
	15310 4400 15310 4300
Connection ~ 15210 4400
Wire Wire Line
	14310 4000 14310 4400
Connection ~ 15110 4400
Wire Wire Line
	14910 4300 14910 4400
Connection ~ 14910 4400
Wire Wire Line
	12860 3050 13260 3050
Wire Wire Line
	15210 1800 15210 1900
Wire Wire Line
	16010 1900 15210 1900
Connection ~ 15210 1900
Wire Wire Line
	15010 2150 15210 2150
Connection ~ 15210 2150
Wire Wire Line
	16310 1900 16410 1900
Wire Wire Line
	16410 1900 16410 2000
Wire Wire Line
	16410 2150 16310 2150
Wire Wire Line
	16560 2000 16560 2100
Wire Wire Line
	16560 2000 16410 2000
Connection ~ 16410 2000
Wire Wire Line
	14060 2600 14310 2600
Wire Wire Line
	14060 1750 13810 1750
Wire Wire Line
	13810 1750 13810 1850
Wire Wire Line
	15910 3800 16510 3800
Wire Wire Line
	17810 3200 17710 3200
Wire Wire Line
	17710 3200 17710 3250
Wire Wire Line
	17710 3300 17810 3300
Wire Wire Line
	17210 3250 17710 3250
Connection ~ 17710 3250
Wire Wire Line
	17810 3400 17210 3400
Wire Wire Line
	17810 3100 17210 3100
Wire Wire Line
	15910 2600 16460 2600
Wire Wire Line
	15910 2700 16460 2700
Wire Wire Line
	15010 2150 15010 2300
Wire Wire Line
	16610 3000 16610 3100
Wire Wire Line
	17010 3000 17010 3100
Wire Wire Line
	16610 3400 16610 3600
Wire Wire Line
	16610 3600 15910 3600
Wire Wire Line
	15910 3700 17010 3700
Wire Wire Line
	17010 3700 17010 3400
Wire Wire Line
	16610 2700 17010 2700
Text Label 16660 2700 0    60   ~ 0
VCCIO1
$Comp
L 4port-usb-hub-rescue:Ferrite_Bead_Small L15
U 1 1 59DF3BF4
P 18210 2350
F 0 "L15" H 18285 2400 50  0000 L CNN
F 1 "Ferrite_Bead_Small" H 18285 2300 50  0000 L CNN
F 2 "Inductors_SMD:L_0805" V 18140 2350 50  0001 C CNN
F 3 "" H 18210 2350 50  0001 C CNN
	1    18210 2350
	1    0    0    -1  
$EndComp
Wire Wire Line
	18210 2450 18210 2600
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C47
U 1 1 59DF43C8
P 17660 2300
F 0 "C47" H 17685 2400 50  0000 L CNN
F 1 "0.1uF" H 17685 2200 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603" H 17698 2150 50  0001 C CNN
F 3 "" H 17660 2300 50  0000 C CNN
	1    17660 2300
	0    -1   -1   0   
$EndComp
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C48
U 1 1 59DF4695
P 17660 2600
F 0 "C48" H 17685 2700 50  0000 L CNN
F 1 "10uF" H 17685 2500 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603" H 17698 2450 50  0001 C CNN
F 3 "" H 17660 2600 50  0000 C CNN
	1    17660 2600
	0    -1   -1   0   
$EndComp
Wire Wire Line
	18210 2450 18010 2450
Wire Wire Line
	18010 2450 18010 2300
Wire Wire Line
	18010 2300 17810 2300
Wire Wire Line
	17810 2600 18210 2600
Connection ~ 18210 2600
Wire Wire Line
	17510 2300 17510 2450
$Comp
L 4port-usb-hub-rescue:GND #PWR055
U 1 1 59DF4EF8
P 17360 2450
F 0 "#PWR055" H 17360 2200 50  0001 C CNN
F 1 "GND" H 17360 2300 50  0000 C CNN
F 2 "" H 17360 2450 50  0001 C CNN
F 3 "" H 17360 2450 50  0001 C CNN
	1    17360 2450
	1    0    0    -1  
$EndComp
Wire Wire Line
	17360 2450 17510 2450
Connection ~ 17510 2450
$Comp
L 4port-usb-hub-rescue:GND #PWR056
U 1 1 59DF55DF
P 18210 3900
F 0 "#PWR056" H 18210 3650 50  0001 C CNN
F 1 "GND" H 18210 3750 50  0000 C CNN
F 2 "" H 18210 3900 50  0001 C CNN
F 3 "" H 18210 3900 50  0001 C CNN
	1    18210 3900
	1    0    0    -1  
$EndComp
Wire Wire Line
	18210 3800 18210 3850
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R13
U 1 1 59DF6D31
P 18910 3100
F 0 "R13" V 18990 3100 50  0000 C CNN
F 1 "10" V 18910 3100 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" V 18840 3100 50  0001 C CNN
F 3 "" H 18910 3100 50  0000 C CNN
	1    18910 3100
	0    -1   -1   0   
$EndComp
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R14
U 1 1 59DF6F9F
P 18910 3400
F 0 "R14" V 18990 3400 50  0000 C CNN
F 1 "10" V 18910 3400 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" V 18840 3400 50  0001 C CNN
F 3 "" H 18910 3400 50  0000 C CNN
	1    18910 3400
	0    -1   -1   0   
$EndComp
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R15
U 1 1 59DF75AB
P 19610 2850
F 0 "R15" V 19690 2850 50  0000 C CNN
F 1 "390" V 19610 2850 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" V 19540 2850 50  0001 C CNN
F 3 "" H 19610 2850 50  0000 C CNN
	1    19610 2850
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R16
U 1 1 59DF77DB
P 19610 3650
F 0 "R16" V 19690 3650 50  0000 C CNN
F 1 "390" V 19610 3650 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" V 19540 3650 50  0001 C CNN
F 3 "" H 19610 3650 50  0000 C CNN
	1    19610 3650
	1    0    0    -1  
$EndComp
Wire Wire Line
	18610 3100 18760 3100
Wire Wire Line
	18610 3400 18760 3400
Wire Wire Line
	19060 3400 19610 3400
Wire Wire Line
	20110 3400 20110 3250
Wire Wire Line
	20110 3250 20360 3250
Wire Wire Line
	19060 3100 19610 3100
Wire Wire Line
	20110 3100 20110 3150
Wire Wire Line
	20110 3150 20460 3150
Wire Wire Line
	19610 3000 19610 3100
Connection ~ 19610 3100
Wire Wire Line
	19610 3500 19610 3400
Connection ~ 19610 3400
Wire Wire Line
	19610 3800 19610 3850
Connection ~ 18210 3850
Wire Wire Line
	19610 2600 19610 2700
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R17
U 1 1 59DF8EF3
P 20010 2750
F 0 "R17" V 20090 2750 50  0000 C CNN
F 1 "120" V 20010 2750 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" V 19940 2750 50  0001 C CNN
F 3 "" H 20010 2750 50  0000 C CNN
	1    20010 2750
	0    1    1    0   
$EndComp
$Comp
L 4port-usb-hub-rescue:Conn_01x02 J7
U 1 1 59DF90AA
P 20260 2500
F 0 "J7" H 20260 2600 50  0000 C CNN
F 1 "Conn_01x02" H 20260 2300 50  0000 C CNN
F 2 "Pin_Headers:Pin_Header_Straight_1x02_Pitch2.54mm" H 20260 2500 50  0001 C CNN
F 3 "" H 20260 2500 50  0001 C CNN
	1    20260 2500
	0    -1   -1   0   
$EndComp
Wire Wire Line
	20260 2700 20260 2750
Wire Wire Line
	20260 2750 20160 2750
Wire Wire Line
	19860 2750 19860 3100
Connection ~ 19860 3100
Wire Wire Line
	20360 2700 20360 3250
Connection ~ 20360 3250
Wire Wire Line
	19610 2600 19760 2600
Wire Wire Line
	19760 2600 19760 3950
Wire Wire Line
	19760 3950 18410 3950
Wire Wire Line
	18410 3950 18410 3850
Wire Wire Line
	18410 3850 18210 3850
Wire Wire Line
	19610 3850 19310 3850
Wire Wire Line
	19310 3850 19310 2600
Wire Wire Line
	2500 1650 4000 1650
Wire Notes Line
	750  800  7850 800 
Wire Notes Line
	7850 800  7850 7500
Wire Notes Line
	7850 7500 750  7500
Wire Notes Line
	750  7500 750  800 
$Comp
L 4port-usb-hub-rescue:CP C9
U 1 1 59E1D305
P 5550 3400
F 0 "C9" H 5575 3500 50  0000 L CNN
F 1 "1000uF 10V" H 5575 3300 50  0000 L CNN
F 2 "Capacitors_SMD:CP_Elec_10x10.5" H 5588 3250 50  0001 C CNN
F 3 "" H 5550 3400 50  0001 C CNN
	1    5550 3400
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:CP C39
U 1 1 59E1DB9A
P 5800 3400
F 0 "C39" H 5825 3500 50  0000 L CNN
F 1 "100uF" H 5825 3300 50  0000 L CNN
F 2 "Capacitors_SMD:CP_Elec_6.3x4.5" H 5838 3250 50  0001 C CNN
F 3 "" H 5800 3400 50  0001 C CNN
	1    5800 3400
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C8
U 1 1 59E1DD9B
P 5250 3400
F 0 "C8" H 5275 3500 50  0000 L CNN
F 1 "0.1uF" H 5275 3300 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603" H 5288 3250 50  0001 C CNN
F 3 "" H 5250 3400 50  0000 C CNN
	1    5250 3400
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C40
U 1 1 59E1E117
P 6050 3400
F 0 "C40" H 6075 3500 50  0000 L CNN
F 1 "0.1uF" H 6075 3300 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603" H 6088 3250 50  0001 C CNN
F 3 "" H 6050 3400 50  0000 C CNN
	1    6050 3400
	1    0    0    -1  
$EndComp
Wire Wire Line
	5250 3100 5550 3100
Wire Wire Line
	6050 3100 6050 3250
Wire Wire Line
	5800 3100 5800 3250
Connection ~ 6050 3100
Wire Wire Line
	5250 3100 5250 3250
Connection ~ 5800 3100
Wire Wire Line
	5550 3250 5550 3100
Connection ~ 5550 3100
Wire Wire Line
	5250 3550 5550 3550
Connection ~ 5550 3550
Connection ~ 5800 3550
$Comp
L 4port-usb-hub-rescue:GND #PWR058
U 1 1 59E1F3CB
P 5700 3650
F 0 "#PWR058" H 5700 3400 50  0001 C CNN
F 1 "GND" H 5700 3500 50  0000 C CNN
F 2 "" H 5700 3650 50  0000 C CNN
F 3 "" H 5700 3650 50  0000 C CNN
	1    5700 3650
	1    0    0    -1  
$EndComp
Wire Wire Line
	5700 3650 5700 3550
Connection ~ 5700 3550
Text Label 14060 2250 0    60   ~ 0
VCCIO1
$Comp
L 4port-usb-hub-rescue:Ferrite_Bead_Small L11
U 1 1 59E74579
P 6250 4750
F 0 "L11" H 6325 4800 50  0000 L CNN
F 1 "Ferrite_Bead_Small" H 6325 4700 50  0000 L CNN
F 2 "Inductors_SMD:L_0805" V 6180 4750 50  0001 C CNN
F 3 "" H 6250 4750 50  0001 C CNN
	1    6250 4750
	1    0    0    -1  
$EndComp
Wire Wire Line
	6100 5100 6250 5100
Wire Wire Line
	6250 5100 6250 4850
Wire Wire Line
	6250 4650 6250 4250
Wire Wire Line
	6250 4250 6800 4250
Text Label 6400 4250 0    60   ~ 0
USBHUB_5V
Wire Wire Line
	2000 3100 2000 3250
Wire Wire Line
	3500 3100 3500 3250
Wire Wire Line
	4900 3100 4900 3250
Wire Wire Line
	4000 1650 4650 1650
Wire Wire Line
	4650 1650 5400 1650
Wire Wire Line
	2750 5200 2750 5350
Wire Wire Line
	6350 5150 6500 5150
Wire Wire Line
	6900 5150 7000 5150
Wire Wire Line
	6100 5150 6350 5150
Wire Wire Line
	1950 5750 2200 5750
Wire Wire Line
	2200 5750 2450 5750
Wire Wire Line
	2450 5750 2600 5750
Wire Wire Line
	1450 5750 1550 5750
Wire Wire Line
	1250 5750 1250 6150
Wire Wire Line
	1450 6650 1750 6650
Wire Wire Line
	1750 6650 1950 6650
Wire Wire Line
	1950 6650 2200 6650
Wire Wire Line
	2200 6650 2450 6650
Wire Wire Line
	3500 6350 3700 6350
Wire Wire Line
	3500 6350 3500 6500
Wire Wire Line
	3600 6950 3700 6950
Wire Wire Line
	6900 5750 7000 5750
Wire Wire Line
	15210 4400 15310 4400
Wire Wire Line
	15110 4400 15210 4400
Wire Wire Line
	14910 4400 15110 4400
Wire Wire Line
	14910 4400 14910 4550
Wire Wire Line
	15210 1900 15210 2150
Wire Wire Line
	15210 2150 16010 2150
Wire Wire Line
	15210 2150 15210 2300
Wire Wire Line
	16410 2000 16410 2150
Wire Wire Line
	17710 3250 17710 3300
Wire Wire Line
	18210 2600 18210 2700
Wire Wire Line
	18210 2600 19310 2600
Wire Wire Line
	17510 2450 17510 2600
Wire Wire Line
	19610 3100 19860 3100
Wire Wire Line
	19610 3400 20110 3400
Wire Wire Line
	18210 3850 18210 3900
Wire Wire Line
	19860 3100 20110 3100
Wire Wire Line
	20360 3250 20460 3250
Wire Wire Line
	6050 3100 6250 3100
Wire Wire Line
	5800 3100 6050 3100
Wire Wire Line
	5550 3100 5800 3100
Wire Wire Line
	5550 3550 5700 3550
Wire Wire Line
	5800 3550 6050 3550
Wire Wire Line
	5700 3550 5800 3550
Wire Wire Line
	14060 2050 14060 2600
Connection ~ 18210 2450
Wire Wire Line
	12860 2900 14310 2900
Wire Wire Line
	13260 3000 13260 3050
Wire Wire Line
	13260 3000 14310 3000
$Comp
L Connector_Generic:Conn_02x20_Odd_Even J1
U 1 1 5C190984
P 2491 9950
F 0 "J1" H 2541 11067 50  0000 C CNN
F 1 "Conn_02x20_Odd_Even" H 2541 10976 50  0000 C CNN
F 2 "Pin_Headers:Pin_Header_Straight_2x20_Pitch2.54mm" H 2491 9950 50  0001 C CNN
F 3 "~" H 2491 9950 50  0001 C CNN
	1    2491 9950
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR020
U 1 1 5C191D94
P 3041 9050
F 0 "#PWR020" H 3041 8800 50  0001 C CNN
F 1 "GND" V 3046 8922 50  0000 R CNN
F 2 "" H 3041 9050 50  0001 C CNN
F 3 "" H 3041 9050 50  0001 C CNN
	1    3041 9050
	0    -1   -1   0   
$EndComp
Wire Wire Line
	2291 9050 2041 9050
Wire Wire Line
	2291 9150 1791 9150
Wire Wire Line
	2791 9050 3041 9050
Wire Wire Line
	2291 9250 1791 9250
Wire Wire Line
	2291 9350 1791 9350
Wire Wire Line
	2791 9450 3141 9450
Wire Wire Line
	2791 9550 3141 9550
Text GLabel 1791 9250 0    50   Input ~ 0
UART3-RX
Text GLabel 1791 9350 0    50   Input ~ 0
UART3-TX
Text GLabel 3141 9450 2    50   Input ~ 0
UART2-TX
Text GLabel 3141 9550 2    50   Input ~ 0
UART2-RX
Wire Notes Line
	21160 4850 11710 4850
Wire Notes Line
	11710 4765 11710 1165
$Comp
L 4port-usb-hub-rescue:Conn_01x04 J2
U 1 1 5C230215
P 19915 9510
F 0 "J2" H 19995 9502 50  0000 L CNN
F 1 "MBUS" H 19995 9411 50  0000 L CNN
F 2 "Connectors_JST:JST_NV_B04P-NV_4x5.00mm_Vertical" H 19915 9510 50  0001 C CNN
F 3 "~" H 19915 9510 50  0001 C CNN
	1    19915 9510
	1    0    0    -1  
$EndComp
Wire Wire Line
	6250 2150 6250 3100
$Comp
L 4port-usb-hub-rescue:Conn_01x03 J3
U 1 1 5C8DCB23
P 20660 3250
F 0 "J3" H 20739 3292 50  0000 L CNN
F 1 "ModBUS" H 20739 3201 50  0000 L CNN
F 2 "Connectors_JST:JST_NV_B03P-NV_3x5.00mm_Vertical" H 20660 3250 50  0001 C CNN
F 3 "" H 20660 3250 50  0001 C CNN
	1    20660 3250
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:GND #PWR031
U 1 1 5C8DCD36
P 20360 3500
F 0 "#PWR031" H 20360 3250 50  0001 C CNN
F 1 "GND" H 20360 3350 50  0000 C CNN
F 2 "" H 20360 3500 50  0001 C CNN
F 3 "" H 20360 3500 50  0001 C CNN
	1    20360 3500
	1    0    0    -1  
$EndComp
Wire Wire Line
	20460 3350 20360 3350
Wire Wire Line
	20360 3350 20360 3500
Text Notes 20360 3050 0    50   ~ 0
3 pin Green Terminal
Text Notes 2091 8600 0    50   ~ 0
Connector from CPU board\n
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R36
U 1 1 5C0D6E5B
P 6550 2700
F 0 "R36" H 6620 2746 50  0000 L CNN
F 1 "0R" H 6620 2655 50  0000 L CNN
F 2 "Resistors_SMD:R_0603" H 6620 2609 50  0001 L CNN
F 3 "" H 6550 2700 50  0000 C CNN
	1    6550 2700
	1    0    0    -1  
$EndComp
Wire Wire Line
	6550 2150 6550 2550
Wire Wire Line
	19615 9410 19615 9610
Wire Wire Line
	19615 9610 19715 9610
Wire Wire Line
	19615 9410 19715 9410
Wire Wire Line
	19715 9510 19665 9510
Wire Wire Line
	19665 9510 19665 9710
Wire Wire Line
	19665 9710 19715 9710
$Comp
L 4port-usb-hub-rescue:BL8042B U8
U 1 1 5C26BB4B
P 3950 13250
F 0 "U8" H 4250 13415 50  0000 C CNN
F 1 "BL8042B" H 4250 13324 50  0000 C CNN
F 2 "TO_SOT_Packages_SMD:SOT-23-6" H 3850 13450 50  0001 C CNN
F 3 "" H 3850 13450 50  0001 C CNN
	1    3950 13250
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R40
U 1 1 5C26D0F3
P 4850 13550
F 0 "R40" H 4920 13596 50  0000 L CNN
F 1 "2K 1%" H 4920 13505 50  0000 L CNN
F 2 "Resistors_SMD:R_0603" H 4780 13550 50  0001 C CNN
F 3 "" H 4850 13550 50  0000 C CNN
	1    4850 13550
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R41
U 1 1 5C26D2E0
P 5350 13400
F 0 "R41" V 5143 13400 50  0000 C CNN
F 1 "100K 1%" V 5234 13400 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" H 5280 13400 50  0001 C CNN
F 3 "" H 5350 13400 50  0000 C CNN
	1    5350 13400
	0    1    1    0   
$EndComp
Wire Wire Line
	4650 13400 4850 13400
Connection ~ 4850 13400
Wire Wire Line
	4850 13400 5200 13400
Wire Wire Line
	4650 13700 4850 13700
$Comp
L 4port-usb-hub-rescue:GND #PWR0101
U 1 1 5C2A47CD
P 5100 13700
F 0 "#PWR0101" H 5100 13450 50  0001 C CNN
F 1 "GND" V 5105 13572 50  0000 R CNN
F 2 "" H 5100 13700 50  0001 C CNN
F 3 "" H 5100 13700 50  0001 C CNN
	1    5100 13700
	0    -1   -1   0   
$EndComp
Connection ~ 4850 13700
Wire Wire Line
	3850 13700 3700 13700
Wire Wire Line
	3700 13700 3700 14000
Wire Wire Line
	3700 14500 4050 14500
$Comp
L pspice:INDUCTOR L1
U 1 1 5C2DE2FE
P 4300 14500
F 0 "L1" H 4300 14715 50  0000 C CNN
F 1 "15uH" H 4300 14624 50  0000 C CNN
F 2 "Inductors_SMD:L_Taiyo-Yuden_MD-5050" H 4300 14500 50  0001 C CNN
F 3 "~" H 4300 14500 50  0001 C CNN
	1    4300 14500
	1    0    0    -1  
$EndComp
Wire Wire Line
	4650 14000 4850 14000
Wire Wire Line
	4850 14000 4850 14500
Wire Wire Line
	4850 14500 4550 14500
Wire Wire Line
	5500 13400 5650 13400
Wire Wire Line
	5650 13400 5650 14500
Connection ~ 5650 14500
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R39
U 1 1 5C407694
P 3500 13400
F 0 "R39" V 3293 13400 50  0000 C CNN
F 1 "100K" V 3384 13400 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" H 3430 13400 50  0001 C CNN
F 3 "" H 3500 13400 50  0000 C CNN
	1    3500 13400
	0    1    1    0   
$EndComp
Wire Wire Line
	3850 13400 3650 13400
Wire Wire Line
	3200 13400 3200 14000
Wire Wire Line
	3200 14000 3700 14000
Connection ~ 3700 14000
Wire Wire Line
	3700 14000 3700 14500
Connection ~ 3200 14000
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C19
U 1 1 5C4A5214
P 3200 14150
F 0 "C19" H 3315 14196 50  0000 L CNN
F 1 "22uF 10V" H 3315 14105 50  0000 L CNN
F 2 "Capacitors_SMD:C_1206" H 3238 14000 50  0001 C CNN
F 3 "" H 3200 14150 50  0000 C CNN
	1    3200 14150
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:GND #PWR0102
U 1 1 5C4A5547
P 3200 14500
F 0 "#PWR0102" H 3200 14250 50  0001 C CNN
F 1 "GND" H 3205 14327 50  0000 C CNN
F 2 "" H 3200 14500 50  0001 C CNN
F 3 "" H 3200 14500 50  0001 C CNN
	1    3200 14500
	1    0    0    -1  
$EndComp
Wire Wire Line
	3200 14300 3200 14500
Wire Wire Line
	3200 13400 3350 13400
Wire Wire Line
	4850 13700 5100 13700
Wire Wire Line
	5250 14500 5650 14500
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C25
U 1 1 5C5AC092
P 6500 14350
F 0 "C25" H 6615 14396 50  0000 L CNN
F 1 "100nF 50V" H 6615 14305 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603" H 6538 14200 50  0001 C CNN
F 3 "" H 6500 14350 50  0000 C CNN
	1    6500 14350
	1    0    0    -1  
$EndComp
Wire Wire Line
	5900 14200 6100 14200
Wire Wire Line
	6100 14200 6100 14050
Connection ~ 6100 14200
Wire Wire Line
	6100 14200 6500 14200
$Comp
L 4port-usb-hub-rescue:GND #PWR0103
U 1 1 5C60BBEF
P 6100 14050
F 0 "#PWR0103" H 6100 13800 50  0001 C CNN
F 1 "GND" H 6105 13877 50  0000 C CNN
F 2 "" H 6100 14050 50  0001 C CNN
F 3 "" H 6100 14050 50  0001 C CNN
	1    6100 14050
	-1   0    0    1   
$EndComp
Text Label 7100 14500 0    50   ~ 0
MBUS-V
Wire Notes Line
	1800 12000 1800 15050
Wire Notes Line
	1800 15050 7750 15050
Wire Notes Line
	7750 15050 7750 12000
Wire Notes Line
	7750 12000 1800 12000
Text Notes 5800 12650 0    197  ~ 0
5V to 30V
Wire Wire Line
	2550 14000 3200 14000
$Comp
L Device:Fuse F1
U 1 1 5C09D764
P 2400 12350
F 0 "F1" V 2203 12350 50  0000 C CNN
F 1 "Fuse" V 2294 12350 50  0000 C CNN
F 2 "Fuse_Holders_and_Fuses:Fuse_SMD1206_Reflow" V 2330 12350 50  0001 C CNN
F 3 "~" H 2400 12350 50  0001 C CNN
	1    2400 12350
	0    1    1    0   
$EndComp
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R37
U 1 1 5C09D913
P 2850 12550
F 0 "R37" V 2643 12550 50  0000 C CNN
F 1 "270K" V 2734 12550 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" H 2780 12550 50  0001 C CNN
F 3 "" H 2850 12550 50  0000 C CNN
	1    2850 12550
	-1   0    0    1   
$EndComp
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R38
U 1 1 5C0BECC8
P 2850 13000
F 0 "R38" V 2643 13000 50  0000 C CNN
F 1 "470K" V 2734 13000 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" H 2780 13000 50  0001 C CNN
F 3 "" H 2850 13000 50  0000 C CNN
	1    2850 13000
	-1   0    0    1   
$EndComp
$Comp
L 4port-usb-hub-rescue:GND #PWR0104
U 1 1 5C0BEF9A
P 2850 13250
F 0 "#PWR0104" H 2850 13000 50  0001 C CNN
F 1 "GND" H 2855 13077 50  0000 C CNN
F 2 "" H 2850 13250 50  0001 C CNN
F 3 "" H 2850 13250 50  0001 C CNN
	1    2850 13250
	1    0    0    -1  
$EndComp
Wire Wire Line
	2550 12350 2850 12350
Wire Wire Line
	2850 12350 2850 12400
Wire Wire Line
	2850 12700 2850 12800
Wire Wire Line
	2850 13150 2850 13250
Wire Wire Line
	2850 12800 3650 12800
Connection ~ 2850 12800
Wire Wire Line
	2850 12800 2850 12850
Wire Wire Line
	2291 9450 1791 9450
Text Label 1791 9450 0    50   ~ 0
Fuse-sense
Text Label 3250 12800 0    50   ~ 0
Fuse-sense
Wire Wire Line
	2550 12350 2550 14000
$Comp
L Device:CP C24
U 1 1 5C267832
P 5900 14350
F 0 "C24" H 5782 14304 50  0000 R CNN
F 1 "10uF 50V" H 5782 14395 50  0000 R CNN
F 2 "Capacitors_SMD:CP_Elec_5x5.3" H 5938 14200 50  0001 C CNN
F 3 "~" H 5900 14350 50  0001 C CNN
	1    5900 14350
	-1   0    0    1   
$EndComp
Wire Wire Line
	5650 14500 5900 14500
Connection ~ 5900 14500
Connection ~ 6500 14500
Wire Wire Line
	6500 14500 7500 14500
Wire Wire Line
	5900 14500 6500 14500
NoConn ~ 2791 9150
NoConn ~ 2791 9250
NoConn ~ 2791 9350
NoConn ~ 2291 9550
NoConn ~ 2291 9650
NoConn ~ 2291 9750
NoConn ~ 2291 9850
NoConn ~ 2291 9950
NoConn ~ 2291 10050
NoConn ~ 2291 10150
NoConn ~ 2291 10250
NoConn ~ 2291 10350
NoConn ~ 2291 10450
NoConn ~ 2291 10550
NoConn ~ 2291 10650
NoConn ~ 2291 10750
NoConn ~ 2291 10850
NoConn ~ 2291 10950
NoConn ~ 2791 9650
NoConn ~ 2791 9750
NoConn ~ 2791 9850
NoConn ~ 2791 9950
NoConn ~ 2791 10050
NoConn ~ 2791 10150
NoConn ~ 2791 10250
NoConn ~ 2791 10350
NoConn ~ 2791 10450
NoConn ~ 2791 10550
NoConn ~ 2791 10650
NoConn ~ 2791 10750
NoConn ~ 2791 10850
NoConn ~ 2791 10950
Wire Wire Line
	4300 4400 3950 4400
Wire Wire Line
	3250 5350 3600 5350
Wire Wire Line
	3250 5050 3600 5050
Connection ~ 3600 5050
Wire Wire Line
	3600 5050 4000 5050
Connection ~ 3600 5350
Wire Wire Line
	3600 5350 4000 5350
Wire Wire Line
	3400 5200 3400 5500
Wire Wire Line
	3400 5500 3600 5500
Wire Wire Line
	3800 5500 3800 5200
$Comp
L 4port-usb-hub-rescue:GND #PWR0106
U 1 1 5C1DDFB1
P 3600 5500
F 0 "#PWR0106" H 3600 5250 50  0001 C CNN
F 1 "GND" H 3605 5327 50  0000 C CNN
F 2 "" H 3600 5500 50  0001 C CNN
F 3 "" H 3600 5500 50  0001 C CNN
	1    3600 5500
	1    0    0    -1  
$EndComp
Connection ~ 3600 5500
Wire Wire Line
	3600 5500 3800 5500
$Comp
L Device:Fuse F2
U 1 1 5C2BD6EC
P 2000 3400
F 0 "F2" V 1803 3400 50  0000 C CNN
F 1 "Fuse" V 1894 3400 50  0000 C CNN
F 2 "Fuse_Holders_and_Fuses:Fuse_SMD1206_Reflow" V 1930 3400 50  0001 C CNN
F 3 "~" H 2000 3400 50  0001 C CNN
	1    2000 3400
	-1   0    0    1   
$EndComp
$Comp
L Device:Fuse F3
U 1 1 5C2E1780
P 3500 3400
F 0 "F3" V 3303 3400 50  0000 C CNN
F 1 "Fuse" V 3394 3400 50  0000 C CNN
F 2 "Fuse_Holders_and_Fuses:Fuse_SMD1206_Reflow" V 3430 3400 50  0001 C CNN
F 3 "~" H 3500 3400 50  0001 C CNN
	1    3500 3400
	1    0    0    -1  
$EndComp
$Comp
L Device:Fuse F4
U 1 1 5C30581B
P 4900 3400
F 0 "F4" V 4703 3400 50  0000 C CNN
F 1 "Fuse" V 4794 3400 50  0000 C CNN
F 2 "Fuse_Holders_and_Fuses:Fuse_SMD1206_Reflow" V 4830 3400 50  0001 C CNN
F 3 "~" H 4900 3400 50  0001 C CNN
	1    4900 3400
	1    0    0    -1  
$EndComp
Wire Wire Line
	2000 2400 2000 3100
Wire Wire Line
	2000 3550 2000 3800
Wire Wire Line
	3500 3550 3500 3800
Wire Wire Line
	3500 2400 3500 3100
Wire Wire Line
	4900 2400 4900 3100
Wire Wire Line
	4900 3550 4900 3800
Connection ~ 2550 12350
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C30
U 1 1 5C381FB7
P 19265 9560
F 0 "C30" H 19380 9606 50  0000 L CNN
F 1 "100pF" H 19380 9515 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603" H 19380 9469 50  0001 L CNN
F 3 "" H 19265 9560 50  0000 C CNN
	1    19265 9560
	1    0    0    -1  
$EndComp
Wire Wire Line
	19615 9410 19265 9410
Connection ~ 19615 9410
Text Label 19265 9410 0    50   ~ 0
MBUS+
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R34
U 1 1 5C3983E3
P 18865 9560
F 0 "R34" H 18935 9606 50  0000 L CNN
F 1 "20k" H 18935 9515 50  0000 L CNN
F 2 "Resistors_SMD:R_0603" H 18935 9469 50  0001 L CNN
F 3 "" H 18865 9560 50  0000 C CNN
	1    18865 9560
	1    0    0    -1  
$EndComp
Wire Wire Line
	19265 9410 18865 9410
Connection ~ 19265 9410
$Comp
L 4port-usb-hub-rescue:LED D12
U 1 1 5C3ADA81
P 18865 9860
F 0 "D12" V 18903 9743 50  0000 R CNN
F 1 "LED" V 18812 9743 50  0000 R CNN
F 2 "LEDs:LED_0805" H 18865 9860 50  0001 C CNN
F 3 "" H 18865 9860 50  0001 C CNN
	1    18865 9860
	0    -1   -1   0   
$EndComp
$Comp
L 4port-usb-hub-rescue:GND #PWR043
U 1 1 5C3ADEA1
P 19265 9760
F 0 "#PWR043" H 19265 9510 50  0001 C CNN
F 1 "GND" H 19270 9587 50  0000 C CNN
F 2 "" H 19265 9760 50  0001 C CNN
F 3 "" H 19265 9760 50  0001 C CNN
	1    19265 9760
	1    0    0    -1  
$EndComp
Wire Wire Line
	19265 9710 19265 9760
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R35
U 1 1 5C3C38C3
P 19015 10210
F 0 "R35" V 18965 10410 50  0000 C CNN
F 1 "27R" V 19065 10410 50  0000 C CNN
F 2 "Resistors_SMD:R_2512" H 18900 10210 50  0001 C CNN
F 3 "" H 19015 10210 50  0000 C CNN
	1    19015 10210
	0    1    1    0   
$EndComp
Wire Wire Line
	18865 10010 18865 10210
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C28
U 1 1 5C3D9362
P 19015 10460
F 0 "C28" V 19065 10610 50  0000 C CNN
F 1 "100pF" V 19165 10660 50  0000 C CNN
F 2 "Capacitors_SMD:C_0603" H 18855 10460 50  0001 C CNN
F 3 "" H 19015 10460 50  0000 C CNN
	1    19015 10460
	0    1    1    0   
$EndComp
Wire Wire Line
	18865 10210 18865 10460
Connection ~ 18865 10210
Wire Wire Line
	19665 9710 19665 10210
Wire Wire Line
	19665 10210 19165 10210
Connection ~ 19665 9710
Wire Wire Line
	19665 10210 19665 10460
$Comp
L 4port-usb-hub-rescue:GND #PWR041
U 1 1 5C41A5CE
P 18865 10560
F 0 "#PWR041" H 18865 10310 50  0001 C CNN
F 1 "GND" H 18870 10387 50  0000 C CNN
F 2 "" H 18865 10560 50  0001 C CNN
F 3 "" H 18865 10560 50  0001 C CNN
	1    18865 10560
	1    0    0    -1  
$EndComp
Wire Wire Line
	18865 10460 18865 10560
Connection ~ 18865 10460
Connection ~ 19665 10210
Wire Wire Line
	19165 10460 19665 10460
$Comp
L Transistor_BJT:BC857 Q13
U 1 1 5C45D648
P 18515 9510
F 0 "Q13" V 18843 9510 50  0000 C CNN
F 1 "BC857" V 18752 9510 50  0000 C CNN
F 2 "TO_SOT_Packages_SMD:SOT-23" H 18715 9435 50  0001 L CIN
F 3 "http://www.fairchildsemi.com/ds/BC/BC856.pdf" H 18515 9510 50  0001 L CNN
	1    18515 9510
	0    1    -1   0   
$EndComp
Wire Wire Line
	18715 9410 18865 9410
Connection ~ 18865 9410
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R30
U 1 1 5C4CC422
P 18215 9710
F 0 "R30" V 18008 9710 50  0000 C CNN
F 1 "20K" V 18099 9710 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" H 18145 9710 50  0001 C CNN
F 3 "Change R30 R31 value" V 18290 9865 50  0000 C CNN
	1    18215 9710
	0    1    1    0   
$EndComp
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R31
U 1 1 5C4CC55A
P 18215 9860
F 0 "R31" V 18315 9810 50  0000 C CNN
F 1 "30K" V 18415 9860 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" H 18145 9860 50  0001 C CNN
F 3 "" H 18215 9860 50  0000 C CNN
	1    18215 9860
	0    1    1    0   
$EndComp
Wire Wire Line
	18365 9710 18515 9710
Wire Wire Line
	18315 9410 17915 9410
Wire Wire Line
	17915 9410 17915 9710
Wire Wire Line
	17915 9710 18065 9710
Wire Wire Line
	18365 9860 18515 9860
Wire Wire Line
	18515 9860 18515 9710
$Comp
L Diode:1N4007 D11
U 1 1 5C510967
P 17915 9260
F 0 "D11" V 17961 9181 50  0000 R CNN
F 1 "1N4007" V 17870 9181 50  0000 R CNN
F 2 "Diodes_SMD:D_SMA" H 17915 9085 50  0001 C CNN
F 3 "http://www.vishay.com/docs/88503/1n4001.pdf" H 17915 9260 50  0001 C CNN
	1    17915 9260
	0    -1   -1   0   
$EndComp
Connection ~ 17915 9410
$Comp
L Diode:1N4007 D9
U 1 1 5C510D88
P 17665 9410
F 0 "D9" H 17665 9194 50  0000 C CNN
F 1 "1N4007" H 17665 9285 50  0000 C CNN
F 2 "Diodes_SMD:D_SMA" H 17665 9235 50  0001 C CNN
F 3 "http://www.vishay.com/docs/88503/1n4001.pdf" H 17665 9410 50  0001 C CNN
	1    17665 9410
	-1   0    0    1   
$EndComp
Wire Wire Line
	17915 9410 17815 9410
$Comp
L Diode:1N62xxA D10
U 1 1 5C528660
P 17915 8860
F 0 "D10" V 17869 8939 50  0000 L CNN
F 1 "12V TVS" V 17960 8939 50  0000 L CNN
F 2 "Diodes_SMD:D_SMA" H 17915 8660 50  0001 C CNN
F 3 "https://www.vishay.com/docs/88301/15ke.pdf" H 17865 8860 50  0001 C CNN
	1    17915 8860
	0    1    1    0   
$EndComp
Wire Wire Line
	17915 9010 17915 9110
Connection ~ 18515 9710
$Comp
L Transistor_BJT:BC857 Q10
U 1 1 5C558067
P 17415 9060
F 0 "Q10" H 17606 9014 50  0000 L CNN
F 1 "BC857" H 17606 9105 50  0000 L CNN
F 2 "TO_SOT_Packages_SMD:SOT-23" H 17615 8985 50  0001 L CIN
F 3 "http://www.fairchildsemi.com/ds/BC/BC856.pdf" H 17415 9060 50  0001 L CNN
	1    17415 9060
	1    0    0    1   
$EndComp
Wire Wire Line
	17515 9260 17515 9410
Wire Wire Line
	17515 8860 17515 8710
Wire Wire Line
	17515 8710 17915 8710
Wire Wire Line
	17915 8710 18315 8710
Connection ~ 17915 8710
Text Label 18015 8710 0    50   ~ 0
MBUS-V
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R22
U 1 1 5C62EB5C
P 16965 8910
F 0 "R22" H 17035 8956 50  0000 L CNN
F 1 "20K" H 17035 8865 50  0000 L CNN
F 2 "Resistors_SMD:R_0603" H 16895 8910 50  0001 C CNN
F 3 "Change R22 value" H 17025 9065 50  0000 C CNN
	1    16965 8910
	1    0    0    -1  
$EndComp
Wire Wire Line
	16965 9060 17215 9060
Wire Wire Line
	16965 8760 16965 8710
Wire Wire Line
	16965 8710 17515 8710
Connection ~ 17515 8710
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R12
U 1 1 5C65F010
P 16515 9060
F 0 "R12" V 16308 9060 50  0000 C CNN
F 1 "30K" V 16399 9060 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" H 16400 9060 50  0001 C CNN
F 3 "Change R12 value" V 16605 9290 50  0000 C CNN
	1    16515 9060
	0    1    1    0   
$EndComp
Wire Wire Line
	16665 9060 16965 9060
Connection ~ 16965 9060
$Comp
L Transistor_FET:2N7002 Q6
U 1 1 5C6778D1
P 16065 9260
F 0 "Q6" H 16270 9306 50  0000 L CNN
F 1 "2N7002" H 16270 9215 50  0000 L CNN
F 2 "TO_SOT_Packages_SMD:SOT-23" H 16265 9185 50  0001 L CIN
F 3 "https://www.fairchildsemi.com/datasheets/2N/2N7002.pdf" H 16065 9260 50  0001 L CNN
	1    16065 9260
	1    0    0    -1  
$EndComp
Wire Wire Line
	16365 9060 16165 9060
Wire Wire Line
	16165 9460 16165 9560
$Comp
L 4port-usb-hub-rescue:GND #PWR034
U 1 1 5C6A90FE
P 16165 9560
F 0 "#PWR034" H 16165 9310 50  0001 C CNN
F 1 "GND" H 16170 9387 50  0000 C CNN
F 2 "" H 16165 9560 50  0001 C CNN
F 3 "" H 16165 9560 50  0001 C CNN
	1    16165 9560
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R7
U 1 1 5C6AA2F0
P 15265 9260
F 0 "R7" V 15058 9260 50  0000 C CNN
F 1 "5R" V 15149 9260 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" H 15195 9260 50  0001 C CNN
F 3 "Change R7 value" V 15370 9335 50  0000 C CNN
	1    15265 9260
	0    1    1    0   
$EndComp
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R5
U 1 1 5C6C90BB
P 14765 9260
F 0 "R5" V 14558 9260 50  0000 C CNN
F 1 "10K" V 14649 9260 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" H 14650 9260 50  0001 C CNN
F 3 "" H 14765 9260 50  0000 C CNN
	1    14765 9260
	0    1    1    0   
$EndComp
Wire Wire Line
	14915 9260 15015 9260
Wire Wire Line
	15015 9260 15015 8910
Wire Wire Line
	15015 9260 15115 9260
Connection ~ 15015 9260
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R6
U 1 1 5C718550
P 15015 10010
F 0 "R6" H 15085 10056 50  0000 L CNN
F 1 "10K" H 15085 9965 50  0000 L CNN
F 2 "Resistors_SMD:R_0603" H 15085 9919 50  0001 L CNN
F 3 "" H 15015 10010 50  0000 C CNN
	1    15015 10010
	1    0    0    -1  
$EndComp
Wire Wire Line
	15415 9260 15865 9260
$Comp
L Transistor_FET:2N7002 Q4
U 1 1 5C7656EC
P 15565 9660
F 0 "Q4" H 15770 9706 50  0000 L CNN
F 1 "2N7002" H 15770 9615 50  0000 L CNN
F 2 "TO_SOT_Packages_SMD:SOT-23" H 15765 9585 50  0001 L CIN
F 3 "https://www.fairchildsemi.com/datasheets/2N/2N7002.pdf" H 15565 9660 50  0001 L CNN
	1    15565 9660
	1    0    0    -1  
$EndComp
$Comp
L Transistor_FET:2N7002 Q5
U 1 1 5C77ED98
P 15565 10160
F 0 "Q5" H 15770 10206 50  0000 L CNN
F 1 "2N7002" H 15770 10115 50  0000 L CNN
F 2 "TO_SOT_Packages_SMD:SOT-23" H 15765 10085 50  0001 L CIN
F 3 "https://www.fairchildsemi.com/datasheets/2N/2N7002.pdf" H 15565 10160 50  0001 L CNN
	1    15565 10160
	1    0    0    -1  
$EndComp
Wire Wire Line
	15015 9260 15015 9860
$Comp
L Transistor_FET:2N7002 Q1
U 1 1 5C7B1D20
P 14465 9860
F 0 "Q1" H 14670 9906 50  0000 L CNN
F 1 "2N7002" H 14670 9815 50  0000 L CNN
F 2 "TO_SOT_Packages_SMD:SOT-23" H 14665 9785 50  0001 L CIN
F 3 "https://www.fairchildsemi.com/datasheets/2N/2N7002.pdf" H 14465 9860 50  0001 L CNN
	1    14465 9860
	1    0    0    -1  
$EndComp
Wire Wire Line
	15365 9660 14565 9660
Wire Wire Line
	15365 10160 15015 10160
Wire Wire Line
	15665 9860 15665 9960
Wire Wire Line
	15665 10360 15665 10510
$Comp
L 4port-usb-hub-rescue:GND #PWR033
U 1 1 5C8326B6
P 15665 10510
F 0 "#PWR033" H 15665 10260 50  0001 C CNN
F 1 "GND" H 15670 10337 50  0000 C CNN
F 2 "" H 15665 10510 50  0001 C CNN
F 3 "" H 15665 10510 50  0001 C CNN
	1    15665 10510
	1    0    0    -1  
$EndComp
Wire Wire Line
	15665 9460 15665 8660
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R4
U 1 1 5C8678DD
P 14265 9660
F 0 "R4" V 14472 9660 50  0000 C CNN
F 1 "10K" V 14381 9660 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" H 14381 9660 50  0001 C CNN
F 3 "Change R4 value" V 14400 9225 50  0000 C CNN
	1    14265 9660
	0    -1   -1   0   
$EndComp
Wire Wire Line
	14565 9660 14415 9660
Connection ~ 14565 9660
Wire Wire Line
	14115 9660 14115 9260
Wire Wire Line
	14115 9260 14615 9260
Connection ~ 14115 9660
Wire Wire Line
	14565 10060 14565 10160
$Comp
L 4port-usb-hub-rescue:GND #PWR029
U 1 1 5C927CFD
P 14565 10160
F 0 "#PWR029" H 14565 9910 50  0001 C CNN
F 1 "GND" H 14570 9987 50  0000 C CNN
F 2 "" H 14565 10160 50  0001 C CNN
F 3 "" H 14565 10160 50  0001 C CNN
	1    14565 10160
	1    0    0    -1  
$EndComp
$Comp
L Transistor_FET:2N7002 Q2
U 1 1 5C92846F
P 14665 10710
F 0 "Q2" H 14515 10860 50  0000 L CNN
F 1 "2N7002" H 14315 10760 50  0000 L CNN
F 2 "TO_SOT_Packages_SMD:SOT-23" H 14865 10635 50  0001 L CIN
F 3 "https://www.fairchildsemi.com/datasheets/2N/2N7002.pdf" H 14665 10710 50  0001 L CNN
	1    14665 10710
	-1   0    0    -1  
$EndComp
$Comp
L Transistor_FET:2N7002 Q3
U 1 1 5C928D42
P 14665 11210
F 0 "Q3" H 14515 11360 50  0000 L CNN
F 1 "2N7002" H 14315 11260 50  0000 L CNN
F 2 "TO_SOT_Packages_SMD:SOT-23" H 14865 11135 50  0001 L CIN
F 3 "https://www.fairchildsemi.com/datasheets/2N/2N7002.pdf" H 14665 11210 50  0001 L CNN
	1    14665 11210
	-1   0    0    -1  
$EndComp
Wire Wire Line
	14565 10910 14565 10960
Wire Wire Line
	15015 10160 15015 10710
Wire Wire Line
	15015 11210 14865 11210
Connection ~ 15015 10160
Wire Wire Line
	14865 10710 15015 10710
Connection ~ 15015 10710
Wire Wire Line
	15015 10710 15015 11210
Wire Wire Line
	14565 10960 14165 10960
Connection ~ 14565 10960
Wire Wire Line
	14565 10960 14565 11010
$Comp
L Diode:1N5819 D6
U 1 1 5C9B2CFF
P 14165 10810
F 0 "D6" V 14119 10731 50  0000 R CNN
F 1 "1N5819" V 14210 10731 50  0000 R CNN
F 2 "Diodes_SMD:D_SOD-323" H 14165 10635 50  0001 C CNN
F 3 "http://www.vishay.com/docs/88525/1n5817.pdf" H 14165 10810 50  0001 C CNN
	1    14165 10810
	0    -1   1    0   
$EndComp
$Comp
L Diode:1N5819 D7
U 1 1 5CA26E74
P 14165 11260
F 0 "D7" V 14119 11181 50  0000 R CNN
F 1 "1N5819" V 14210 11181 50  0000 R CNN
F 2 "Diodes_SMD:D_SOD-323" H 14165 11085 50  0001 C CNN
F 3 "http://www.vishay.com/docs/88525/1n5817.pdf" H 14165 11260 50  0001 C CNN
	1    14165 11260
	0    -1   1    0   
$EndComp
Wire Wire Line
	14165 11110 14165 10960
Connection ~ 14165 10960
Wire Wire Line
	14165 11410 14565 11410
$Comp
L Diode:1N5819 D4
U 1 1 5CA7BDB5
P 13915 10810
F 0 "D4" V 13869 10731 50  0000 R CNN
F 1 "1N5819" V 13960 10731 50  0000 R CNN
F 2 "Diodes_SMD:D_SOD-323" H 13915 10635 50  0001 C CNN
F 3 "http://www.vishay.com/docs/88525/1n5817.pdf" H 13915 10810 50  0001 C CNN
	1    13915 10810
	0    -1   1    0   
$EndComp
Wire Wire Line
	14165 10960 13915 10960
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R3
U 1 1 5CB449DC
P 13865 10210
F 0 "R3" V 13658 10210 50  0000 C CNN
F 1 "20K" V 13749 10210 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" H 13750 10210 50  0001 C CNN
F 3 "" H 13865 10210 50  0000 C CNN
	1    13865 10210
	0    1    1    0   
$EndComp
Wire Wire Line
	14015 10210 14165 10210
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R2
U 1 1 5CB6352F
P 13565 10910
F 0 "R2" H 13635 10956 50  0000 L CNN
F 1 "10k" H 13635 10865 50  0000 L CNN
F 2 "Resistors_SMD:R_0603" H 13635 10819 50  0001 L CNN
F 3 "Change R2 value" H 13950 10770 50  0000 C CNN
	1    13565 10910
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C4
U 1 1 5CB63662
P 13165 10910
F 0 "C4" H 13280 10956 50  0000 L CNN
F 1 "100pF" H 13280 10865 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603" H 13203 10760 50  0001 C CNN
F 3 "" H 13165 10910 50  0000 C CNN
	1    13165 10910
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C1
U 1 1 5CB6377E
P 12915 10210
F 0 "C1" H 13030 10256 50  0000 L CNN
F 1 "10uF 50V" H 13030 10165 50  0000 L CNN
F 2 "Capacitors_SMD:C_0805" H 12953 10060 50  0001 C CNN
F 3 "Change C1 value" H 13305 10075 50  0000 C CNN
	1    12915 10210
	1    0    0    -1  
$EndComp
Wire Wire Line
	13565 10760 13565 10660
Connection ~ 13565 10660
Wire Wire Line
	13565 10660 13915 10660
Wire Wire Line
	13165 10760 13165 10660
Wire Wire Line
	13165 10660 13565 10660
Wire Wire Line
	13165 11060 13365 11060
$Comp
L Amplifier_Operational:LMV321 U1
U 1 1 5CBDEA12
P 12315 10560
F 0 "U1" H 12465 10160 50  0000 C CNN
F 1 "LMV321" H 12365 10260 50  0000 C CNN
F 2 "TO_SOT_Packages_SMD:SOT-23-5" H 12315 10560 50  0001 L CNN
F 3 "http://www.ti.com/lit/ds/symlink/lmv324.pdf" H 12315 10560 50  0001 C CNN
	1    12315 10560
	-1   0    0    1   
$EndComp
$Comp
L Diode:1N5819 D1
U 1 1 5CAB51B2
P 13865 10460
F 0 "D1" H 13965 10560 50  0000 C CNN
F 1 "1N5819" H 13865 10360 50  0000 C CNN
F 2 "Diodes_SMD:D_SOD-323" H 13865 10285 50  0001 C CNN
F 3 "http://www.vishay.com/docs/88525/1n5817.pdf" H 13865 10460 50  0001 C CNN
	1    13865 10460
	-1   0    0    -1  
$EndComp
Wire Wire Line
	14165 10210 14165 10460
Wire Wire Line
	14015 10460 14165 10460
Wire Wire Line
	14565 10460 14565 10510
Connection ~ 14165 10460
Wire Wire Line
	14165 10460 14165 10660
Wire Wire Line
	14165 10460 14565 10460
Wire Wire Line
	12415 10260 12415 10060
Wire Wire Line
	12415 10060 12915 10060
$Comp
L 4port-usb-hub-rescue:GND #PWR025
U 1 1 5C21B9B8
P 12915 10060
F 0 "#PWR025" H 12915 9810 50  0001 C CNN
F 1 "GND" H 12915 9910 50  0000 C CNN
F 2 "" H 12915 10060 50  0001 C CNN
F 3 "" H 12915 10060 50  0001 C CNN
	1    12915 10060
	-1   0    0    1   
$EndComp
$Comp
L 4port-usb-hub-rescue:GND #PWR026
U 1 1 5C21C73E
P 13365 11060
F 0 "#PWR026" H 13365 10810 50  0001 C CNN
F 1 "GND" H 13370 10887 50  0000 C CNN
F 2 "" H 13365 11060 50  0001 C CNN
F 3 "" H 13365 11060 50  0001 C CNN
	1    13365 11060
	1    0    0    -1  
$EndComp
Connection ~ 13365 11060
Wire Wire Line
	13365 11060 13565 11060
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R1
U 1 1 5C21CE55
P 12015 10710
F 0 "R1" H 12085 10756 50  0000 L CNN
F 1 "10K" H 12085 10665 50  0000 L CNN
F 2 "Resistors_SMD:R_0603" H 11945 10710 50  0001 C CNN
F 3 "" H 12015 10710 50  0000 C CNN
	1    12015 10710
	1    0    0    -1  
$EndComp
Wire Wire Line
	12015 10860 12415 10860
Wire Wire Line
	12015 10560 12015 9860
Wire Wire Line
	12015 9860 14265 9860
Connection ~ 12015 10560
Wire Wire Line
	13715 10210 13415 10210
Wire Wire Line
	13415 10210 13415 10460
Wire Wire Line
	12915 10460 13415 10460
Connection ~ 13415 10460
Wire Wire Line
	13415 10460 13715 10460
Connection ~ 12915 10060
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C11
U 1 1 5C3484CF
P 13915 11260
F 0 "C11" H 13615 11260 50  0000 L CNN
F 1 "100pF" H 13615 11160 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603" H 13953 11110 50  0001 C CNN
F 3 "" H 13915 11260 50  0000 C CNN
	1    13915 11260
	1    0    0    -1  
$EndComp
Wire Wire Line
	13915 11110 13915 10960
Connection ~ 13915 10960
$Comp
L 4port-usb-hub-rescue:GND #PWR027
U 1 1 5C38BDD3
P 13915 11460
F 0 "#PWR027" H 13915 11210 50  0001 C CNN
F 1 "GND" H 13920 11287 50  0000 C CNN
F 2 "" H 13915 11460 50  0001 C CNN
F 3 "" H 13915 11460 50  0001 C CNN
	1    13915 11460
	1    0    0    -1  
$EndComp
Wire Wire Line
	13915 11410 13915 11460
$Comp
L Transistor_FET:2N7002 Q12
U 1 1 5C3AED35
P 17815 10210
F 0 "Q12" H 18020 10256 50  0000 L CNN
F 1 "2N7002" H 18020 10165 50  0000 L CNN
F 2 "TO_SOT_Packages_SMD:SOT-23" H 18015 10135 50  0001 L CIN
F 3 "https://www.fairchildsemi.com/datasheets/2N/2N7002.pdf" H 17815 10210 50  0001 L CNN
	1    17815 10210
	1    0    0    -1  
$EndComp
Wire Wire Line
	17915 10010 17915 9860
Wire Wire Line
	17915 9860 18065 9860
$Comp
L Transistor_FET:2N7002 Q11
U 1 1 5C3D0A6B
P 17515 10560
F 0 "Q11" H 17720 10606 50  0000 L CNN
F 1 "2N7002" H 17720 10515 50  0000 L CNN
F 2 "TO_SOT_Packages_SMD:SOT-23" H 17715 10485 50  0001 L CIN
F 3 "https://www.fairchildsemi.com/datasheets/2N/2N7002.pdf" H 17515 10560 50  0001 L CNN
	1    17515 10560
	1    0    0    -1  
$EndComp
Wire Wire Line
	17615 10360 17615 10210
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R28
U 1 1 5C3F2D15
P 17615 10010
F 0 "R28" H 17685 10056 50  0000 L CNN
F 1 "20k" H 17685 9965 50  0000 L CNN
F 2 "Resistors_SMD:R_0603" H 17685 9919 50  0001 L CNN
F 3 "Change R28 value" H 17520 10200 50  0000 C CNN
	1    17615 10010
	1    0    0    -1  
$EndComp
Wire Wire Line
	17615 10210 17615 10160
Connection ~ 17615 10210
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C26
U 1 1 5C41526A
P 17165 10710
F 0 "C26" H 17280 10756 50  0000 L CNN
F 1 "1UF" H 17280 10665 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603" H 17280 10619 50  0001 L CNN
F 3 "" H 17165 10710 50  0000 C CNN
	1    17165 10710
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R19
U 1 1 5C415391
P 16815 10710
F 0 "R19" H 16885 10756 50  0000 L CNN
F 1 "1.5M" H 16885 10665 50  0000 L CNN
F 2 "Resistors_SMD:R_0603" H 16885 10619 50  0001 L CNN
F 3 "" H 16815 10710 50  0000 C CNN
	1    16815 10710
	1    0    0    -1  
$EndComp
Wire Wire Line
	17315 10560 17165 10560
Connection ~ 17165 10560
Wire Wire Line
	17165 10560 16815 10560
Wire Wire Line
	16815 10860 17165 10860
Wire Wire Line
	17915 10860 17915 10410
Connection ~ 17165 10860
Wire Wire Line
	17165 10860 17615 10860
Wire Wire Line
	17615 10760 17615 10860
Connection ~ 17615 10860
Wire Wire Line
	17615 10860 17915 10860
$Comp
L Device:Q_PMOS_GSD Q8
U 1 1 5C4A2C30
P 16715 10260
F 0 "Q8" H 16921 10214 50  0000 L CNN
F 1 "Q_PMOS_GSD" H 16921 10305 50  0000 L CNN
F 2 "TO_SOT_Packages_SMD:SOT-23" H 16915 10360 50  0001 C CNN
F 3 "~" H 16715 10260 50  0001 C CNN
	1    16715 10260
	1    0    0    1   
$EndComp
Wire Wire Line
	16815 10460 16815 10560
Connection ~ 16815 10560
Wire Wire Line
	16815 10060 16815 9860
Wire Wire Line
	16815 9860 17615 9860
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R18
U 1 1 5C4EA480
P 16515 10010
F 0 "R18" H 16585 10056 50  0000 L CNN
F 1 "20K" H 16585 9965 50  0000 L CNN
F 2 "Resistors_SMD:R_0603" H 16585 9919 50  0001 L CNN
F 3 "Change R18 value" H 17165 10010 50  0000 C CNN
	1    16515 10010
	1    0    0    -1  
$EndComp
Wire Wire Line
	16515 10160 16515 10260
Wire Wire Line
	16515 9860 16815 9860
Connection ~ 16815 9860
Connection ~ 16515 9860
$Comp
L Transistor_FET:2N7002 Q7
U 1 1 5C57D472
P 16415 10660
F 0 "Q7" H 16165 10910 50  0000 L CNN
F 1 "2N7002" H 16165 10810 50  0000 L CNN
F 2 "TO_SOT_Packages_SMD:SOT-23" H 16615 10585 50  0001 L CIN
F 3 "https://www.fairchildsemi.com/datasheets/2N/2N7002.pdf" H 16415 10660 50  0001 L CNN
	1    16415 10660
	1    0    0    -1  
$EndComp
Wire Wire Line
	16515 10460 16515 10260
Connection ~ 16515 10260
Wire Wire Line
	16515 10860 16815 10860
Connection ~ 16815 10860
$Comp
L 4port-usb-hub-rescue:GND #PWR037
U 1 1 5C5C7E34
P 17915 10860
F 0 "#PWR037" H 17915 10610 50  0001 C CNN
F 1 "GND" H 17920 10687 50  0000 C CNN
F 2 "" H 17915 10860 50  0001 C CNN
F 3 "" H 17915 10860 50  0001 C CNN
	1    17915 10860
	1    0    0    -1  
$EndComp
Connection ~ 17915 10860
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R42
U 1 1 5C5C8CDB
P 19365 11360
F 0 "R42" V 19158 11360 50  0000 C CNN
F 1 "10K" V 19249 11360 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" H 19250 11360 50  0001 C CNN
F 3 "" H 19365 11360 50  0000 C CNN
	1    19365 11360
	0    1    1    0   
$EndComp
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R33
U 1 1 5C5C8F12
P 18815 12160
F 0 "R33" V 18608 12160 50  0000 C CNN
F 1 "3K" V 18699 12160 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" H 18745 12160 50  0001 C CNN
F 3 "" H 18815 12160 50  0000 C CNN
	1    18815 12160
	0    1    1    0   
$EndComp
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R32
U 1 1 5C5C9164
P 18265 12160
F 0 "R32" V 18058 12160 50  0000 C CNN
F 1 "1K" V 18149 12160 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" H 18195 12160 50  0001 C CNN
F 3 "Change R32 R33 value" V 18375 12380 50  0000 C CNN
	1    18265 12160
	0    1    1    0   
$EndComp
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R29
U 1 1 5C5C9409
P 17815 11310
F 0 "R29" H 17885 11356 50  0000 L CNN
F 1 "20K" H 17885 11265 50  0000 L CNN
F 2 "Resistors_SMD:R_0603" H 17745 11310 50  0001 C CNN
F 3 "" H 17815 11310 50  0000 C CNN
	1    17815 11310
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R10
U 1 1 5C5C9539
P 16215 11310
F 0 "R10" H 16285 11356 50  0000 L CNN
F 1 "1.5M" H 16285 11265 50  0000 L CNN
F 2 "Resistors_SMD:R_0603" H 16145 11310 50  0001 C CNN
F 3 "" H 16215 11310 50  0000 C CNN
	1    16215 11310
	1    0    0    -1  
$EndComp
Wire Wire Line
	19665 10460 19665 11360
Wire Wire Line
	19665 11360 19515 11360
Connection ~ 19665 10460
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C29
U 1 1 5C5EEA9E
P 19065 11510
F 0 "C29" H 19180 11556 50  0000 L CNN
F 1 "100nF" H 19180 11465 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603" H 19180 11419 50  0001 L CNN
F 3 "" H 19065 11510 50  0000 C CNN
	1    19065 11510
	1    0    0    -1  
$EndComp
$Comp
L Amplifier_Operational:LMV321 U3
U 1 1 5C61508D
P 18365 11460
F 0 "U3" H 18065 11810 50  0000 C CNN
F 1 "LMV321" H 17965 11710 50  0000 C CNN
F 2 "TO_SOT_Packages_SMD:SOT-23-5" H 18365 11460 50  0001 L CNN
F 3 "http://www.ti.com/lit/ds/symlink/lmv324.pdf" H 18365 11460 50  0001 C CNN
	1    18365 11460
	-1   0    0    -1  
$EndComp
Wire Wire Line
	19065 11660 19065 11810
Wire Wire Line
	19065 11810 18965 11810
Wire Wire Line
	18965 12160 19065 12160
Wire Wire Line
	19065 12160 19065 11810
Connection ~ 19065 11810
$Comp
L 4port-usb-hub-rescue:GND #PWR042
U 1 1 5C742701
P 19065 12160
F 0 "#PWR042" H 19065 11910 50  0001 C CNN
F 1 "GND" H 19070 11987 50  0000 C CNN
F 2 "" H 19065 12160 50  0001 C CNN
F 3 "" H 19065 12160 50  0001 C CNN
	1    19065 12160
	1    0    0    -1  
$EndComp
Connection ~ 19065 12160
Wire Wire Line
	18665 12160 18415 12160
Connection ~ 18665 12160
$Comp
L 4port-usb-hub-rescue:GND #PWR040
U 1 1 5C82B660
P 18465 11860
F 0 "#PWR040" H 18465 11610 50  0001 C CNN
F 1 "GND" H 18470 11687 50  0000 C CNN
F 2 "" H 18465 11860 50  0001 C CNN
F 3 "" H 18465 11860 50  0001 C CNN
	1    18465 11860
	1    0    0    -1  
$EndComp
Wire Wire Line
	18465 11760 18465 11860
Wire Wire Line
	18465 11160 17815 11160
Connection ~ 17815 11160
Wire Wire Line
	17815 11160 17515 11160
Wire Wire Line
	18065 11460 17815 11460
$Comp
L Device:Q_PMOS_GSD Q9
U 1 1 5C8C9C05
P 17315 11260
F 0 "Q9" V 17658 11260 50  0000 C CNN
F 1 "Q_PMOS_GSD" V 17567 11260 50  0000 C CNN
F 2 "TO_SOT_Packages_SMD:SOT-23" H 17515 11360 50  0001 C CNN
F 3 "~" H 17315 11260 50  0001 C CNN
	1    17315 11260
	0    -1   -1   0   
$EndComp
Wire Wire Line
	17815 11460 17315 11460
Connection ~ 17815 11460
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C12
U 1 1 5C918E00
P 16665 11310
F 0 "C12" H 16780 11356 50  0000 L CNN
F 1 "1UF" H 16780 11265 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603" H 16703 11160 50  0001 C CNN
F 3 "" H 16665 11310 50  0000 C CNN
	1    16665 11310
	1    0    0    -1  
$EndComp
Wire Wire Line
	16665 11460 16415 11460
Wire Wire Line
	16215 11160 16665 11160
Connection ~ 16665 11160
Wire Wire Line
	16665 11160 17115 11160
Wire Wire Line
	16215 10660 16215 11160
Connection ~ 16215 11160
$Comp
L 4port-usb-hub-rescue:GND #PWR035
U 1 1 5C9E1801
P 16415 11460
F 0 "#PWR035" H 16415 11210 50  0001 C CNN
F 1 "GND" H 16420 11287 50  0000 C CNN
F 2 "" H 16415 11460 50  0001 C CNN
F 3 "" H 16415 11460 50  0001 C CNN
	1    16415 11460
	1    0    0    -1  
$EndComp
Connection ~ 16415 11460
Wire Wire Line
	16415 11460 16215 11460
Wire Wire Line
	19665 11360 19665 12510
Wire Wire Line
	19665 12510 14565 12510
Wire Wire Line
	14565 12510 14565 11410
Connection ~ 19665 11360
Connection ~ 14565 11410
Wire Wire Line
	15665 8660 15015 8660
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R8
U 1 1 5CB5DDBC
P 15665 8510
F 0 "R8" H 15735 8556 50  0000 L CNN
F 1 "10K" H 15735 8465 50  0000 L CNN
F 2 "Resistors_SMD:R_0603" H 15735 8419 50  0001 L CNN
F 3 "" H 15665 8510 50  0000 C CNN
	1    15665 8510
	1    0    0    -1  
$EndComp
Connection ~ 15665 8660
$Comp
L 4port-usb-hub-rescue:LED D8
U 1 1 5CB5DF12
P 15665 8110
F 0 "D8" V 15703 7993 50  0000 R CNN
F 1 "LED" V 15612 7993 50  0000 R CNN
F 2 "LEDs:LED_0805" H 15665 8110 50  0001 C CNN
F 3 "" H 15665 8110 50  0001 C CNN
	1    15665 8110
	0    -1   -1   0   
$EndComp
Wire Wire Line
	15665 8260 15665 8360
Wire Notes Line
	11215 6810 21415 6810
Wire Notes Line
	21415 6810 21415 13460
Wire Notes Line
	21415 13460 11215 13460
Wire Notes Line
	11215 13460 11215 6810
Wire Wire Line
	2041 8655 1736 8655
Wire Wire Line
	2041 8655 2041 9050
Wire Wire Line
	1791 8790 1506 8790
Wire Wire Line
	1791 8790 1791 9150
Text Label 1801 8655 0    50   ~ 0
MAIN_5V
Text Label 1536 8790 0    50   ~ 0
MAIN_3.3V
Wire Wire Line
	1860 12350 2250 12350
Text Label 1905 12350 0    50   ~ 0
MAIN_5V
Text Label 2000 3704 3    60   ~ 0
USBHUB_5V
Wire Wire Line
	15210 1600 15210 1290
Wire Wire Line
	18210 2295 18210 2250
Text Label 15210 1350 0    50   ~ 0
USBHUB_5V
Text Label 18210 1590 0    50   ~ 0
USBHUB_5V
Wire Wire Line
	15665 7680 15665 7960
Wire Wire Line
	16515 9645 16750 9645
Wire Wire Line
	16515 9645 16515 9860
Text Label 2710 12350 0    50   ~ 0
MAIN_5V_FUSED
Text Label 16565 9645 0    50   ~ 0
MAIN_5V_FUSED
Wire Wire Line
	18465 11160 18465 10955
Wire Wire Line
	18465 10955 19125 10955
Text Label 18595 10955 0    50   ~ 0
MAIN_5V_FUSED
Text Label 15665 7735 0    50   ~ 0
LDO_3.3V
Wire Wire Line
	13610 9660 14115 9660
Text Label 13675 9660 0    50   ~ 0
LDO_3.3V
Wire Wire Line
	16805 12160 16805 12135
Wire Wire Line
	16805 12160 18115 12160
Text Label 17130 12160 0    50   ~ 0
LDO_3.3V
Connection ~ 12015 10860
Text Label 12015 11480 1    50   ~ 0
MAIN_5V_FUSED
Wire Wire Line
	6534 8773 6744 8773
$Comp
L 4port-usb-hub-rescue:GND #PWR018
U 1 1 5C8EB185
P 5273 11003
F 0 "#PWR018" H 5273 10753 50  0001 C CNN
F 1 "GND" H 5278 10830 50  0000 C CNN
F 2 "" H 5273 11003 50  0001 C CNN
F 3 "" H 5273 11003 50  0001 C CNN
	1    5273 11003
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:GND #PWR019
U 1 1 5CB106F3
P 5624 9703
F 0 "#PWR019" H 5624 9453 50  0001 C CNN
F 1 "GND" H 5629 9530 50  0000 C CNN
F 2 "" H 5624 9703 50  0001 C CNN
F 3 "" H 5624 9703 50  0001 C CNN
	1    5624 9703
	1    0    0    -1  
$EndComp
Wire Wire Line
	5624 9463 5624 9643
Wire Wire Line
	5624 9063 5624 8973
Wire Wire Line
	5624 8973 5934 8973
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R46
U 1 1 5CB98D37
P 5684 8418
F 0 "R46" V 5477 8418 50  0000 C CNN
F 1 "470R" V 5568 8418 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" H 5614 8418 50  0001 C CNN
F 3 "" H 5684 8418 50  0000 C CNN
	1    5684 8418
	-1   0    0    1   
$EndComp
Wire Wire Line
	5934 8773 5684 8773
Wire Wire Line
	5684 8773 5684 8568
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R43
U 1 1 5CBC750A
P 4984 9263
F 0 "R43" V 4777 9263 50  0000 C CNN
F 1 "1K" V 4868 9263 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" H 4914 9263 50  0001 C CNN
F 3 "" H 4984 9263 50  0000 C CNN
	1    4984 9263
	0    1    1    0   
$EndComp
Wire Wire Line
	5134 9263 5249 9263
Text GLabel 4764 9263 0    50   Input ~ 0
UART3-TX
Wire Wire Line
	4834 9263 4764 9263
Text Label 5156 8268 0    50   ~ 0
MAIN_3.3V
Wire Wire Line
	6744 8123 7314 8123
Wire Wire Line
	7314 8123 7314 8128
Text Label 6948 8123 0    50   ~ 0
LDO_3.3V
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R47
U 1 1 5CD11C66
P 6694 9158
F 0 "R47" V 6487 9158 50  0000 C CNN
F 1 "470R" V 6578 9158 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" H 6624 9158 50  0001 C CNN
F 3 "" H 6694 9158 50  0000 C CNN
	1    6694 9158
	-1   0    0    1   
$EndComp
$Comp
L 4port-usb-hub-rescue:GND #PWR028
U 1 1 5CD11EC0
P 6694 9388
F 0 "#PWR028" H 6694 9138 50  0001 C CNN
F 1 "GND" H 6699 9215 50  0000 C CNN
F 2 "" H 6694 9388 50  0001 C CNN
F 3 "" H 6694 9388 50  0001 C CNN
	1    6694 9388
	1    0    0    -1  
$EndComp
Wire Wire Line
	6534 8973 6694 8973
Wire Wire Line
	6694 8973 6694 9008
Wire Wire Line
	6694 9308 6694 9388
Connection ~ 6694 8973
Text Label 6829 8973 0    50   ~ 0
MBUS_TX
Wire Wire Line
	15015 8910 14395 8910
Wire Wire Line
	14395 8910 14395 8925
Text Label 14560 8910 0    50   ~ 0
MBUS_TX
Text Label 15155 8660 0    50   ~ 0
MBUS_RX
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R45
U 1 1 5D051397
P 5249 9468
F 0 "R45" V 5042 9468 50  0000 C CNN
F 1 "10K" V 5133 9468 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" H 5179 9468 50  0001 C CNN
F 3 "" H 5249 9468 50  0000 C CNN
	1    5249 9468
	-1   0    0    1   
$EndComp
Wire Wire Line
	5249 9618 5249 9643
Wire Wire Line
	5249 9643 5624 9643
Connection ~ 5624 9643
Wire Wire Line
	5624 9643 5624 9703
Wire Wire Line
	5249 9318 5249 9263
Connection ~ 5249 9263
Wire Wire Line
	5249 9263 5324 9263
$Comp
L 4port-usb-hub-rescue:GND #PWR030
U 1 1 5D1B06A0
P 6853 11293
F 0 "#PWR030" H 6853 11043 50  0001 C CNN
F 1 "GND" H 6858 11120 50  0000 C CNN
F 2 "" H 6853 11293 50  0001 C CNN
F 3 "" H 6853 11293 50  0001 C CNN
	1    6853 11293
	1    0    0    -1  
$EndComp
Wire Wire Line
	6853 11228 6853 11293
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R49
U 1 1 5D1E3536
P 7403 11028
F 0 "R49" V 7196 11028 50  0000 C CNN
F 1 "1K" V 7287 11028 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" H 7333 11028 50  0001 C CNN
F 3 "" H 7403 11028 50  0000 C CNN
	1    7403 11028
	0    1    1    0   
$EndComp
Wire Wire Line
	7253 11028 7153 11028
Wire Wire Line
	7553 11028 8008 11028
Wire Wire Line
	8008 11028 8008 11033
Text Label 7643 11028 0    50   ~ 0
MBUS_RX
Wire Wire Line
	5978 10748 6853 10748
$Comp
L 4port-usb-hub-rescue:GND #PWR024
U 1 1 5D27E666
P 6048 11048
F 0 "#PWR024" H 6048 10798 50  0001 C CNN
F 1 "GND" H 6053 10875 50  0000 C CNN
F 2 "" H 6048 11048 50  0001 C CNN
F 3 "" H 6048 11048 50  0001 C CNN
	1    6048 11048
	1    0    0    -1  
$EndComp
Wire Wire Line
	5978 10948 6048 10948
Wire Wire Line
	6048 10948 6048 11048
Wire Wire Line
	6744 8123 6744 8773
Wire Wire Line
	5378 10948 5273 10948
Wire Wire Line
	5273 10948 5273 11003
Text Label 6903 10168 0    50   ~ 0
LDO_3.3V
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R44
U 1 1 5D4FE347
P 5193 10508
F 0 "R44" V 4986 10508 50  0000 C CNN
F 1 "1K" V 5077 10508 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" H 5123 10508 50  0001 C CNN
F 3 "" H 5193 10508 50  0000 C CNN
	1    5193 10508
	-1   0    0    1   
$EndComp
Wire Wire Line
	5193 10658 5193 10748
Wire Wire Line
	5193 10748 5378 10748
Wire Wire Line
	5193 10358 5193 10213
Wire Wire Line
	5193 10213 4663 10213
Text Label 4708 10213 0    50   ~ 0
MAIN_3.3V
Text GLabel 4758 10748 0    50   Input ~ 0
UART3-RX
Wire Wire Line
	4758 10748 5193 10748
Connection ~ 5193 10748
Connection ~ 18210 2250
Wire Wire Line
	18210 2250 18210 1580
Wire Wire Line
	6694 8973 7249 8973
Wire Wire Line
	6853 10168 7293 10168
Wire Wire Line
	5684 8268 5037 8268
$Comp
L Transistor_BJT:BC847 Q14
U 1 1 5C40BAF5
P 5524 9263
F 0 "Q14" H 5715 9309 50  0000 L CNN
F 1 "BC847" H 5715 9218 50  0000 L CNN
F 2 "TO_SOT_Packages_SMD:SOT-23" H 5724 9188 50  0001 L CIN
F 3 "http://www.infineon.com/dgdl/Infineon-BC847SERIES_BC848SERIES_BC849SERIES_BC850SERIES-DS-v01_01-en.pdf?fileId=db3a304314dca389011541d4630a1657" H 5524 9263 50  0001 L CNN
	1    5524 9263
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:R-RESCUE-4port-usb-hub R48
U 1 1 5D17DA53
P 6853 10551
F 0 "R48" V 6646 10551 50  0000 C CNN
F 1 "470R" V 6737 10551 50  0000 C CNN
F 2 "Resistors_SMD:R_0603" H 6783 10551 50  0001 C CNN
F 3 "" H 6853 10551 50  0000 C CNN
	1    6853 10551
	-1   0    0    1   
$EndComp
Wire Wire Line
	6853 10701 6853 10748
Connection ~ 6853 10748
Wire Wire Line
	6853 10748 6853 10828
Wire Wire Line
	6853 10401 6853 10168
$Comp
L Transistor_BJT:BC847 Q15
U 1 1 5C715092
P 6953 11028
F 0 "Q15" H 7143 11074 50  0000 L CNN
F 1 "BC847" H 7143 10983 50  0000 L CNN
F 2 "TO_SOT_Packages_SMD:SOT-23" H 7153 10953 50  0001 L CIN
F 3 "http://www.infineon.com/dgdl/Infineon-BC847SERIES_BC848SERIES_BC849SERIES_BC850SERIES-DS-v01_01-en.pdf?fileId=db3a304314dca389011541d4630a1657" H 6953 11028 50  0001 L CNN
	1    6953 11028
	-1   0    0    -1  
$EndComp
$Comp
L Diode:1N5819 D2
U 1 1 5C718211
P 5100 14500
F 0 "D2" H 5100 14284 50  0000 C CNN
F 1 "1N5819" H 5100 14375 50  0000 C CNN
F 2 "Diodes_SMD:D_SOD-123" H 5100 14325 50  0001 C CNN
F 3 "http://www.vishay.com/docs/88525/1n5817.pdf" H 5100 14500 50  0001 C CNN
	1    5100 14500
	-1   0    0    1   
$EndComp
Wire Wire Line
	4950 14500 4850 14500
Wire Wire Line
	4850 14500 4850 14499
Connection ~ 4850 14500
$Comp
L Regulator_Linear:AMS1117-3.3 U9
U 1 1 5C76CC75
P 12660 7595
F 0 "U9" H 12660 7837 50  0000 C CNN
F 1 "RT9161/A-33PV" H 12660 7746 50  0000 C CNN
F 2 "TO_SOT_Packages_SMD:SOT-23" H 12660 7795 50  0001 C CNN
F 3 "http://www.advanced-monolithic.com/pdf/ds1117.pdf" H 12760 7345 50  0001 C CNN
	1    12660 7595
	1    0    0    -1  
$EndComp
Wire Wire Line
	11965 7595 12360 7595
Wire Wire Line
	12960 7595 13270 7595
Wire Wire Line
	12660 7895 12660 7995
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C31
U 1 1 5C815E21
P 11965 7745
F 0 "C31" H 12080 7791 50  0000 L CNN
F 1 "1uF 10V" H 12080 7700 50  0000 L CNN
F 2 "Capacitors_SMD:C_0805" H 12003 7595 50  0001 C CNN
F 3 "" H 11965 7745 50  0000 C CNN
	1    11965 7745
	1    0    0    -1  
$EndComp
Wire Wire Line
	11965 7595 11615 7595
Connection ~ 11965 7595
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C33
U 1 1 5C886751
P 13270 7745
F 0 "C33" H 13385 7791 50  0000 L CNN
F 1 "10uF 10V" H 13385 7700 50  0000 L CNN
F 2 "Capacitors_SMD:C_0805" H 13308 7595 50  0001 C CNN
F 3 "" H 13270 7745 50  0000 C CNN
	1    13270 7745
	1    0    0    -1  
$EndComp
Wire Wire Line
	13270 7595 13430 7595
Connection ~ 13270 7595
$Comp
L 4port-usb-hub-rescue:GND #PWR032
U 1 1 5C99EAB8
P 11965 7945
F 0 "#PWR032" H 11965 7695 50  0001 C CNN
F 1 "GND" H 11970 7772 50  0000 C CNN
F 2 "" H 11965 7945 50  0001 C CNN
F 3 "" H 11965 7945 50  0001 C CNN
	1    11965 7945
	1    0    0    -1  
$EndComp
Wire Wire Line
	11965 7895 11965 7945
$Comp
L 4port-usb-hub-rescue:GND #PWR038
U 1 1 5C9D7E82
P 12660 7995
F 0 "#PWR038" H 12660 7745 50  0001 C CNN
F 1 "GND" H 12665 7822 50  0000 C CNN
F 2 "" H 12660 7995 50  0001 C CNN
F 3 "" H 12660 7995 50  0001 C CNN
	1    12660 7995
	1    0    0    -1  
$EndComp
$Comp
L 4port-usb-hub-rescue:GND #PWR039
U 1 1 5C9D83A2
P 13270 7960
F 0 "#PWR039" H 13270 7710 50  0001 C CNN
F 1 "GND" H 13275 7787 50  0000 C CNN
F 2 "" H 13270 7960 50  0001 C CNN
F 3 "" H 13270 7960 50  0001 C CNN
	1    13270 7960
	1    0    0    -1  
$EndComp
Wire Wire Line
	13270 7895 13270 7960
Text Label 11690 7595 0    50   ~ 0
MAIN_5V_FUSED
Text Label 13300 7595 0    50   ~ 0
LDO_3.3V
Wire Notes Line
	11710 1175 21160 1175
Wire Notes Line
	21160 1175 21160 4850
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C32
U 1 1 5CBF95B7
P 12415 11010
F 0 "C32" H 12530 11056 50  0000 L CNN
F 1 "100nF" H 12530 10965 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603" H 12453 10860 50  0001 C CNN
F 3 "Add cap C32" H 12685 10860 50  0000 C CNN
	1    12415 11010
	1    0    0    -1  
$EndComp
Connection ~ 12415 10860
Wire Wire Line
	12015 10860 12015 11505
$Comp
L 4port-usb-hub-rescue:GND #PWR036
U 1 1 5CD12FBC
P 12415 11250
F 0 "#PWR036" H 12415 11000 50  0001 C CNN
F 1 "GND" H 12420 11077 50  0000 C CNN
F 2 "" H 12415 11250 50  0001 C CNN
F 3 "" H 12415 11250 50  0001 C CNN
	1    12415 11250
	1    0    0    -1  
$EndComp
Wire Wire Line
	12415 11160 12415 11250
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C34
U 1 1 5CDA3021
P 18465 10805
F 0 "C34" H 18580 10851 50  0000 L CNN
F 1 "100nF" H 18580 10760 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603" H 18503 10655 50  0001 C CNN
F 3 "Add cap C34" H 18540 10660 50  0000 C CNN
	1    18465 10805
	-1   0    0    1   
$EndComp
$Comp
L 4port-usb-hub-rescue:GND #PWR044
U 1 1 5CDA3028
P 18465 10565
F 0 "#PWR044" H 18465 10315 50  0001 C CNN
F 1 "GND" H 18470 10392 50  0000 C CNN
F 2 "" H 18465 10565 50  0001 C CNN
F 3 "" H 18465 10565 50  0001 C CNN
	1    18465 10565
	-1   0    0    1   
$EndComp
Wire Wire Line
	18465 10655 18465 10565
Connection ~ 18465 10955
$Comp
L Isolator:LTV-356T U5
U 1 1 5CEC28CA
P 5678 10848
F 0 "U5" H 5678 11173 50  0000 C CNN
F 1 "LTV-356T" H 5678 11082 50  0000 C CNN
F 2 "UCM:LTV-356T" H 5478 10648 50  0001 L CIN
F 3 "http://optoelectronics.liteon.com/upload/download/DS70-2001-010/S_110_LTV-356T%2020140520.pdf" H 5678 10848 50  0001 L CNN
	1    5678 10848
	-1   0    0    -1  
$EndComp
$Comp
L Isolator:LTV-356T U6
U 1 1 5D016F49
P 6234 8873
F 0 "U6" H 6234 9198 50  0000 C CNN
F 1 "LTV-356T" H 6234 9107 50  0000 C CNN
F 2 "UCM:LTV-356T" H 6034 8673 50  0001 L CIN
F 3 "http://optoelectronics.liteon.com/upload/download/DS70-2001-010/S_110_LTV-356T%2020140520.pdf" H 6234 8873 50  0001 L CNN
	1    6234 8873
	1    0    0    -1  
$EndComp
Wire Wire Line
	18665 11810 18665 12160
$Comp
L 4port-usb-hub-rescue:C-RESCUE-4port-usb-hub C27
U 1 1 5C6611F4
P 18815 11810
F 0 "C27" V 18678 11948 50  0000 C CNN
F 1 "100nF" V 18746 11987 50  0000 C CNN
F 2 "Capacitors_SMD:C_0603" H 18930 11719 50  0001 L CNN
F 3 "" H 18815 11810 50  0000 C CNN
	1    18815 11810
	0    1    1    0   
$EndComp
Connection ~ 18465 11160
Wire Wire Line
	19065 11360 19215 11360
Wire Wire Line
	18665 11560 18842 11560
Wire Wire Line
	18842 11560 18842 11360
Wire Wire Line
	18842 11360 19065 11360
Connection ~ 19065 11360
Wire Wire Line
	18665 11360 18745 11360
Wire Wire Line
	18745 11360 18745 11655
Wire Wire Line
	18745 11655 18665 11655
Wire Wire Line
	18665 11655 18665 11810
Connection ~ 18665 11810
Wire Wire Line
	12615 10660 12915 10660
Wire Wire Line
	12915 10360 12915 10460
Connection ~ 12915 10460
Wire Wire Line
	12915 10460 12915 10660
Wire Wire Line
	12615 10460 12725 10460
Wire Wire Line
	12725 10460 12725 10574
Wire Wire Line
	12725 10574 13165 10574
Wire Wire Line
	13165 10574 13165 10660
Connection ~ 13165 10660
$EndSCHEMATC
