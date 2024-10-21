import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavAnunciadorComponent } from './nav-anunciador.component';

describe('NavAnunciadorComponent', () => {
  let component: NavAnunciadorComponent;
  let fixture: ComponentFixture<NavAnunciadorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NavAnunciadorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NavAnunciadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
