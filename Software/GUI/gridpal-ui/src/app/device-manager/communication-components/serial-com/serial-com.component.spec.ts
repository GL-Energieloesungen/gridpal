import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SerialComComponent } from './serial-com.component';

describe('SerialComComponent', () => {
  let component: SerialComComponent;
  let fixture: ComponentFixture<SerialComComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SerialComComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SerialComComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
