package com.indra.selecaoJava.repository;

import com.indra.selecaoJava.model.entidade.HistoricoPrecoCombustivel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HistoricoPrecoCombustivelRepository extends JpaRepository<HistoricoPrecoCombustivel, Long> {


    @Query(value="select avg(valor_de_venda) from historico_preco_combustivel where valor_de_venda <> 0 and municipio ilike ?1", nativeQuery = true)
    Optional<Double> findAvgValorDeVendaByMunicipio(String municipio);

    @Query(value="select avg(valor_de_compra) from historico_preco_combustivel where valor_de_compra <> 0 and municipio ilike ?1", nativeQuery = true)
    Optional<Double> findAvgValorDeCompraByMunicipio(String municipio);

    @Query(value="select avg(valor_de_venda) from historico_preco_combustivel where valor_de_venda <> 0 and bandeira ilike ?1", nativeQuery = true)
    Optional<Double> findAvgValorDeVendaByBandeira(String bandeira);

    @Query(value="select avg(valor_de_compra) from historico_preco_combustivel where valor_de_compra <> 0 and bandeira ilike ?1", nativeQuery = true)
    Optional<Double> findAvgValorDeCompraByBandeira(String bandeira);

    Page<HistoricoPrecoCombustivel> findAllByRegiaoSiglaContainingIgnoreCase(String regiaoSigla, Pageable pageable);

    @Query(value="select distinct bandeira from historico_preco_combustivel", nativeQuery = true)
    List<String> findDistinctBandeira();

    @Query(value="select distinct data_da_coleta from historico_preco_combustivel", nativeQuery = true)
    List<Date> findDistinctDataDaColeta();

    List<HistoricoPrecoCombustivel> findAllByBandeira(String bandeira, Pageable pageable);

    List<HistoricoPrecoCombustivel> findAllByDataDaColeta(LocalDate data, Pageable pageable);

}
