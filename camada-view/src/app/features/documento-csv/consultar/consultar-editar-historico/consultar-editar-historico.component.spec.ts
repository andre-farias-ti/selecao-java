import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultarEditarHistoricoComponent } from './consultar-editar-historico.component';

describe('ConsultarEditarHistoricoComponent', () => {
  let component: ConsultarEditarHistoricoComponent;
  let fixture: ComponentFixture<ConsultarEditarHistoricoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultarEditarHistoricoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultarEditarHistoricoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
