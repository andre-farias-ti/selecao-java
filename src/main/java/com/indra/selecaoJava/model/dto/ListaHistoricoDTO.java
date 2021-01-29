package com.indra.selecaoJava.model.dto;

import com.indra.selecaoJava.model.entidade.HistoricoPrecoCombustivel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ListaHistoricoDTO {

    String chave;
    List<HistoricoPrecoCombustivel> historico;

    @Builder
    public ListaHistoricoDTO(String chave, List<HistoricoPrecoCombustivel> historico) {
        this.chave = chave;
        this.historico = historico;
    }
}
