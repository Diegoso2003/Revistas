import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreciosUpdateComponent } from './precios-update.component';

describe('PreciosUpdateComponent', () => {
  let component: PreciosUpdateComponent;
  let fixture: ComponentFixture<PreciosUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PreciosUpdateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PreciosUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
