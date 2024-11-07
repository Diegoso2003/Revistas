import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormPrecioBloqueoRevistaComponent } from './form-precio-bloqueo-revista.component';

describe('FormPrecioBloqueoRevistaComponent', () => {
  let component: FormPrecioBloqueoRevistaComponent;
  let fixture: ComponentFixture<FormPrecioBloqueoRevistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormPrecioBloqueoRevistaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormPrecioBloqueoRevistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
