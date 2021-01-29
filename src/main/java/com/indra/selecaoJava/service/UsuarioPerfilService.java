package com.indra.selecaoJava.service;

import com.indra.selecaoJava.model.entidade.Perfil;
import com.indra.selecaoJava.model.entidade.Pessoa;
import com.indra.selecaoJava.model.entidade.UsrPerfil;
import com.indra.selecaoJava.model.entidade.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioPerfilService {

    UsrPerfil salvar(Usuario usuario, Perfil perfil) throws Exception;

    UsrPerfil salvar (UsrPerfil usrPerfil) throws Exception;

    UsrPerfil buscar(Usuario usuario) throws Exception;

    void deletar(Usuario usuario) throws Exception;
}
