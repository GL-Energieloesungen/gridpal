import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpgetService } from '../services/httpget.service';
import { InternetData } from './internet-data-model';
import { SourceLink } from '../module/api-source';
import { Subscription } from 'rxjs/Subscription';
import { NgProgress } from 'ngx-progressbar';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-internet',
  templateUrl: './internet.component.html',
  styleUrls: ['./internet.component.css']
})
export class InternetComponent implements OnInit, OnDestroy {

  httpStatusRequest: Subscription;
  ip_address: String;
  id: String;
  sys_version: String;
  ddns_ip: String;
  uptime: String;
  internetData = new InternetData();
  sourceLink = new SourceLink();
  httpTarrifRequest: Subscription;
  tarrifHistoryArray: any = [];
  interval = Observable.interval(10 * 1000);
  intervalRequest: Subscription;

  constructor(private _getRequest: HttpgetService, private ngProgress: NgProgress) {
  }

  ngOnInit() {
    this.httpStatusRequest = this._getRequest
      .makeGetRequest('rest/system/internet/status')
      .subscribe(data => {
        this.ngProgress.start();
        this.ngProgress.inc(20);
        this.internetData = JSON.parse(data._body);
        this.ip_address = this.internetData['interface']['ip-address'];
        this.id = this.internetData['interface']['id'];
        this.sys_version = this.internetData['interface']['system-version'];
        this.ddns_ip = this.internetData['router']['ddns-ip-address'];
        this.uptime = this.internetData['router']['active-since'];
        this.ngProgress.done();
      });
      this.intervalRequest = this.interval.subscribe(() => {
        this.getTraffic();
      });
  }

  getTraffic(){
    this.httpTarrifRequest = this._getRequest
    .makeGetRequest('rest/system/resources/traffic')
    .subscribe(data => {
      var trafficData = JSON.parse(data.text())
      this.tarrifHistoryArray = trafficData.history;
      console.debug(this.tarrifHistoryArray);      
    });
  }

  ngOnDestroy() {
    this.httpStatusRequest.unsubscribe();
    this.httpStatusRequest.unsubscribe();
    this.intervalRequest.unsubscribe();
  }

}
