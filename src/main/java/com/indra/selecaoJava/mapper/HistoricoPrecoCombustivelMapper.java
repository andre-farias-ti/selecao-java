package com.indra.selecaoJava.mapper;

import com.indra.selecaoJava.model.entidade.HistoricoPrecoCombustivel;
import com.indra.selecaoJava.model.dto.HistoricoPrecoCombustivelDTO;
import com.indra.selecaoJava.util.Formatter;

public class HistoricoPrecoCombustivelMapper {

    public static HistoricoPrecoCombustivel fromDTO(HistoricoPrecoCombustivelDTO dto) {

        HistoricoPrecoCombustivel historicoPrecoCombustivel = new HistoricoPrecoCombustivel();

        historicoPrecoCombustivel.setRegiaoSigla(dto.getRegiaoSigla());
        historicoPrecoCombustivel.setEstadoSigla(dto.getEstadoSigla());
        historicoPrecoCombustivel.setMunicipio(dto.getMunicipio());
        historicoPrecoCombustivel.setRevenda(dto.getRevenda());
        historicoPrecoCombustivel.setCnpj(dto.getCnpj());
        historicoPrecoCombustivel.setProduto(dto.getProduto());
        historicoPrecoCombustivel.setDataDaColeta(dto.getDataDaColeta());
        historicoPrecoCombustivel.setValorDeCompra(dto.getValorDeCompra());
        historicoPrecoCombustivel.setValorDeVenda(dto.getValorDeVenda());
        historicoPrecoCombustivel.setUnidadeDeMedida(dto.getUnidadeDeMedida());
        historicoPrecoCombustivel.setBandeira(dto.getBandeira());

        return historicoPrecoCombustivel;
    }

    public static HistoricoPrecoCombustivel stringToHistorico (String linha) {
        String [] coluna = linha.split(",");
        HistoricoPrecoCombustivel historicoPrecoCombustivel = new HistoricoPrecoCombustivel();

        historicoPrecoCombustivel.setRegiaoSigla(coluna[0]);
        historicoPrecoCombustivel.setEstadoSigla(coluna[1]);
        historicoPrecoCombustivel.setMunicipio(coluna[2]);
        historicoPrecoCombustivel.setRevenda(coluna[3]);
        historicoPrecoCombustivel.setCnpj(coluna[4]);
        historicoPrecoCombustivel.setProduto(coluna[5]);
        historicoPrecoCombustivel.setDataDaColeta(Formatter.toDate(coluna[6]));

        if(coluna[7].equals("")) {
            historicoPrecoCombustivel.setValorDeVenda(0);
        }
        else{
            historicoPrecoCombustivel.setValorDeVenda(Double.parseDouble(coluna[7]));
        }

        if(coluna[8].equals("")){
            historicoPrecoCombustivel.setValorDeCompra(0);
        }
        else {
            historicoPrecoCombustivel.setValorDeCompra(Double.parseDouble(coluna[8]));
        }
        historicoPrecoCombustivel.setUnidadeDeMedida(coluna[9]);
        historicoPrecoCombustivel.setBandeira(coluna[10]);

        return historicoPrecoCombustivel;
    }
}
