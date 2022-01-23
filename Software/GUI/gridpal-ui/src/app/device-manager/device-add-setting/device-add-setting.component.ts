import {
  Component,
  OnInit,
  OnDestroy,
  ViewChild
} from '@angular/core';
import { Router } from '@angular/router';
import { HttpgetService } from '../../services/httpget.service';
import { HttppostService } from '../../services/httppost.service';
import { NgForm } from '@angular/forms';
import { DataRouteService } from '../../services/data-route.service';
import { DataMap } from './dataMap';
import { SerialComComponent } from '../communication-components/serial-com/serial-com.component';
import { RegisterMapComponent } from '../communication-components/register-map/register-map.component';
import { Subscription } from 'rxjs/Subscription';
import { KnxComponent } from '../communication-components/knx/knx.component';
import { WmbusClass } from './wmbus-class';
import { TcpComComponent } from '../communication-components/tcp-com/tcp-com.component';

@Component({
  selector: 'app-device-add-setting',
  templateUrl: './device-add-setting.component.html',
  styleUrls: ['./device-add-setting.component.css']
})
export class DeviceAddSettingComponent implements OnInit, OnDestroy {

  pageHeader: String;
  formHeader: String;
  pageBehaviour: String;
  selectedInterfaceType: String;
  selectedDeviceManufacturerType: String;
  selectedDeviceId: String;
  defaultOption: String = '== SELECT ==';
  SerialDataList: String[] = [];
  registerList: Object[] = [];

  // Objects
  dMapObj = new DataMap();
  wmbusObj = new WmbusClass();

  // Observable Object
  subjectDataRequest: Subscription;
  interfaceRequest: Subscription;
  deviceManufacturerRequest: Subscription;
  deviceTypeRequest: Subscription;
  devicePropertyRequest: Subscription;
  resisterListRequest: Subscription;
  addDevicePostRequest: Subscription;
  settingDevicePostRequest: Subscription;

  // Disable Properties
  deviceNameDisabled: Boolean = false;
  resisterMapDisbaled: Boolean = false;

  // HTML variables
  interface_type: String[];
  interface_type_keys: String[];
  deviceManufacturer: Object[] = [];
  deviceManufacturerList: Object[] = [];
  deviceTypeObjectList: Object = {};
  deviceTypeKeys: String[];
  selectedResisterList: String[];
  deviceSlaveId: String;
  deviceTypeComponent: String;
  wmbusScanObject: Object[] = [];
  scannedManufaturer: string;

  // Hidden Variables
  hideDeviceManfacturer: Boolean = false;
  hideDeviceType: Boolean = false;
  hideConnectionParameter: Boolean = false;
  hideRegister: Boolean = false;
  deviceTypeScanned: Boolean = false;

  @ViewChild(SerialComComponent) serialChild;
  @ViewChild(RegisterMapComponent) registerMapChild;
  @ViewChild(KnxComponent) knxChild;
  @ViewChild(TcpComComponent) tcpChild;

  constructor(
    private _getRoutedData: DataRouteService,
    private _getHttpRequest: HttpgetService,
    private _postHttpRequest: HttppostService,
    private router: Router
  ) { }

  ngOnInit() {
    this.subjectDataRequest = this._getRoutedData
      .getMessage()
      .subscribe(data => {
        if (data === 'empty') {
          this.pageBehaviour = 'addPage';
          this.addDeviceInitial();
        } else {
          this.pageBehaviour = 'settingPage';
          this.settingDeviceInitial(data);
        }
      });
  }

  ngOnDestroy() {
    this.subjectDataRequest.unsubscribe();
    if (this.interfaceRequest) { this.interfaceRequest.unsubscribe(); }
    if (this.deviceManufacturerRequest) { this.deviceManufacturerRequest.unsubscribe(); }
    if (this.devicePropertyRequest) { this.devicePropertyRequest.unsubscribe(); }
    if (this.resisterListRequest) { this.resisterListRequest.unsubscribe(); }
    if (this.addDevicePostRequest) { this.addDevicePostRequest.unsubscribe(); }
    if (this.deviceTypeRequest) { this.deviceTypeRequest.unsubscribe(); }
    if (this.settingDevicePostRequest) { this.settingDevicePostRequest.unsubscribe(); }
  }

