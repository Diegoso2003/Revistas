import { TestBed } from '@angular/core/testing';
import { CanActivateChildFn } from '@angular/router';

import { editorGuard } from './editor.guard';

describe('editorGuard', () => {
  const executeGuard: CanActivateChildFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => editorGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
