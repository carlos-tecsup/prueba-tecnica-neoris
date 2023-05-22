import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CambioDivisaComponent } from './cambio-divisa.component';

describe('CambioDivisaComponent', () => {
  let component: CambioDivisaComponent;
  let fixture: ComponentFixture<CambioDivisaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CambioDivisaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CambioDivisaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
