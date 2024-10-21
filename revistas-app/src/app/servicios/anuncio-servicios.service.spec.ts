import { TestBed } from '@angular/core/testing';

import { AnuncioServiciosService } from './anuncio-servicios.service';

describe('AnuncioServiciosService', () => {
  let service: AnuncioServiciosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AnuncioServiciosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
