import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultarMediaCompraVendaComponent } from './consultar-media-compra-venda.component';

describe('ConsultarMediaCompraVendaComponent', () => {
  let component: ConsultarMediaCompraVendaComponent;
  let fixture: ComponentFixture<ConsultarMediaCompraVendaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultarMediaCompraVendaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultarMediaCompraVendaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
