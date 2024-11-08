import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormReportePagosComponent } from './form-reporte-pagos.component';

describe('FormReportePagosComponent', () => {
  let component: FormReportePagosComponent;
  let fixture: ComponentFixture<FormReportePagosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormReportePagosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormReportePagosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
