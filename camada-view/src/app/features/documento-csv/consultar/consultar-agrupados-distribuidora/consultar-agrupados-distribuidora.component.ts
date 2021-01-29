import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-consultar-agrupados-distribuidora',
  templateUrl: './consultar-agrupados-distribuidora.component.html',
  styleUrls: ['./consultar-agrupados-distribuidora.component.css']
})
export class ConsultarAgrupadosDistribuidoraComponent implements OnInit {

  public baseUrl = environment.api_base_url
  public listaDadosRetorno: any = [];
  public listaOpt = [{ id: 1, descricao: "Bandeira" }, { id: 2, descricao: "Data" }];
  public optEscolha: any;
  public listaFiltrada: any = [];
  public itemChave: any;

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
  }

  consultarAgrupado() {
    if (this.optEscolha == 1) {
      this.consultaAgrupadaBandeira();
    } else {
      this.consultaAgrupadaData();
    }
  }

  consultaAgrupadaBandeira() {
    this.http.get(`${this.baseUrl}/historico/bandeiras`).subscribe((resultado: any) => {
      this.listaDadosRetorno = resultado
    }, (erro: any) => {
      alert("Sem Registro");
    })
  }

  consultaAgrupadaData() {
    this.http.get(`${this.baseUrl}/historico/data-da-coleta`).subscribe((resultado: any) => {
      this.listaDadosRetorno = resultado;
    }, (erro: any) => {
      alert("Sem Registro");
    })
  }

  exibirLista() {
    this.listaFiltrada = [];
    for (var i = 0; i < this.listaDadosRetorno.length; i++) {
      if(this.listaDadosRetorno[i].chave == this.itemChave){
        return this.listaFiltrada = this.listaDadosRetorno[i].historico;
      }
    }
  }
}
