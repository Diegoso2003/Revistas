import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnuncioUpdateComponent } from './anuncio-update.component';

describe('AnuncioUpdateComponent', () => {
  let component: AnuncioUpdateComponent;
  let fixture: ComponentFixture<AnuncioUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnuncioUpdateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnuncioUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
