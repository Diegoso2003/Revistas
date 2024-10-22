import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormCarteraComponent } from './form-cartera.component';

describe('FormCarteraComponent', () => {
  let component: FormCarteraComponent;
  let fixture: ComponentFixture<FormCarteraComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormCarteraComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormCarteraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
