export class SourceLink {

  _host_address: string = localStorage.getItem('IP'); // window.location.hostname;
  //_port: String = window.location.port + '/';
  _port: String = '8080/';
  _base_url: string = window.location.protocol + '//' + this._host_address + ':' + this._port;
  // _base_url: string = window.location.protocol + '//' + this._host_address + '/';
}
