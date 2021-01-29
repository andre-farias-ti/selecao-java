package com.indra.selecaoJava.repository;

import com.indra.selecaoJava.model.entidade.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    List<Pessoa> findByNomeContaining(String nome);

    Pessoa findByCpf(String cpf);
}

