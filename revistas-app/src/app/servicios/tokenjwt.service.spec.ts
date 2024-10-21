import { TestBed } from '@angular/core/testing';

import { TokenjwtService } from './tokenjwt.service';

describe('TokenjwtService', () => {
  let service: TokenjwtService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TokenjwtService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
