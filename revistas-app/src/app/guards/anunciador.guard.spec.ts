import { TestBed } from '@angular/core/testing';
import { CanActivateChildFn } from '@angular/router';

import { anunciadorGuard } from './anunciador.guard';

describe('anunciadorGuard', () => {
  const executeGuard: CanActivateChildFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => anunciadorGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
