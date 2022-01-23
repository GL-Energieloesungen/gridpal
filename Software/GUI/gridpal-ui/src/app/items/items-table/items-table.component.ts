import { Component, OnInit, Input } from '@angular/core';
import { DataRouteService } from '../../services/data-route.service';

@Component({
  selector: 'app-items-table',
  templateUrl: './items-table.component.html',
  styleUrls: ['./items-table.component.css']
})
export class ItemsTableComponent implements OnInit {

  @Input() tableItemList: string;

  name: string;
  itemList: string[];

  constructor(private _dataSubject: DataRouteService) { }

  ngOnInit() {
    this.name = Object.keys(this.tableItemList)[0];
    this.itemList = Object.values(this.tableItemList);
  }

  dataObject(value) {
    this._dataSubject.routeData(value);
  }
}
