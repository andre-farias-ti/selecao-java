import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Mask } from 'src/app/mask/mask.util'
import { Util } from '../../../util/util';

@Component({
  selector: 'app-consultar-usuario',
  templateUrl: './consultar-usuario.component.html',
  styleUrls: ['./consultar-usuario.component.css']
})
export class ConsultarUsuarioComponent implements OnInit {

  public listaUsuarios: any = [];

  get Util() { return Util }

  public baseUrl = environment.api_base_url
  public url = `${this.baseUrl}/usuario/buscar`;

  get maskUtil() { return Mask }

  public perfis = this.Util.perfis;
  public idDescricao: any
  public nome: any;
  public cpf: any;

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit() { }

  consultarUsuarios() {
    const params = new URLSearchParams()
    this.nome && params.append('nome', this.nome)
    this.cpf && params.append('cpf', this.cpf)
    this.http.get(`${this.baseUrl}/usuario/buscar-por-filtro?${params.toString()}`).subscribe((usuario:any) => {
      this.listaUsuarios = usuario;
      if(usuario.length <= 0){
        alert("Sem Registro");
      }
    },
    erro =>{
      alert("Não foi possivel consultar");
    })
  }

  editarUsuario(id: any) {
    this.router.navigate([`/usuario/editar/${id}`])
  }

   perfilDesc(idPerfil: any) {
      const descricao = this.perfis.filter((filter:any)=>{return filter.id == idPerfil})
      return descricao[0].descricao;
   }

  deletar(id:any){
    this.http.get(`${this.baseUrl}/usuario/deletar/${id}`).subscribe(retorno => {
      this.listaUsuarios = [];
      alert("Usuario deletado com sucesso!")
    })
  }

}
