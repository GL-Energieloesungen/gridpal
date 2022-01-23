import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpgetService } from '../services/httpget.service';
import { DataRouteService } from '../services/data-route.service';
import { Router } from '@angular/router';
import { NgProgress } from 'ngx-progressbar';


@Component({
  selector: 'app-device-manager',
  templateUrl: './device-manager.component.html',
  styleUrls: ['./device-manager.component.css']
})
export class DeviceManagerComponent implements OnInit, OnDestroy {

  public _getItemData = [];
  private getDataRequest;
  private deleteDataRequest;
  private deleteAllDataRequest;

  constructor(
    private _getRequest: HttpgetService,
    private _dataSubject: DataRouteService,
    private router: Router,
    private ngProgress: NgProgress
  ) { }

  ngOnInit() {
    this.ngProgress.start();
    this.getDataRequest = this._getRequest.makeGetRequest('rest/devices')
        .subscribe(itemHttpResponse => {
          this.ngProgress.set(50);
      this._getItemData = JSON.parse(itemHttpResponse._body);
      this._getItemData.forEach(element => {
        // changing wmbus to wireless mbus
        if (element['interface-type'] === 'wmbus') {
          element['interface-type'] = 'wireless mbus';
        }
      }); this.ngProgress.set(40);
    }, err => {
      alert('Could not Get Device List');
    });
    this.ngProgress.done();
  }

  ngOnDestroy() {
    this.getDataRequest.unsubscribe();
    if (this.deleteDataObject === undefined) { this.deleteDataRequest.unsubscribe(); }
  }

  sendEmptyObject(): void {
    this._dataSubject.routeData('empty');
  }

  sendDataObject(dataObject): void {
    this._dataSubject.routeData(dataObject);
  }

  deleteDataObject(dataObject): void {
    const deleteObject = confirm('Do you want to delete ' + dataObject + '?');
    if (deleteObject) {
      this.deleteDataRequest = this._getRequest.makeGetRequest('rest/devices/remove/' + encodeURIComponent(dataObject))
        .subscribe(data => {
          const response: any = JSON.parse(data._body);
          if (response.status.toString() === 'success') {
            this.router.navigateByUrl('/devicemanager');
          }
        });
    }
  }

  deleteAllObject() {
    const deleteObject = confirm('Do you want to delete all the devices?');
    if (deleteObject) {
      this.deleteAllDataRequest = this._getRequest.makeGetRequest('rest/devices/remove/all')
        .subscribe(data => {
          const response: any = JSON.parse(data._body);
          if (response.status === 'success') {
            window.location.reload();
          }
        });
    }
  }

}
