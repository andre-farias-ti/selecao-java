package com.indra.selecaoJava.service;

import com.indra.selecaoJava.model.entidade.Endereco;
import com.indra.selecaoJava.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoServiceImpl implements EnderecoService{

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    public Endereco salvar(Endereco endereco) throws Exception {
        return enderecoRepository.saveAndFlush(endereco);
    }
}
