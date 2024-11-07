import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompraBloqueoFormComponent } from './compra-bloqueo-form.component';

describe('CompraBloqueoFormComponent', () => {
  let component: CompraBloqueoFormComponent;
  let fixture: ComponentFixture<CompraBloqueoFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CompraBloqueoFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CompraBloqueoFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
