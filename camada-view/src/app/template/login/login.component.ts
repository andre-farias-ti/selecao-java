import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public static usuarioLogado: any

  public baseUrl = environment.api_base_url
  private url = `${this.baseUrl}/login`;

  public login: any;
  public senha: any;

  constructor(
    private http: HttpClient,
    private router: Router
  ) {
    sessionStorage.clear()
  }

  ngOnInit() { }

  logarUsuario() {

    const objEnvio = {
      login: this.login,
      senha: this.senha
    }

    // const headers = new HttpHeaders({ Authorizantion: 'basic' + btoa(this.login + ":" + this.senha) });

    this.http.post(this.url, objEnvio).subscribe((retorno: any) => {
      if (retorno) {
        sessionStorage.setItem('login', retorno.login);
        if (LoginComponent.verificarPerfil(2, retorno.perfil)) {
          sessionStorage.setItem('perfil', '2');
          this.router.navigate(['/usuario/consultar']);
        } else if (LoginComponent.verificarPerfil(1, retorno.perfil)) {
          sessionStorage.setItem('perfil', '1');
          this.router.navigate(['arquivo/incluir']);
        }
      } else {
        alert("Authentication failed.")
      }
    })
  }

  static verificarPerfil(nrPerfil: any, usuario: any) {
    const perfil = usuario.filter((perfil: any) => {
      return perfil.id === nrPerfil
    })

    return perfil.length > 0 ? true : false;
  }

}
