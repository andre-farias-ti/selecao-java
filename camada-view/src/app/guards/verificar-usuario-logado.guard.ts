import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';

@Injectable()
export class VerificarUsuarioLogadoGuard implements CanActivate {

  constructor(private router: Router) { }

  canActivate() {
    const logado = sessionStorage.getItem('login')

    if (logado) {
      return true
    } else {
      this.router.navigate([`/login`])
      return false;
    }
  }
}

@Injectable()
export class VerificarPermissaoLogadoGuard implements CanActivate {

  constructor() { }

  canActivate(activatedRouteSnapshot: ActivatedRouteSnapshot) {
    const perfil = activatedRouteSnapshot.data.perfil

    if (sessionStorage.getItem('perfil') == perfil || perfil == undefined) {
      return true
    } else {
      alert("Sem permissão");
      return false;
    }
  }
}