  addDeviceInitial(): void {
    this.pageHeader = 'Add Device';
    this.resisterMapDisbaled = false;
    this.formHeader = 'Device Add Form';
    this.deviceNameDisabled = false;
    this.dMapObj.dataMap['name'] = '';
    this.dMapObj.dataMap['label'] = '';
    this.dMapObj.dataMap['interface-type'] = this.defaultOption;
    this.dMapObj.dataMap['manufacturer'] = this.defaultOption;
    this.dMapObj.dataMap['devicetype'] = this.defaultOption;
    this.interfaceRequest = this._getHttpRequest
      .makeGetRequest('rest/vendors/interface')
      .subscribe(data => {
        this.interface_type = JSON.parse(data._body);
        this.interface_type_keys = Object.keys(this.interface_type);
      });
    this.hideDeviceManfacturer = true;
    this.hideDeviceType = true;
    this.hideConnectionParameter = true;
    this.hideRegister = true;
  }

  settingDeviceInitial(data): void {
    this.resisterMapDisbaled = true;
    this.deviceNameDisabled = false;
    this.pageHeader = 'Device Setting';
    this.formHeader = 'Device Setting Form';
    this.dMapObj.dataMap = data;
    this.deviceSlaveId = this.dMapObj.dataMap['id'];
    this.registerList = this.dMapObj.dataMap['register-map'];
    const localInterfaceType: String = this.dMapObj.dataMap['interface-type'];

    if (localInterfaceType.includes(' - ')) {
      this.deviceTypeComponent = localInterfaceType.split(' - ')[1];
    } else {
      this.deviceTypeComponent = localInterfaceType;
    }
    if (this.deviceTypeComponent === 'knx') {
      this.hideRegister = true;
    }
    this.SerialDataList = this.dMapObj.dataMap['connection'];
  }

  // onChange Events
  interfaceTypeChange(value) {
    // reset fields
    this.dMapObj.dataMap['manufacturer'] = this.defaultOption;
    this.dMapObj.dataMap['devicetype'] = this.defaultOption;
    this.hideDeviceManfacturer = true;
    this.hideDeviceType = true;
    this.hideConnectionParameter = true;
    this.hideRegister = true;

    // Get Device manufacturer
    this.selectedInterfaceType = this.getString(value);

    // Convert selectedInterfaceType from string to number
    const tempInterfaceType: String = this.interface_type[+this.selectedInterfaceType];

    if (tempInterfaceType.includes('-')) {
      this.deviceTypeComponent = tempInterfaceType.split('-')[1].trim();
    } else {
      this.deviceTypeComponent = tempInterfaceType;
    }

    this.hideDeviceManfacturer = false;
    if (this.selectedInterfaceType === '7') { // if wireless modbus scan is selected
      this.deviceTypeScanned = true;
      this._getHttpRequest
        .makeGetRequest('rest/vendors/scan?id_interface=7')
        .subscribe(data => {
          this.wmbusScanObject = JSON.parse(data._body);
          this.deviceManufacturer = this.wmbusObj.getManufacturerList(this.wmbusScanObject, 'manufacturer');
          this.deviceManufacturerList = Object.keys(this.deviceManufacturer);
        });
    } else {
      this.deviceManufacturerRequest = this._getHttpRequest
        .makeGetRequest('rest/vendors/manufacturer?id_interface=' + this.selectedInterfaceType)
        .subscribe(data => {
          this.deviceManufacturer = JSON.parse(data._body);
          this.deviceManufacturerList = Object.keys(this.deviceManufacturer);
        });
    }
  }

