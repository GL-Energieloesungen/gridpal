import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpgetService } from '../services/httpget.service';
import { Observable } from 'rxjs/Observable';
import { SourceLink } from '../module/api-source';
import { Home } from '../module/home';
import { Subscription } from 'rxjs/Subscription';
import { NgProgress } from 'ngx-progressbar';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/interval';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: [
    './home.component.css'
  ]
})
export class HomeComponent implements OnInit, OnDestroy {

  interval = Observable.interval(2 * 1000);
  wifiTX: string;
  wifiRX: String;
  lanTX: String;
  lanRX: String;
  interfaceRequest: Subscription;
  busRequest: Subscription;
  resourceRequest: Subscription;
  intervalRequest: Subscription;
  homePage = new Home();
  sourceLink = new SourceLink();

  testCheck: boolean;

  constructor(private _getRequest: HttpgetService, private ngProgress: NgProgress) { }

  ngOnInit() {
    this.ngProgress.start();
    this.homePage.resourceUseage['cpu'] = '0.00%';
    this.homePage.resourceUseage['ram'] = '0.00/0.00 GB';
    this.wifiRX = '0.00';
    this.wifiTX = '0.00';
    this.lanRX = '0.00';
    this.lanTX = '0.00';
    this.ngProgress.set(20);
    this.getSystemResources();
    this.ngProgress.inc(20);
    this.getSystemInterfaces();
    this.ngProgress.inc(20);
    this.getBusConnections();
    this.ngProgress.done();
    this.intervalRequest = this.interval.subscribe(() => {
      this.getSystemResources();
    });
    this.testCheck = false;
  }

  ngOnDestroy() {
    this.interfaceRequest.unsubscribe();
    this.intervalRequest.unsubscribe();
    this.busRequest.unsubscribe();
    this.resourceRequest.unsubscribe();
  }

  onValueChange(index: string, value: boolean) {
    this.ngProgress.start();
    this.ngProgress.set(20);
    this.homePage.busConnectors[index] = value;
    this.ngProgress.inc(50);
    console.log(this.homePage.busConnectors);
    this.ngProgress.done();
  }

  getSystemInterfaces() {
    this.interfaceRequest = this._getRequest
      .makeGetRequest('rest/system/interfaces')
      .subscribe(data => {
        this.homePage.systemInterface = JSON.parse(data._body);
      });
  }

  getBusConnections() {
    this.busRequest = this._getRequest
      .makeGetRequest('rest/system/buses')
      .subscribe(data => {
        this.homePage.busConnectors = JSON.parse(data._body);
      });
  }

  getSystemResources() {
    this.resourceRequest = this._getRequest.makeGetRequest('rest/system/resources').subscribe(data => {
      this.homePage.resourceUseage = JSON.parse(data._body);
      this.wifiRX = this.getMB(this.homePage.resourceUseage['wifi'][0]['rx']);
      this.wifiTX = this.getMB(this.homePage.resourceUseage['wifi'][0]['tx']);
      this.lanRX = this.getMB(this.homePage.resourceUseage['ethernet'][0]['rx']);
      this.lanTX = this.getMB(this.homePage.resourceUseage['ethernet'][0]['tx']);
    });
  }

  getMB(useageNumber: any) {
    return Number(useageNumber * 0.000001).toFixed(2);
  }

}
