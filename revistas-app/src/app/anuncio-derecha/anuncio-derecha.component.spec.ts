import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnuncioDerechaComponent } from './anuncio-derecha.component';

describe('AnuncioDerechaComponent', () => {
  let component: AnuncioDerechaComponent;
  let fixture: ComponentFixture<AnuncioDerechaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnuncioDerechaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnuncioDerechaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
