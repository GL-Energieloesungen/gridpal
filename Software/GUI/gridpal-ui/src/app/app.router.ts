// import system modules
import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// import component modules
import { DeviceManagerComponent } from './device-manager/device-manager.component';
import { HomeComponent } from './home/home.component';
import { InternetComponent } from './internet/internet.component';
import { DeviceAddSettingComponent } from './device-manager/device-add-setting/device-add-setting.component';
import { ItemsComponent } from './items/items.component';
import { ItemHistoryComponent } from './items/item-history/item-history.component';
import { WifiComponent } from './wifi/wifi.component';

export const router: Routes = [
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full'
  },
  {
    path: 'devicemanager',
    component: DeviceManagerComponent
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'internet',
    component: InternetComponent
  },
  {
    path: 'devicemanager/device-add-setting',
    component: DeviceAddSettingComponent
  },
  {
    path: 'live-data',
    component: ItemsComponent
  },
  {
    path: 'live-data/live-data-history',
    component: ItemHistoryComponent
  },
  {
    path: 'wifi',
    component: WifiComponent
  },
  {
    path: '**',
    component: HomeComponent
  }
];

export const routes: ModuleWithProviders = RouterModule.forRoot(router);