  deviceManufacturerTypeChange(value) {
    // reset fields
    this.dMapObj.dataMap['devicetype'] = this.defaultOption;
    this.hideDeviceType = true;
    this.hideConnectionParameter = true;
    this.hideRegister = true;
    this.dMapObj.dataMap['devicetype'] = this.defaultOption;
    this.selectedDeviceManufacturerType = this.getString(value);


    if (this.deviceTypeScanned) {
      this.hideDeviceType = false;
      this.scannedManufaturer = this.deviceManufacturer[+this.selectedDeviceManufacturerType] as string;
      this.deviceTypeObjectList = this.wmbusObj.getDeviceList(this.wmbusScanObject, this.scannedManufaturer);
      this.deviceTypeKeys = Object.keys(this.deviceTypeObjectList);
    } else {
      // Get Device Type
      this.hideDeviceType = false;
      this.deviceTypeRequest = this._getHttpRequest
        .makeGetRequest('rest/vendors/device?id_interface=' + this.selectedInterfaceType +
          '&id_manufacturer=' + this.selectedDeviceManufacturerType)
        .subscribe(data => {
          this.deviceTypeObjectList = JSON.parse(data._body);
          this.deviceTypeKeys = Object.keys(this.deviceTypeObjectList);
        });
    }
  }

  deviceTypeChange(value) {
    this.selectedDeviceId = this.getString(value);

    if (this.deviceTypeScanned) {
      const scannedDeviceType: string = this.deviceTypeObjectList[+this.selectedDeviceId].split(' (')[0];
      const scannedDeviceTypeID: string = this.deviceTypeObjectList[+this.selectedDeviceId]
        .split(' (')[1].slice(0, -1);

      this.hideConnectionParameter = false;
      this.hideRegister = false;
      const conReg = this.wmbusObj.getConnectionRegister(this.wmbusScanObject,
        this.scannedManufaturer,
        scannedDeviceType,
        scannedDeviceTypeID);

      this.SerialDataList = conReg['connection'];
      this.registerList = conReg['register-map'];
      this.deviceSlaveId = scannedDeviceTypeID;
      this.registerList.forEach(element => {
        element['offset'] = '';
      });

    } else {
      // this.deviceTypeScanned = false;
      this.devicePropertyRequest = this._getHttpRequest
        .makeGetRequest('rest/vendors/device/config?id=' + this.selectedDeviceId)
        .subscribe(data => {
          this.hideConnectionParameter = false;
          const objectKey = Object.keys(JSON.parse(data._body))[0];
          this.SerialDataList = JSON.parse(data._body)[objectKey].split(':');
          this.deviceSlaveId = '1';
        });

      if (!(this.deviceTypeComponent === 'knx')) {
        this.hideRegister = false;
      }
      this.sleep(200);
      this.resisterListRequest = this._getHttpRequest
        .makeGetRequest('rest/vendors/registermap?id_device=' + this.selectedDeviceId)
        .subscribe(data => {
          this.registerList = JSON.parse(data._body);
          this.registerList.forEach(element => {
            element['offset'] = '';
          });
        });
    }
  }

  sleep(milliseconds) {
    const start = new Date().getTime();
    for (let i = 0; i < 1e7; i++) {
      if ((new Date().getTime() - start) > milliseconds) {
        break;
      }
    }
  }

