import { Component, OnInit, OnDestroy } from '@angular/core';
import { DataRouteService } from '../../services/data-route.service';
import { Subscription } from 'rxjs/Subscription';
import { DatePipe } from '@angular/common';
import { Router } from '@angular/router';
import { SourceLink } from '../../module/api-source';
import { HttpgetService } from '../../services/httpget.service';

@Component({
  selector: 'app-item-history',
  templateUrl: './item-history.component.html',
  styleUrls: ['./item-history.component.css']
})
export class ItemHistoryComponent implements OnInit, OnDestroy {

  subjectDataRequest: Subscription;
  viewGetRequest: Subscription;
  isMeridian: Boolean = false;
  itemObject: String;
  startDate: Date = new Date();
  endDate: Date = new Date();
  startTime: Date = new Date(new Date().setHours(0, 0));
  endTime: Date = new Date();
  sourceObj = new SourceLink();
  viewTableData: Object;
  dataPoints: number;
  hideViewData: Boolean = true;

  constructor(private _getRoutedData: DataRouteService,
    private router: Router,
    private _getRequest: HttpgetService) { }

  ngOnInit() {
    this.subjectDataRequest = this._getRoutedData
      .getMessage()
      .subscribe(data => this.itemObject = data);
  }

  ngOnDestroy() {
    this.subjectDataRequest.unsubscribe();
    if (this.viewGetRequest) { this.viewGetRequest.unsubscribe(); }
  }

  historyDownload() {
    const ROOT_URL: String = this.sourceObj._base_url;
    const url: string = this.getURL(false);
    if (this.itemObject['original'] === undefined) {
      alert('Can not make download request, Item name not found');
      this.router.navigateByUrl('/live-data');
    } else {
      window.open(ROOT_URL + url);
    }
  }

  transformDate(date: Date): String {
    return new DatePipe('en-US').transform(date, 'yyyy-MM-dd');
  }

  transformTime(time: Date): String {
    if (time === undefined) {
      time = new Date(new Date().setHours(0, 0, 0, 0));
    }
    return new DatePipe('en-US').transform(time, 'HH:mm:ss');
  }

  viewButton(): void {
    // this.hideViewData = false;
    const url: string = this.getURL(true);
    this.viewGetRequest = this._getRequest.makeGetRequest(url)
      .subscribe(data => {
        this.hideViewData = false;
        this.viewTableData = JSON.parse(data._body)['data'];
        this.dataPoints = JSON.parse(data._body)['datapoints'];
        
      }, err => {
        if (err.status === 500) {
          alert('Data Not Found!!');
        }
        
      });
  }

  getURL(option: Boolean): string {
    const strStartDate: String = this.transformDate(this.startDate);
    const strEndDate: String = this.transformDate(this.endDate);
    const strStartTime: String = this.transformTime(this.startTime);
    const strEndTime: String = this.transformTime(this.endTime);
    const fromDate: string = strStartDate + 'T' + strStartTime;
    const toDate: string = strEndDate + 'T' + strEndTime;

    let url: string;
    if (option) {
      // View for true
      url = 'rest/persistence/items/' + this.itemObject['original'];
    } else {
      // download for false
      url = 'rest/devices/export/items/' + this.itemObject['original'];
    }

    if (strStartDate === null && strEndDate === null) {
      url = url;
    } else if (strStartDate === null) {
      url = url + '?endtime=' + encodeURIComponent(toDate);
    } else if (strEndDate === null) {
      url = url + '?starttime=' + encodeURIComponent(fromDate);
    } else {
      url = url + '?starttime=' + encodeURIComponent(fromDate) + '&endtime=' + encodeURIComponent(toDate);
    }

    return url;
  }
}
