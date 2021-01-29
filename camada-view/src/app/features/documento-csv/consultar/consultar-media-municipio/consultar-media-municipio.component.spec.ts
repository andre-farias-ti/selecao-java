import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultarMediaMunicipioComponent } from './consultar-media-municipio.component';

describe('ConsultarMediaMunicipioComponent', () => {
  let component: ConsultarMediaMunicipioComponent;
  let fixture: ComponentFixture<ConsultarMediaMunicipioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultarMediaMunicipioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultarMediaMunicipioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
