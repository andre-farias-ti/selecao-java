import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultarPorSiglaComponent } from './consultar-por-sigla.component';

describe('ConsultarPorSiglaComponent', () => {
  let component: ConsultarPorSiglaComponent;
  let fixture: ComponentFixture<ConsultarPorSiglaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultarPorSiglaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultarPorSiglaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
