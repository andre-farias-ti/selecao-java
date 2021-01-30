import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Mask } from 'src/app/mask/mask.util'
import { Util } from '../../../util/util';

@Component({
  selector: 'app-cadastrar-usuario',
  templateUrl: './cadastrar-usuario.component.html',
  styleUrls: ['./cadastrar-usuario.component.css']
})
export class CadastrarUsuarioComponent implements OnInit {
  
  get Util() { return Util }

  public perfis = this.Util.perfis;
  public idPerfilSelect: any
  public nome: any;
  public cpf: any;
  public dtNascimento: any;
  public telefone: any;
  public email: any;
  public estado: any;
  public cidade: any;
  public bairro: any;
  public rua: any;
  public numero: any;
  public complemento: any;
  public login: any;
  public senha: any;
  public senhaNovamente: any;

  get maskUtil() { return Mask }

  public baseUrl = environment.api_base_url
  public url = `${this.baseUrl}/usuario/salvar`;

  constructor(
    private http: HttpClient,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  cadastrarUsuario() {
    const objEnvio = {
      pessoa: {
        nome: this.nome,
        cpf: this.cpf,
        dtNascimento: new Date(this.dtNascimento),
        telefone: this.telefone,
        email: this.email,
        endereco: {
          estado: this.estado,
          cidade: this.cidade,
          bairro: this.bairro,
          rua: this.rua,
          numero: this.numero,
          complemento: this.complemento
        }
      },
      login: this.login,
      senha: this.senha,
      idPerfil: this.idPerfilSelect
    }

    this.http.post(this.url, objEnvio).subscribe(retorno => {
        alert("Usuario Cadastrado com sucesso!")
        this.router.navigate(['/usuario/consultar'])
    })
  }

}
