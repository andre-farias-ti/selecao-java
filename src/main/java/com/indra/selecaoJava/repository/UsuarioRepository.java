package com.indra.selecaoJava.repository;

import com.indra.selecaoJava.model.dto.UsuarioDTO;
import com.indra.selecaoJava.model.entidade.Pessoa;
import com.indra.selecaoJava.model.entidade.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByLogin(String login);

    public Usuario findByPessoa(Pessoa pessoa);

    @Query(value="SELECT new com.indra.selecaoJava.model.dto.UsuarioDTO(p, u.login, u.senha, per.id) " +
            " FROM Pessoa p, Endereco e, UsrPerfil usr, Usuario u, Perfil per " +
            " WHERE p.endereco.id  = e.id " +
            " AND usr.usuario.id   = u.id " +
            " AND u.pessoa.id  = p.id " +
            " AND usr.perfil.id  = per.id " +
            " AND p.cpf  = ?1")
    public List<UsuarioDTO> buscarUsuarioFiltroCpf(String cpf);

    @Query(value="SELECT new com.indra.selecaoJava.model.dto.UsuarioDTO(p, u.login, u.senha, per.id) " +
            " FROM Pessoa p, Endereco e, UsrPerfil usr, Usuario u, Perfil per " +
            " WHERE p.endereco.id  = e.id " +
            " AND usr.usuario.id   = u.id " +
            " AND u.pessoa.id  = p.id " +
            " AND usr.perfil.id  = per.id " +
            " AND lower(p.nome) like %:nome%")
    public List<UsuarioDTO> buscarUsuarioFiltroNome(@Param("nome") String name);

    @Query(value="SELECT new com.indra.selecaoJava.model.dto.UsuarioDTO(p, u.login, u.senha, per.id) " +
            " FROM Pessoa p, Endereco e, UsrPerfil usr, Usuario u, Perfil per " +
            " WHERE p.endereco.id  = e.id " +
            " AND usr.usuario.id   = u.id " +
            " AND u.pessoa.id  = p.id " +
            " AND usr.perfil.id  = per.id " +
            " AND p.id = ?1")
    public UsuarioDTO buscarUsuarioFiltroId(Long id);

}
