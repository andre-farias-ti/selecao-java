import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-consultar-media-compra-venda',
  templateUrl: './consultar-media-compra-venda.component.html',
  styleUrls: ['./consultar-media-compra-venda.component.css']
})
export class ConsultarMediaCompraVendaComponent implements OnInit {


  public baseUrl = environment.api_base_url
  public nomeConsulta: any;
  public mediaValorCompra: any;
  public mediaValorVenda: any;
  public listaOpt = [{id:1,descricao:"Municipio"}, {id:2,descricao:"Bandeira"}];
  public optEscolha : any;

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
  }

  consultarMedia(){
    if(this.optEscolha == 1){
      this.consultarMediaMunicipio();
    }else{
      this.consultarMediaBandeira();
    }
  }

  consultarMediaMunicipio(){
    this.mediaValorCompra = undefined;
    this.mediaValorVenda = undefined;
    this.http.get(`${this.baseUrl}/historico/media-valores/${this.nomeConsulta}/municipios`).subscribe((resultado: any) => {
      this.mediaValorCompra = resultado.valorDeCompra;
      this.mediaValorVenda = resultado.valorDeVenda;
    })
  }

  consultarMediaBandeira(){
    this.mediaValorCompra = undefined;
    this.mediaValorVenda = undefined;
    this.http.get(`${this.baseUrl}/historico/media-valores/${this.nomeConsulta}/bandeiras`).subscribe((resultado: any) => {
      this.mediaValorCompra = resultado.valorDeCompra;
      this.mediaValorVenda = resultado.valorDeVenda;
    }, (erro : any) =>{
      alert("Sem Registro");
    })
  }

}
