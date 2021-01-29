package com.indra.selecaoJava.repository;

import com.indra.selecaoJava.model.entidade.UsrPerfil;
import com.indra.selecaoJava.model.entidade.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsrPerfilRepository extends JpaRepository<UsrPerfil, Long> {

    UsrPerfil findByUsuario(Usuario usuario);
}

