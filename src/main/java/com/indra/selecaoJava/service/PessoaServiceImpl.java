package com.indra.selecaoJava.service;

import com.indra.selecaoJava.model.entidade.Endereco;
import com.indra.selecaoJava.model.entidade.Pessoa;
import com.indra.selecaoJava.repository.EnderecoRepository;
import com.indra.selecaoJava.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService{

    @Autowired
    private PessoaRepository repository;

    @Autowired
    EnderecoService enderecoService;

    @Override
    public Pessoa salvar(Pessoa pessoa) throws Exception {
        Endereco endereco = enderecoService.salvar(pessoa.getEndereco());
        pessoa.setCpf(linparString(pessoa.getCpf()));
        pessoa.setTelefone(linparString(pessoa.getTelefone()));
        pessoa.setEndereco(endereco);
        return repository.saveAndFlush(pessoa);
    }

    @Override
    public Optional<Pessoa> buscarPessoaId(Long id) throws Exception {
        return repository.findById(id);
    }

    @Override
    public List<Pessoa> buscarPessoasPorNome(String nome) throws Exception {
        return repository.findByNomeContaining(nome);
    }

    @Override
    public Pessoa buscarPessoaPorCPF(String cpf) throws Exception {
        return repository.findByCpf(cpf);
    }

    private String linparString(String string) {
        string = string.replace("-", "");
        string = string.replace(".", "");
        string = string.replace("(", "");
        string = string.replace(")", "");
        string = string.replace(" ", "");

        return string;
    }
}
