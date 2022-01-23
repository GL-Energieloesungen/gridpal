import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-tcp-com',
  templateUrl: './tcp-com.component.html',
  styleUrls: ['./tcp-com.component.css']
})
export class TcpComComponent implements OnInit {

  @Input() SerialData;
  constructor() { }

  ngOnInit() {
  }

  tcpComData(): Object {
    return this.SerialData;
  }
}
