import { AfterViewChecked, Component, DoCheck, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  public usuario = sessionStorage.getItem('login');

  constructor(private router: Router) { }

  verificarUsuarioLogado() {
    return sessionStorage.getItem('login')
  }

  mudarRota(rota: string) {
    this.router.navigate([rota])
  }

  verificarPerfil(nrPerfil: any) {
    return sessionStorage.getItem('perfil') == nrPerfil
  }

  sair(){
    sessionStorage.clear();
    this.mudarRota("/login");
  }

}
