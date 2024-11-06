import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnuncioIzquierdaComponent } from './anuncio-izquierda.component';

describe('AnuncioIzquierdaComponent', () => {
  let component: AnuncioIzquierdaComponent;
  let fixture: ComponentFixture<AnuncioIzquierdaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnuncioIzquierdaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnuncioIzquierdaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
