import { Component, OnInit, OnDestroy, HostListener } from '@angular/core';
import { HttpgetService } from './services/httpget.service';
import { Observable } from 'rxjs/Observable';
import { SourceLink } from './module/api-source';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: [
    './app.component.css'
  ]
})

export class AppComponent implements OnInit, OnDestroy {

  gridpal_logo = 'assets/images/logo.png';
  initialDataObject: number;
  activity: Boolean = false;
  interval = Observable.interval(10 * 1000);
  wifiInterval = Observable.interval(30 * 1000);
  initialHttpRequest: Subscription;
  wifiHttpRequest: Subscription;
  srcLink: SourceLink = new SourceLink();
  ipAddress: string;
  ipAddressHidden: Boolean = true;

  constructor(private _getHttpData: HttpgetService) {  }

  @HostListener('window:keyup', ['$event'])
  keyEvent(event: KeyboardEvent) {
    if (event.key === 'z' && event.ctrlKey && event.altKey) {
      this.ipAddressHidden = !this.ipAddressHidden;
    } else {

    }
  }

  ngOnInit() {
    this.wifiAPList();
    this.ipAddress = localStorage.getItem('IP');
    this.initialHttpRequest = this._getHttpData
      .makeGetRequest('rest')
      .subscribe(data => {
        this.initialDataObject = data.status;
      }, err => {
        this.initialDataObject = err.status;
      }
      );
    this.interval.subscribe(() => this.systemStatus());
    this.wifiInterval.subscribe(() => this.wifiAPList());
  }

  systemStatus() {
    this.initialHttpRequest = this._getHttpData
      .makeGetRequest('rest')
      .subscribe(() => {
        this.activity = false;
      }, () => {
        this.activity = true;
      }
      );
  }

  wifiAPList(): void {
    this.wifiHttpRequest = this._getHttpData
      .makeGetRequest('rest/wireless/list')
      .subscribe(data => {
        localStorage.setItem('ap_list', data._body);
      });
  }

  changeIP(): void {
    localStorage.setItem('IP', this.ipAddress);
    window.location.reload();
  }

  resetIP(): void {
    localStorage.setItem('IP', window.location.hostname);
    window.location.reload();
  }

  ngOnDestroy(): void {
    if (this.initialHttpRequest) { this.initialHttpRequest.unsubscribe(); }
  }


}
