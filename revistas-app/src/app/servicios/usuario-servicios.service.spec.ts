import { TestBed } from '@angular/core/testing';

import { UsuarioServiciosService } from './usuario-servicios.service';

describe('UsuarioServiciosService', () => {
  let service: UsuarioServiciosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UsuarioServiciosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