  onFormSubmit(submittedForm: NgForm) {

    if (this.pageBehaviour === 'addPage') {

      let addDevicePostData: Object = {};
      let tempInterfaceType: String = this.interface_type[submittedForm.value.interfaceType];
      // check if device name field is empty
      if (submittedForm.value.deviceName === '') {
        alert('Device Name not given');
        return;
      }

      if (tempInterfaceType === 'wireless mbus') {
        tempInterfaceType = 'wmbus';
      }
      // console.log(tempInterfaceType);

      if (tempInterfaceType === 'knx') {
        addDevicePostData = {
          'label': submittedForm.value.deviceName,
          'interface-type': tempInterfaceType,
          'manufacturer': this.deviceManufacturer[submittedForm.value.deviceManufacturer],
          'devicetype': this.deviceTypeObjectList[submittedForm.value.deviceType],
          'register-map': this.knxChild.getKnxData()
        };
      } else if (tempInterfaceType === 'wireless mbus (scan)') {
        addDevicePostData = {
          'label': submittedForm.value.deviceName,
          'connection': this.serialChild.serialData()['connection'],
          'register-map': this.registerMapChild.returnRegisterMap(),
          'devicetype': this.deviceTypeObjectList[submittedForm.value.deviceType].split(' (')[0],
          'id': this.serialChild.serialData()['id'],
          'manufacturer': this.scannedManufaturer,
          'interface-type': 'wmbus'
        };
      } else if (tempInterfaceType === 'modbus - tcp' || tempInterfaceType === 'mbus - tcp') {
        addDevicePostData = {
          'label': submittedForm.value.deviceName,
          'interface-type': tempInterfaceType,
          'manufacturer': this.deviceManufacturer[submittedForm.value.deviceManufacturer],
          'devicetype': this.deviceTypeObjectList[submittedForm.value.deviceType],
          'connection': this.tcpChild.tcpComData(),
          'register-map': this.registerMapChild.returnRegisterMap()
        };
      } else {
        addDevicePostData = {
          'label': submittedForm.value.deviceName,
          'connection': this.serialChild.serialData()['connection'],
          'interface-type': tempInterfaceType,
          'register-map': this.registerMapChild.returnRegisterMap(),
          'manufacturer': this.deviceManufacturer[submittedForm.value.deviceManufacturer],
          'devicetype': this.deviceTypeObjectList[submittedForm.value.deviceType],
          'id': this.serialChild.serialData()['id'],
          'postundefinedonreaderror': 'true'
        };
      }
    //  console.log(addDevicePostData);

      this.addDevicePostRequest = this._postHttpRequest
        .makePostRequest('rest/devices/add', addDevicePostData)
        .subscribe(data => {
          const response: any = JSON.parse(data._body);
          if (response['status'] === 'success') {
            this.router.navigateByUrl('/devicemanager');
          }
        });

    } else if (this.pageBehaviour === 'settingPage') {
      //console.log(submittedForm.value);
      let settingdevicePostData = {};      
      if (submittedForm.value.interfaceType === 'knx') {
        settingdevicePostData = {
          'name': this.dMapObj.dataMap['name'],
          'label': submittedForm.value.deviceName,
          'register-map': this.registerMapChild.returnRegisterMap()
        };
      } else if (submittedForm.value.interfaceType === 'modbus - tcp' || submittedForm.value.interfaceType === 'mbus - tcp') {
        settingdevicePostData = {
          'name': this.dMapObj.dataMap['name'],
          'label': submittedForm.value.deviceName,
          'register-map': this.registerMapChild.returnRegisterMap(),
          'connection': this.tcpChild.tcpComData()
        };
      } else {
        settingdevicePostData = {
          'name': this.dMapObj.dataMap['name'],
          'label': submittedForm.value.deviceName,
          'connection': this.serialChild.serialData()['connection'],
          'id': this.serialChild.serialData()['id'],
          'register-map': this.registerMapChild.returnRegisterMap()
        };
      }

      //  console.log(settingdevicePostData);

      this.settingDevicePostRequest = this._postHttpRequest
        .makePostRequest('rest/devices/update', settingdevicePostData)
        .subscribe(data => {
          const response: any = JSON.parse(data._body);
          if (response['status'] === 'success') {
            this.router.navigateByUrl('/devicemanager');
          }
        });
    }
  }

  getString(getValue: String): String {
    return getValue.split(':')[1].trim();
  }
}
