import { TestBed, inject } from '@angular/core/testing';

import { HttppostService } from './httppost.service';

describe('HttppostService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [HttppostService]
    });
  });

  it('should be created', inject([HttppostService], (service: HttppostService) => {
    expect(service).toBeTruthy();
  }));
});
