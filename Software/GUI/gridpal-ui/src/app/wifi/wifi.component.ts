import { Component, OnInit, OnDestroy, TemplateRef } from '@angular/core';
import { BsModalService } from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal/bs-modal-ref.service';
import { HttpgetService } from '../services/httpget.service';
import { HttppostService } from '../services/httppost.service';
import { Subscription } from 'rxjs/Subscription';
import { NgProgress } from 'ngx-progressbar';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-wifi',
  templateUrl: './wifi.component.html',
  styleUrls: ['./wifi.component.css']
})
export class WifiComponent implements OnInit, OnDestroy {

  modalRef: BsModalRef;
  passType: String = 'password';
  modalData: Object;
  wirelessListData: Object[];
  getListDataSubscriber: Subscription;
  wirelessStatusData: Object;
  wirelessConnectionObjSSID: String;
  wirelessConnectionObjEncryp: String;
  errorMsg: String;
  activeConnectionTable: Boolean = true;
  availableConnectionTable: Boolean = true;
  otherButton: Boolean = true;
  pageStatus: Boolean = false;
  accessPoints: Object[] = [];
  activeConnectionInterval = Observable.interval(5 * 1000);

  getStatusHttpRequest: Subscription;
  intervalObservableActiveConnection: Subscription;
  onDisconnectHttpRequest: Subscription;
  forceRefreshHttpRequest: Subscription;


  constructor(private modalService: BsModalService,
    private ngProgress: NgProgress,
    private _getHttpData: HttpgetService,
    private _postHttpData: HttppostService) { }

  ngOnInit() {
    this.ngProgress.start();

    this.getStatus().then(
      resolveData => {

        this.ngProgress.set(20);
        this.ngProgress.inc(50);
        this.renderAPList(resolveData);
        this.ngProgress.done();
      },
      rejectData => { }
    );

    // this.intervalObservableAPlist = this.APlistInterval.subscribe(() => this.renderAPList(this.wirelessStatusData));
    this.intervalObservableActiveConnection = this.activeConnectionInterval.subscribe(() => {
      this.getStatus();
      this.renderAPList(this.wirelessStatusData);
    });
  }

  ngOnDestroy() {
    if (this.intervalObservableActiveConnection) {
      this.intervalObservableActiveConnection.unsubscribe();
    }
    if (this.getStatusHttpRequest) {
      this.getStatusHttpRequest.unsubscribe();
    }
    if (this.forceRefreshHttpRequest) {
      this.forceRefreshHttpRequest.unsubscribe();
    }
    if (this.onDisconnectHttpRequest) {
      this.onDisconnectHttpRequest.unsubscribe();
    }
  }

  renderAPList(resolveData: Object): void {
    this.accessPoints = JSON.parse(localStorage.getItem('ap_list'));

    if (resolveData['connected'] === 'true') {
      // Removed Conneced SSID from AP List
      this.pageStatus = false;
      for (let index = 0; index < this.accessPoints.length; index++) {
        if (this.accessPoints[index]['ssid'] === resolveData['connection']['name']) {
          this.wirelessConnectionObjSSID = this.wirelessStatusData['connection']['name'];
          this.wirelessConnectionObjEncryp = this.accessPoints[index]['security'];
          this.activeConnectionTable = false;
          this.accessPoints.splice(index, 1);
        }
      } //
      this.wirelessListData = this.accessPoints;
    } else if (resolveData['connected'] === 'false' && 'status' in resolveData) {
      this.errorMsg = resolveData['status'];
      this.pageStatus = true;
    } else {
      this.pageStatus = false;
      this.activeConnectionTable = true;
      this.wirelessListData = this.accessPoints;
    }
    this.availableConnectionTable = false;
  }



  getStatus(): Promise<Object> {
    return new Promise((resolve, reject) => {
      this.getStatusHttpRequest = this._getHttpData.makeGetRequest('rest/wireless/status').subscribe(
        data => {
          this.wirelessStatusData = JSON.parse(data._body);
          resolve(this.wirelessStatusData);
        },
        () => {
          const errorStatus = { error: 'data_error' };
          reject(errorStatus);
        }
      );
    });
  }

