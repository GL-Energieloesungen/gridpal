import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-view-data',
  templateUrl: './view-data.component.html',
  styleUrls: ['./view-data.component.css']
})
export class ViewDataComponent implements OnInit {
  @Input() tableData: Object;
  @Input() dataPoints: number;
  sInt: number;
  
  ngOnInit(): void {
    if (this.dataPoints <= 100) {
      this.sInt = this.dataPoints;
    } else {
      this.sInt = 100;
    }
  }
}
