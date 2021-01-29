import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './template/login/login.component';
import { VerificarPermissaoLogadoGuard, VerificarUsuarioLogadoGuard } from 'src/app/guards/verificar-usuario-logado.guard'
import { CadastrarUsuarioComponent } from './features/usuario/cadastrar-usuario/cadastrar-usuario.component';
import { IncluirArquivoComponent } from './features/documento-csv/incluir-arquivo/incluir-arquivo.component';
import { ConsultarUsuarioComponent } from './features/usuario/consultar-usuario/consultar-usuario.component';
import { ConsultarComponent } from './features/documento-csv/consultar/consultar.component';
import { UsuarioEditarComponent } from './features/usuario/usuario-editar/usuario-editar.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
    pathMatch: 'full'
  },
  {
    path: '',
    component: LoginComponent,
    pathMatch: 'full'
  },
  {
    path: 'usuario/cadastrar',
    component: CadastrarUsuarioComponent,
    pathMatch: 'full',
    canActivate: [VerificarUsuarioLogadoGuard, VerificarPermissaoLogadoGuard],
  },
  {
    path: 'usuario/consultar',
    component: ConsultarUsuarioComponent,
    pathMatch: 'full',
    canActivate: [VerificarUsuarioLogadoGuard],
  },
  {
    path: 'arquivo/incluir',
    component: IncluirArquivoComponent,
    pathMatch: 'full',
    canActivate: [VerificarUsuarioLogadoGuard, VerificarPermissaoLogadoGuard],
    data: { perfil: '1' }
  },
  {
    path: 'consultar/historicos',
    component: ConsultarComponent,
    pathMatch: 'full',
    canActivate: [VerificarUsuarioLogadoGuard, VerificarPermissaoLogadoGuard],
  },
  {
    path: 'usuario/editar/:id',
    component: UsuarioEditarComponent,
    pathMatch: 'full',
    canActivate: [VerificarUsuarioLogadoGuard, VerificarPermissaoLogadoGuard],
  },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
