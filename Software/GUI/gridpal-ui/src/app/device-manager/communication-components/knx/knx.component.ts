import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-knx',
  templateUrl: './knx.component.html',
  styleUrls: ['./knx.component.css']
})
export class KnxComponent {

  @Input() knxData: String[];
  
  typeList: String[] = [
    'Number',
    'Switch'
  ];
   
  getKnxData() {
    return this.knxData;
  }
} 
