import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetallesObjetoComponent } from './detalles-objeto.component';

describe('DetallesObjetoComponent', () => {
  let component: DetallesObjetoComponent;
  let fixture: ComponentFixture<DetallesObjetoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetallesObjetoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetallesObjetoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
