import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RevistaPdfFormComponent } from './revista-pdf-form.component';

describe('RevistaPdfFormComponent', () => {
  let component: RevistaPdfFormComponent;
  let fixture: ComponentFixture<RevistaPdfFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RevistaPdfFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RevistaPdfFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
