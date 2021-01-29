package com.indra.selecaoJava.util;

import org.springframework.http.HttpHeaders;

public class HeaderUtil {

  public static HttpHeaders criarAlerta(String tipo, String mensagem) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("X-alert-msg", mensagem);
    headers.add("X-alert-tipo", tipo);
    return headers;
  }

  public static HttpHeaders criarAlertaSucesso(String mensagem) {
    return criarAlerta("success", mensagem);
  }

  public static HttpHeaders criarAlertaInformacao(String mensagem) {
    return criarAlerta("info", mensagem);
  }
}
