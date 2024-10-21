import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavLectorComponent } from './nav-lector.component';

describe('NavLectorComponent', () => {
  let component: NavLectorComponent;
  let fixture: ComponentFixture<NavLectorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NavLectorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NavLectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
