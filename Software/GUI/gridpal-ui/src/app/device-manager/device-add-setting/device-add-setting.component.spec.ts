import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeviceAddSettingComponent } from './device-add-setting.component';

describe('DeviceAddSettingComponent', () => {
  let component: DeviceAddSettingComponent;
  let fixture: ComponentFixture<DeviceAddSettingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeviceAddSettingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeviceAddSettingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
