import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RevistaSubidasVistaComponent } from './revista-subidas-vista.component';

describe('RevistaSubidasVistaComponent', () => {
  let component: RevistaSubidasVistaComponent;
  let fixture: ComponentFixture<RevistaSubidasVistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RevistaSubidasVistaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RevistaSubidasVistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
