export class DataMap {
  constructor() { }

  dataMap: any = {
    'name': String,
    'label': String,
    'connection': [
      String, // Serial Port [0]
      String, // Baud Rate [1]
      String, // Data bit [2]
      String, // Parity Bit[3]
      String, // Stop Bit[4]
      'rtu',
      '1000',
      '5000'
    ],
    // 'slave-type': addDeviceForm.value.interfaceType,
    'interface-type': String,
    'register-map': [],
    'manufacturer': String,
    'devicetype': String,
    'id': String,
    'length': '1',
    'type': 'holding'
  };


  deviceName: String;
  InterfaceType: String;
  deviceManufacturer: String;
  deviceType: String;
}
