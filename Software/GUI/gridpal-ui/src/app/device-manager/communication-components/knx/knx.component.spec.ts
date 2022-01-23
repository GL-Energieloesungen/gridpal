import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KnxComponent } from './knx.component';

describe('KnxComponent', () => {
  let component: KnxComponent;
  let fixture: ComponentFixture<KnxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KnxComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KnxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
