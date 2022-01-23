import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpgetService } from '../services/httpget.service';
import { Observable } from 'rxjs/Observable';
import { Subscription } from 'rxjs/Subscription';
import { ItemDataCheck } from './itemDataCheck';
import { NgProgress } from 'ngx-progressbar';

@Component({
  selector: 'app-items',
  templateUrl: './items.component.html',
  styleUrls: ['./items.component.css']
})
export class ItemsComponent implements OnInit, OnDestroy {

  interval = Observable.interval(2 * 1000);

  itemCheck = new ItemDataCheck();
  getGroupListRequest: Subscription;
  intervalRequest: Subscription;
  groupNamesList: string[] = [];
  itemsObject: Object[] = [];
  tableData: Object[] = [];
  oldTableData: Object[] = [];

  constructor(private _getHttpData: HttpgetService, private ngProgress: NgProgress) { }

  ngOnInit() {
    this.ngProgress.start();
    this.getData();
    this.ngProgress.done();
    this.intervalRequest = this.interval.subscribe(() => this.getData());
  }

  getData() {
    this.getGroupListRequest = this._getHttpData
      .makeGetRequest('rest/items?recursive=true')
      .subscribe(data => {
        this.itemsObject = JSON.parse(data._body);
        this.groupNamesList = this.itemCheck.getGroupList(this.itemsObject);
        const tempTableData = this.itemCheck.tempTableData(this.groupNamesList, this.itemsObject);
        this.tableData = this.itemCheck.newTabledata(tempTableData, this.oldTableData);
        this.oldTableData = tempTableData;
      });
  }

  ngOnDestroy() {
    if (this.getGroupListRequest) { this.getGroupListRequest.unsubscribe(); }
    if (this.intervalRequest) { this.intervalRequest.unsubscribe(); }
  }
}
