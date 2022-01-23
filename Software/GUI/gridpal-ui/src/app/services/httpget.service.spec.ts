import { TestBed, inject } from '@angular/core/testing';
import { HttpgetService } from './httpget.service';

describe('HttpgetService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [HttpgetService]
    });
  });

  it('should be created', inject([HttpgetService], (service: HttpgetService) => {
    expect(service).toBeTruthy();
  }));
});
