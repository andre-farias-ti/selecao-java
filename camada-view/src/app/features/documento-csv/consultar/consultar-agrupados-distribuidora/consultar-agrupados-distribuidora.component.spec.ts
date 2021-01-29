import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultarAgrupadosDistribuidoraComponent } from './consultar-agrupados-distribuidora.component';

describe('ConsultarAgrupadosDistribuidoraComponent', () => {
  let component: ConsultarAgrupadosDistribuidoraComponent;
  let fixture: ComponentFixture<ConsultarAgrupadosDistribuidoraComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultarAgrupadosDistribuidoraComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultarAgrupadosDistribuidoraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
