import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { LocalStorageModule } from '@ngx-pwa/local-storage';

import { HttpModule } from '@angular/http';
import { routes } from './app.router';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { InternetComponent } from './internet/internet.component';
import { DeviceManagerComponent } from './device-manager/device-manager.component';
import { FormsModule } from '@angular/forms';

import { HttpgetService } from './services/httpget.service';
import { HttppostService } from './services/httppost.service';
import { DataRouteService } from './services/data-route.service';
import { SerialComComponent } from './device-manager/communication-components/serial-com/serial-com.component';
import { TcpComComponent } from './device-manager/communication-components/tcp-com/tcp-com.component';
import { RegisterMapComponent } from './device-manager/communication-components/register-map/register-map.component';
import { DeviceAddSettingComponent } from './device-manager/device-add-setting/device-add-setting.component';
import { ItemsComponent } from './items/items.component';
import { ItemsTableComponent } from './items/items-table/items-table.component';
import { ItemHistoryComponent } from './items/item-history/item-history.component';
import { BsDatepickerModule, ModalModule } from 'ngx-bootstrap';
import { TimepickerModule } from 'ngx-bootstrap/timepicker';
import { TooltipModule } from 'ngx-bootstrap/tooltip';
import { AlertModule } from 'ngx-bootstrap/alert';

import { HttpClientModule } from '@angular/common/http';
import { KnxComponent } from './device-manager/communication-components/knx/knx.component';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { ViewDataComponent } from './items/item-history/view-data/view-data.component';
import { WifiComponent } from './wifi/wifi.component';
import { NgProgressModule } from 'ngx-progressbar';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    InternetComponent,
    DeviceManagerComponent,
    SerialComComponent,
    TcpComComponent,
    RegisterMapComponent,
    DeviceAddSettingComponent,
    ItemsComponent,
    ItemsTableComponent,
    ItemHistoryComponent,
    KnxComponent,
    ViewDataComponent,
    WifiComponent
  ],
  imports: [
    BrowserModule,
    LocalStorageModule,
    routes,
    HttpModule,
    FormsModule,
    BsDatepickerModule.forRoot(),
    ModalModule.forRoot(),
    TimepickerModule.forRoot(),
    TooltipModule.forRoot(),
    AlertModule.forRoot(),
    HttpClientModule,
    AngularFontAwesomeModule,
    NgProgressModule
  ],
  providers: [
    HttpgetService,
    HttppostService,
    DataRouteService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule {
  constructor() {
    if (localStorage.getItem('IP') === null) {
      localStorage.setItem('IP', window.location.hostname);
    }
  }


 }
