package com.indra.selecaoJava.model.entidade;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name="HistoricoPrecoCombustivel")
public class HistoricoPrecoCombustivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="regiaoSigla")
    private String regiaoSigla;

    @Column(name="estadoSigla")
    private String estadoSigla;

    @Column(name="municipio")
    private String municipio;

    @Column(name="revenda")
    private String revenda;

    @Column(name="cnpj")
    private String cnpj;

    @Column(name="produto")
    private String produto;

    @Column(name="dataDaColeta")
    private LocalDate dataDaColeta;

    @Column(name="valorDeCompra")
    private double valorDeCompra;

    @Column(name="valorDeVenda")
    private double valorDeVenda;

    @Column(name="unidadeDeMedida")
    private String unidadeDeMedida;

    @Column(name="bandeira")
    private String bandeira;
}
