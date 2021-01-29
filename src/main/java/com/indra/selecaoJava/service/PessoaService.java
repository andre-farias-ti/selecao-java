package com.indra.selecaoJava.service;

import com.indra.selecaoJava.model.entidade.Pessoa;

import java.util.List;
import java.util.Optional;

public interface PessoaService {

    Pessoa salvar(Pessoa pessoa) throws Exception;

    Optional<Pessoa> buscarPessoaId(Long id) throws Exception;

    List<Pessoa> buscarPessoasPorNome(String nome) throws Exception;

    Pessoa buscarPessoaPorCPF(String cpf) throws Exception;

}
