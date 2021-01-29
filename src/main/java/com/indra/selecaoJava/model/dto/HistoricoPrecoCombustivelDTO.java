package com.indra.selecaoJava.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
public class HistoricoPrecoCombustivelDTO {

    private String regiaoSigla;

    private String estadoSigla;

    private String municipio;

    private String revenda;

    private String cnpj;

    private String produto;

    private LocalDate dataDaColeta;

    private double valorDeCompra;

    private double valorDeVenda;

    private String unidadeDeMedida;

    private String bandeira;
}
