import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Mask } from 'src/app/mask/mask.util';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-usuario-editar',
  templateUrl: './usuario-editar.component.html',
  styleUrls: ['./usuario-editar.component.css']
})
export class UsuarioEditarComponent implements OnInit {

  public baseUrl = environment.api_base_url
  public url = `${this.baseUrl}/usuario`;

  get maskUtil() { return Mask }


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
  public idPerfil: any;
  public retorno: any;

  constructor(private http: HttpClient, private activatedRoute: ActivatedRoute, private route: Router) { }

  ngOnInit(): void {
    this.http.get(`${this.url}/${this.activatedRoute.snapshot.params['id']}`).subscribe((retorno: any) => {
      this.retorno = retorno
      this.nome = retorno.pessoa.nome
      this.cpf = retorno.pessoa.cpf
      this.dtNascimento = this.formataStringData(new Date(retorno.pessoa.dtNascimento))
      this.telefone = retorno.pessoa.telefone
      this.email = retorno.pessoa.email
      this.estado = retorno.pessoa.endereco.estado
      this.cidade = retorno.pessoa.endereco.cidade
      this.bairro = retorno.pessoa.endereco.bairro
      this.rua = retorno.pessoa.endereco.rua
      this.numero = retorno.pessoa.endereco.numero
      this.complemento = retorno.pessoa.endereco.complemento
      this.login = retorno.login
      this.senha = retorno.senha
      this.idPerfil = retorno.idPerfil
      this.senhaNovamente = retorno.senha
    })
  }

  editarUsuario(){
    const objEnvio = {
      pessoa: {
        id:this.retorno.pessoa.id,
        nome: this.nome,
        cpf: this.cpf,
        dtNascimento: new Date(this.formataDataString(this.dtNascimento)),
        telefone: this.telefone,
        email: this.email,
        endereco: {
          id: this.retorno.pessoa.endereco.id,
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
      idPerfil: this.idPerfil
    }

    this.http.post(`${this.url}/editar`, objEnvio).subscribe(retorno => {
        alert("Usuario editado com sucesso!")
        this.route.navigate(['/usuario/consultar'])
    })
  }

  formataStringData(data : Date) {
      var dia  = data.getDate().toString().padStart(2, '0')
      var mes  = (data.getMonth()+1).toString().padStart(2, '0') 
      var ano  = data.getFullYear();
    return dia+"/"+mes+"/"+ano;
  }
  
  formataDataString(data :String){
    var dia  = data.substring(0,2);
    var mes  = data.substring(3,5);
    var ano  = data.substring(6,10);
    return ano+"-"+mes+"-"+dia+" 00:00:00 GMT-0200";
  }
}
