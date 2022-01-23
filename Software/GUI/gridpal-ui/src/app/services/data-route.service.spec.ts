import { TestBed, inject } from '@angular/core/testing';

import { DataRouteService } from './data-route.service';

describe('DataRouteService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DataRouteService]
    });
  });

  it('should be created', inject([DataRouteService], (service: DataRouteService) => {
    expect(service).toBeTruthy();
  }));
});
