import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TcpComComponent } from './tcp-com.component';

describe('TcpComComponent', () => {
  let component: TcpComComponent;
  let fixture: ComponentFixture<TcpComComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TcpComComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TcpComComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
