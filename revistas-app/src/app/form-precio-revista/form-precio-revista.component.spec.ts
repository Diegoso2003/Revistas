import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormPrecioRevistaComponent } from './form-precio-revista.component';

describe('FormPrecioRevistaComponent', () => {
  let component: FormPrecioRevistaComponent;
  let fixture: ComponentFixture<FormPrecioRevistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormPrecioRevistaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormPrecioRevistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
