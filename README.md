# Desafio-INDRA

### Requisitos de sistema

    *Node v10.3 em diante
    *Npm v6.4.1 em diante

### Instruções
 - Após o projeto ser clonado deverá ser aberto em uma ide de sua preferência, recomendo o IntelliJ. 
     
   ```sh 
   https://github.com/andre-farias-ti/selecao-java.git
   ```
 
   
### Rodando a aplicação

  - Na parte de back-end executar o comando 'RUN' do Spring Boot ;
  - Abra um terminal e acesse a pasta 'camada-view' e rode os seguintes comandos:
 
    ```
    npm install
    ng serve
 
### Acessando a aplicação

  - No seu navegador acesse http://localhost:4200/;
  - o sistema possui por padrão dois usuário;
  - O sistema possui autenticação e autorização, sendo assim só quem pode incluir o arquivo 2019-1_CA.csv é o administrador.
 
    ```
    Administrador
    usuario: andre
    senha: 12345
    
    Analista
    usuario: fran
    senha : 12345
 
### Sobre a aplicação

   - A API desenvolvida permite a utilização de CRUD para usuários e CRUD para classe de histórico de preços de combustível além de outros recursos customizados para a mesma. A classe de histórico foi criada baseada nos dados do arquivo csv que pode ser baixado através do link -> http://www.anp.gov.br/images/dadosabertos/precos/2018-1_CA.csv

   - A API foi desenvolvida utilizando as tecnologias:
   H2 Banco de dados.
   Linguagem JAVA com framework Spring.
   Linguagem JAVA com framework Spring.

   - A aplicação conta também com uma documentação interativa através do swagger.

## Banco de dados

   - A aplicação conta com o uso do bando de dados H2 direto em memória, o qual é executado automaticamente junto com a aplicação.

   - Para acessar seu console, vá até o seguinte caminho e clique em run:
   ```sh
   http://localhost:8080/api/v1/h2
   ```
   - obs: Lembre-se, como o banco está sendo executado em memória, sempre que a aplicação parar, os dados serão perdidos.

## Requisições na API

   - Para realização das requisições, foi adicionado ao projeto um arquivo Requests-API.rest com as requisições no formato http.

   - Para facilitar as chamadas é recomendado o uso da extensão Rest Client no Visual Studio Code.

   - Caso prefira, é possivel realizá-las diretamente através do swagger pelo caminho (http://localhost:8080/api/v1/swagger-ui.html)

## Contato

 - André Luis Farias
 - andrefariasti@hotmail.com
 - [Gitlab](https://gitlab.com/andre-farias-ti)  
  