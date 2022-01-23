export class Home {
  // System Interfaces
  systemInterface: Object = {
    'lan': Boolean,
    'wlan24': Boolean,
    'wlan50': Boolean,
    'usb1': Boolean,
    'usb2': Boolean,
    'usb3': Boolean,
    'usb4': Boolean,
    'usb5': Boolean,
    'uart': Boolean
  };

  // Bus Connectors
  busConnectors: Object = {
    'modbus-rs485': Boolean,
    'mbus': Boolean,
    'wireless-mbus': Boolean,
    'knx-eib': Boolean,
    'bluetooth': Boolean
  };

  // Resource useage
  resourceUseage: Object = {
    'cpu': String,
    'ram': String,
    'ethernet': {
      'device': String,
      'rx': String,
      'tx': String
    },
    'wifi': {
      'device': String,
      'frequency': String,
      'rx': String,
      'tx': String
    }
  };



}
