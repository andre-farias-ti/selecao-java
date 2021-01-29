package com.indra.selecaoJava.service;

import com.indra.selecaoJava.model.dto.UsuarioDTO;
import com.indra.selecaoJava.model.entidade.Pessoa;
import com.indra.selecaoJava.model.entidade.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario salvar(UsuarioDTO usuario) throws Exception;

    UsuarioDTO editar(UsuarioDTO usuario) throws Exception;

    Usuario salvar(Usuario usuario) throws Exception;

    UsuarioDTO buscarUsuarioId(Long id) throws  Exception;

    Usuario buscarUsuarioLogin(String login) throws Exception;

    List<UsuarioDTO> buscarUsuarioFiltro(String cpf, String mome) throws Exception;

    public Usuario buscarUsuarioPessoa(Pessoa pessoa) throws Exception;

    public void deletar(Long id) throws Exception;
}
