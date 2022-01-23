import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/throw';
import { SourceLink } from '../module/api-source';

@Injectable()
export class HttppostService {


  constructor(private _http: Http) { }
  sourceLink = new SourceLink();

  makePostRequest(_api: string, _body: Object) {
    const headers = new Headers(
      {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      }
    );
    const options = new RequestOptions(
      {
        headers: headers,
        method: 'POST'
      }
    );

    return this._http
      .post(this.sourceLink._base_url + _api, _body, options)
      .catch(this._httpPostError);
  }

  _httpPostError(error: Response) {
    return Observable.throw(error || 'Http Post Error');
  }


}
