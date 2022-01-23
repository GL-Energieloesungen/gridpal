import {
  Component,
  Input,
  OnInit,
  OnChanges
} from '@angular/core';

@Component({
  selector: 'app-register-map',
  templateUrl: './register-map.component.html',
  styleUrls: ['./register-map.component.css']
})
export class RegisterMapComponent implements OnInit, OnChanges {

  constructor() { }

  selectionStatus: Boolean = true;
  selecteditemObject: Object[] = [];
  eventObjectList: Object[] = [];
  @Input() resisterMap: Object[];
  @Input() resisterMapDisabled: Boolean;

  ngOnInit() {
    if (this.resisterMapDisabled) {
      this.selecteditemObject = this.resisterMap;
    }
  }

  ngOnChanges(): void {
    this.selecteditemObject = this.resisterMap.slice();
  }

  checkBoxClick(selectedItem: any, event) {
    this.eventObjectList.push(event);
    if (event.target.checked) {
      this.selecteditemObject.push(selectedItem);
    } else {
      const index = this.selecteditemObject.indexOf(selectedItem);
      this.selecteditemObject.splice(index, 1);
    }
  }

  returnRegisterMap(): Object {
    return this.selecteditemObject;
  }

  selectAllButton() {
    this.selectionStatus = true;
    this.selecteditemObject = this.resisterMap.slice();
  }

  clearAllButton() {
    this.eventObjectList.forEach(element => {
      element['target'].checked = false;
    });
    this.selectionStatus = false;
    this.selecteditemObject = [];
  }
}
