import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BloqueoRevistaFormComponent } from './bloqueo-revista-form.component';

describe('BloqueoRevistaFormComponent', () => {
  let component: BloqueoRevistaFormComponent;
  let fixture: ComponentFixture<BloqueoRevistaFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BloqueoRevistaFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BloqueoRevistaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
