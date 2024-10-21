import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormCompraAnuncioComponent } from './form-compra-anuncio.component';

describe('FormCompraAnuncioComponent', () => {
  let component: FormCompraAnuncioComponent;
  let fixture: ComponentFixture<FormCompraAnuncioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormCompraAnuncioComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormCompraAnuncioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