  getTableClass(signalStrength): String {
    if (signalStrength >= 85 && signalStrength <= 100) {
      return 'list-group-item-success';
    } else if (signalStrength >= 55 && signalStrength <= 84) {
      return 'list-group-item-warning';
    } else if (signalStrength <= 54) {
      return 'list-group-item-danger';
    }
  }

  openModal(template: TemplateRef<any>) {
    this.modalData = {
      ssid: '',
      password: '',
      passwordEn: false
    };

    this.modalRef = this.modalService.show(template, this.modalData);
  }


  getModal(secureTemplate: TemplateRef<any>, ssid: String, encryptionStatus: String) {
    this.modalData = { ssid: ssid, passwordEn: false };

    if (encryptionStatus === 'true') {
      this.modalData['passwordEn'] = true;
    }
    this.modalRef = this.modalService.show(secureTemplate, this.modalData);
  }

  showPass(event): void {
    if (event.target.checked === true) {
      this.passType = 'text';
    } else if (event.target.checked === false) {
      this.passType = 'password';
    }
  }

  enablePass(event): void {
    if (event.target.checked === true) {
      this.modalData['passwordEn'] = false;
    } else if (event.target.checked === false) {
      this.modalData['passwordEn'] = true;
    }
  }

  onConnectClick(modalData: Object) {
    this.ngProgress.start();

    if (!modalData['passwordEn'] && modalData['password'] === '') {
      this.ngProgress.inc(20);
      alert('Password Field Empty');
      this.ngProgress.done();
      return;
    }
    this.modalRef.hide();
    delete modalData['passwordEn'];
    this._postHttpData.makePostRequest('rest/wireless/connect', modalData).subscribe(data => {
      this.ngProgress.set(20);
      const responseData = JSON.parse(data._body);
      if (responseData['status'] === 'success') {
        // this.ngProgress.inc(90);
        this.ngProgress.done();
        window.location.reload();
      } else if (responseData['status'] === 'failed') {
        this.ngProgress.done();
        alert(responseData['exception']['message']);
        // this.ngProgress.inc(90);
        this.ngProgress.done();
        window.location.reload();
      }
    });
  }

  getSecurityState(encryption: String): Boolean {
    if (encryption === 'WPA' || encryption === 'WPA1' || encryption === 'WPA2' || encryption === 'WPA1 WPA2') {
      return true;
    } else {
      return false;
    }
  }

  getSecurityString(encryption: String): String {
    if (encryption === 'WPA' || encryption === 'WPA1' || encryption === 'WPA2' || encryption === 'WPA1 WPA2') {
      return encryption;
    } else {
      return 'Open';
    }
  }

  onDiconnect(): void {
    this.ngProgress.start();
    this.onDisconnectHttpRequest = this._getHttpData.makeGetRequest('rest/wireless/disconnect').subscribe(data => {
      const responceData = JSON.parse(data._body);

      this.ngProgress.set(30);
      if (responceData['status'] === 'success') {
        this.ngProgress.inc(60);
        this.ngProgress.done();
        window.location.reload();
      }
    });
  }



  forgetNetwork(modalData: Object): void {
    console.log(modalData);
    delete modalData['passwordEn'];
    console.log(modalData);

    this._postHttpData.makePostRequest('rest/wireless/delete', modalData).subscribe(data => {
      const responseData = JSON.parse(data._body);
      console.log(responseData);

    });
  }

  forceRefresh(): void {
    const apList = 'ap_list';
    this.ngProgress.start();
    this.forceRefreshHttpRequest = this._getHttpData.makeGetRequest('rest/wireless/list').subscribe(data => {
      this.ngProgress.inc(30);
      const responseData = data._body;
      localStorage.removeItem(apList);
      localStorage.setItem(apList, responseData);
      this.ngProgress.done();
      window.location.reload();
    });
  }
}
