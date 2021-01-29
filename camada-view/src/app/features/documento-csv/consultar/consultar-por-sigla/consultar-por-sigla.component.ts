import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Util } from 'src/app/util/util';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-consultar-por-sigla',
  templateUrl: './consultar-por-sigla.component.html',
  styleUrls: ['./consultar-por-sigla.component.css']
})
export class ConsultarPorSiglaComponent implements OnInit {

  get Util() { return Util }
  
  public baseUrl = environment.api_base_url
  public sigla: any;
  public listaDados: any = [];
  public listaSigla = Util.regiaos;
  
  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
  }

  consultarDadosSigla(){
    this.listaDados = [];
    this.http.get(`${this.baseUrl}/historico/regioes/${this.sigla}`).subscribe((retorno: any) => {
      this.listaDados = retorno.content;
    })
  }

}
