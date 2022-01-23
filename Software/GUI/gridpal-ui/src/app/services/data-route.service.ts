import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Observable } from 'rxjs/Observable';


@Injectable()
export class DataRouteService {

  public routeDataSubject = new BehaviorSubject<any>('empty');

  routeData(dataObject: Object) {
    this.routeDataSubject.next(dataObject);
  }

  getMessage(): Observable<any> {
    return this.routeDataSubject.asObservable();
  }
}
