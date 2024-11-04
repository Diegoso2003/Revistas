import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TextoUpdateComponent } from './texto-update.component';

describe('TextoUpdateComponent', () => {
  let component: TextoUpdateComponent;
  let fixture: ComponentFixture<TextoUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TextoUpdateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TextoUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
