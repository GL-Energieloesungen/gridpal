import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { HttpgetService } from '../../../services/httpget.service';

@Component({
  selector: 'app-serial-com',
  templateUrl: './serial-com.component.html',
  styleUrls: ['./serial-com.component.css']
})
export class SerialComComponent implements OnInit, OnDestroy {

  baudRate: String[] = [
    '2400',
    '4800',
    '9600',
    '14400',
    '19200',
    '38400',
    '57600',
    '115200',
    '128000',
    '256000'
  ];
  serialPort: String[];
  parity: String[] = ['none', 'even', 'odd'];
  stopBit: String[] = ['1', '2'];
  dataBit: String[] = ['6', '7', '8'];
  defaultOption: String = '== SELECT ==';

  // Observable
  serialPortRequest;

  @Input() SerialData: String[];
  @Input() deviceSlaveAddress: String;

  constructor(private _getRequest: HttpgetService) { }

  ngOnInit() {
    this.serialPortRequest = this._getRequest.makeGetRequest('rest/system/serials')
      .subscribe(data => {
        this.serialPort = JSON.parse(data._body);
      });

    if (this.SerialData.length === 0) {
      this.SerialData[0] = this.defaultOption;
      this.SerialData[1] = this.defaultOption;
      this.SerialData[2] = this.defaultOption;
      this.SerialData[3] = this.defaultOption;
      this.SerialData[4] = this.defaultOption;
    }
  }

  serialData(): Object {
    return {
      'id': this.deviceSlaveAddress.toString(),
      'connection': [
        this.SerialData[0],
        this.SerialData[1],
        this.SerialData[2],
        this.SerialData[3],
        this.SerialData[4],
        'rtu',
        '1000',
        '5000'
      ]
    };
  }

  ngOnDestroy() {
    this.serialPortRequest.unsubscribe();
  }

}
