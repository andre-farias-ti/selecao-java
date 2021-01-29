import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Mask } from 'src/app/mask/mask.util';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-consultar-editar-historico',
  templateUrl: './consultar-editar-historico.component.html',
  styleUrls: ['./consultar-editar-historico.component.css']
})
export class ConsultarEditarHistoricoComponent implements OnInit {


  get maskUtil() { return Mask }

  public baseUrl = environment.api_base_url
  public id: any
  public regiaoSigla: any
  public municipio: any
  public estadoSigla: any
  public revenda: any
  public unidadeDeMedida: any
  public produto: any
  public valorDeCompra: any
  public valorDeVenda: any
  public cnpj: any
  public dataDaColeta: any
  public bandeira: any
  public objetoResquest: any

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
  }

  consultar() {
    this.objetoResquest = [];
    this.http.get(`${this.baseUrl}/historico/${this.id}`).subscribe((resultado: any) => {
        this.regiaoSigla = resultado.regiaoSigla
        this.municipio = resultado.municipio
        this.estadoSigla = resultado.estadoSigla
        this.revenda = resultado.revenda
        this.unidadeDeMedida = resultado.unidadeDeMedida
        this.produto = resultado.produto
        this.valorDeCompra = resultado.valorDeCompra
        this.valorDeVenda = resultado.valorDeVenda
        this.cnpj =resultado.cnpj
        this.dataDaColeta = this.formataDataResponse(resultado.dataDaColeta)
        this.bandeira = resultado.bandeira
    })
  }

  salvar() {
    this.objetoResquest = {
      regiaoSigla: this.regiaoSigla,
      municipio: this.municipio,
      estadoSigla: this.estadoSigla,
      revenda: this.revenda,
      unidadeDeMedida: this.unidadeDeMedida,
      produto: this.produto,
      valorDeCompra: this.valorDeCompra,
      valorDeVenda: this.valorDeVenda,
      cnpj: this.cnpj,
      dataDaColeta:  this.formataDataRquest(this.dataDaColeta),
      bandeira: this.bandeira
    }

    this.http.put(`${this.baseUrl}/historico/${this.id}`, this.objetoResquest).subscribe((resultado: any) => {
      this.objetoResquest = [];
      this.id = undefined;
      alert("Historico Salvo com Sucesso")
    }, (erro: any) => {
      alert("Sem Registro")
    })
  }

  deletar() {
    this.http.delete(`${this.baseUrl}/historico/${this.id}`).subscribe((resultado: any) => {
      this.objetoResquest = [];
      this.id = undefined;
      alert("Historico Delatado com Sucesso")
    }, (erro: any) => {
      alert("Sem Registro")
    })
  }

  formataDataRquest(data : String) {
    var dia  = data.substring(0,2);
    var mes  = data.substring(3,5);
    var ano  = data.substring(6,10);
    return ano+"-"+mes+"-"+dia;
  }

  formataDataResponse(data :String){
    var dia  = data.substring(8,10)
    var mes  = data.substring(5,7)
    var ano  = data.substring(0,4)
    return dia+"/"+mes+"/"+ano;
  }
}
