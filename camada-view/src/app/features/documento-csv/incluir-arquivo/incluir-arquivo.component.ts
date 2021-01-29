import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Mask } from 'src/app/mask/mask.util'

@Component({
  selector: 'app-incluir-arquivo',
  templateUrl: './incluir-arquivo.component.html',
  styleUrls: ['./incluir-arquivo.component.css']
})
export class IncluirArquivoComponent implements OnInit {

  public baseUrl = environment.api_base_url
  public url = `${this.baseUrl}/historico/csv`;
  public historicoCSV: any;

  get maskUtil() { return Mask }

  constructor(private http: HttpClient, private activatedRoute: ActivatedRoute, private route: Router) { }

  ngOnInit() {
  }

  selectFiles(event: any) {
    const formData = new FormData();
    formData.append('historicoCSV', event.target.files[0]);
    this.historicoCSV = formData;
  }

  incluirArquivo() {
    this.http.post(this.url, this.historicoCSV).subscribe(
      retorno => { 
        alert("Arquivo incluido sucesso!") 
      },
      erro =>{
        alert("Arquivo não foi incluido!")
      })
  }

  public async getBase64(file: File) {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => resolve(reader.result);
      reader.onerror = error => reject(error);
    });
  }

}
