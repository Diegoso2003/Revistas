import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RevistaVistaEditorComponent } from './revista-vista-editor.component';

describe('RevistaVistaEditorComponent', () => {
  let component: RevistaVistaEditorComponent;
  let fixture: ComponentFixture<RevistaVistaEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RevistaVistaEditorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RevistaVistaEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
