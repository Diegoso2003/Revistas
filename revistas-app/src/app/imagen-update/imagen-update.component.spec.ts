import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImagenUpdateComponent } from './imagen-update.component';

describe('ImagenUpdateComponent', () => {
  let component: ImagenUpdateComponent;
  let fixture: ComponentFixture<ImagenUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ImagenUpdateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ImagenUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
