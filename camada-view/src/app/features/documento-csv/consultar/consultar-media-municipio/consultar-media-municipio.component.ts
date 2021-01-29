import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-consultar-media-municipio',
  templateUrl: './consultar-media-municipio.component.html',
  styleUrls: ['./consultar-media-municipio.component.css']
})
export class ConsultarMediaMunicipioComponent implements OnInit {

  public baseUrl = environment.api_base_url
  public nomeMunicipio: any;
  public media: any;
  
  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
  }

  consultarMunicipio(){
    this.media = undefined;
    this.http.get(`${this.baseUrl}/historico/media-valor-venda/${this.nomeMunicipio}/municipios`).subscribe((resultado: any) => {
      this.media = resultado;
    })
  }

}
