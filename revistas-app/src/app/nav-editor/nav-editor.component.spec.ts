import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavEditorComponent } from './nav-editor.component';

describe('NavEditorComponent', () => {
  let component: NavEditorComponent;
  let fixture: ComponentFixture<NavEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NavEditorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NavEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
