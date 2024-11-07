import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RevistaVistaComponent } from './revista-vista.component';

describe('RevistaVistaComponent', () => {
  let component: RevistaVistaComponent;
  let fixture: ComponentFixture<RevistaVistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RevistaVistaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RevistaVistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
