import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnuncioVistaComponent } from './anuncio-vista.component';

describe('AnuncioVistaComponent', () => {
  let component: AnuncioVistaComponent;
  let fixture: ComponentFixture<AnuncioVistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnuncioVistaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnuncioVistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
