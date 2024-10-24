import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnunciosVigentesVistaComponent } from './anuncios-vigentes-vista.component';

describe('AnunciosVigentesVistaComponent', () => {
  let component: AnunciosVigentesVistaComponent;
  let fixture: ComponentFixture<AnunciosVigentesVistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnunciosVigentesVistaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnunciosVigentesVistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
