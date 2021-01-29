import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule }   from '@angular/forms';
import { ReactiveFormsModule} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http'
import { TextMaskModule } from 'angular2-text-mask';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CadastrarUsuarioComponent } from './features/usuario/cadastrar-usuario/cadastrar-usuario.component';
import { FooterComponent } from './template/footer/footer.component';
import { HeaderComponent } from './template/header/header.component';
import { LoginComponent } from './template/login/login.component';
import { VerificarPermissaoLogadoGuard, VerificarUsuarioLogadoGuard } from './guards/verificar-usuario-logado.guard';
import { IncluirArquivoComponent } from './features/documento-csv/incluir-arquivo/incluir-arquivo.component';
import { ConsultarUsuarioComponent } from './features/usuario/consultar-usuario/consultar-usuario.component';
import { ConsultarMediaMunicipioComponent } from './features/documento-csv/consultar/consultar-media-municipio/consultar-media-municipio.component';
import { ConsultarPorSiglaComponent } from './features/documento-csv/consultar/consultar-por-sigla/consultar-por-sigla.component';
import { ConsultarAgrupadosDistribuidoraComponent } from './features/documento-csv/consultar/consultar-agrupados-distribuidora/consultar-agrupados-distribuidora.component';
import { ConsultarMediaCompraVendaComponent } from './features/documento-csv/consultar/consultar-media-compra-venda/consultar-media-compra-venda.component';
import { ConsultarComponent } from './features/documento-csv/consultar/consultar.component';
import { NgbModule, NgbNav, NgbNavModule } from '@ng-bootstrap/ng-bootstrap';
import { UsuarioEditarComponent } from './features/usuario/usuario-editar/usuario-editar.component';
import { ConsultarEditarHistoricoComponent } from './features/documento-csv/consultar/consultar-editar-historico/consultar-editar-historico.component';


@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    LoginComponent,
    CadastrarUsuarioComponent,
    IncluirArquivoComponent,
    ConsultarUsuarioComponent,
    ConsultarMediaMunicipioComponent,
    ConsultarPorSiglaComponent,
    ConsultarAgrupadosDistribuidoraComponent,
    ConsultarMediaCompraVendaComponent,
    ConsultarComponent,
    UsuarioEditarComponent,
    ConsultarEditarHistoricoComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    CommonModule,
    TextMaskModule,
    ReactiveFormsModule,
    NgbModule
  ],
  providers: [
    VerificarUsuarioLogadoGuard,
    VerificarPermissaoLogadoGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
