import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SourceLink } from '../module/api-source';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

@Injectable()
export class HttpgetService {

  sourceLink = new SourceLink();
  constructor(private _http: Http) { }

  makeGetRequest(_api: string) {
    const headers = new Headers(
      {
        'Accept': 'application/json'
      }
    );

    const options = new RequestOptions(
      {
        headers: headers,
        method: 'GET'
      }
    );

    return this._http
      .get(this.sourceLink._base_url + _api, options)
      .map((response: Response) => response)
      .catch(this._errorGetHttpData);
  }

  _errorGetHttpData(error: Response) {
    return Observable.throw(error || 'Http Get Error');
  }
}
