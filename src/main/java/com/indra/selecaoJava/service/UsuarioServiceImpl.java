package com.indra.selecaoJava.service;

import com.indra.selecaoJava.model.dto.UsuarioDTO;
import com.indra.selecaoJava.model.entidade.Perfil;
import com.indra.selecaoJava.model.entidade.Pessoa;
import com.indra.selecaoJava.model.entidade.UsrPerfil;
import com.indra.selecaoJava.model.entidade.Usuario;
import com.indra.selecaoJava.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    UsuarioRepository repository;

    @Autowired
    PessoaService pessoaService;

    @Autowired
    UsuarioPerfilService usuarioPerfilService;

    @Override
    @Transactional
    public Usuario salvar(UsuarioDTO usuarioDTO) throws Exception {

        Pessoa pessoa = pessoaService.salvar(usuarioDTO.getPessoa());
        Usuario usuario = repository.save(Usuario.builder()
                .login(usuarioDTO.getLogin())
                .pessoa(usuarioDTO.getPessoa())
                .senha(usuarioDTO.getSenha()).build());
        UsrPerfil usrPerfil = usuarioPerfilService.salvar(usuario,
                Perfil.builder().id(usuarioDTO.getIdPerfil()).build());
        return usuario;
    }


    @Override
    @Transactional
    public UsuarioDTO editar(UsuarioDTO usuarioDTO) throws Exception {

        Usuario usuario = buscarUsuarioPessoa(pessoaService.salvar(usuarioDTO.getPessoa()));
        usuario.setLogin(usuarioDTO.getLogin());
        usuario.setSenha(usuarioDTO.getSenha());
        repository.save(usuario);

        UsrPerfil usrPerfil = usuarioPerfilService.buscar(usuario);
        usrPerfil.setPerfil(Perfil.builder().id(usuarioDTO.getIdPerfil()).build());
        usuarioPerfilService.salvar(usrPerfil);
        return usuarioDTO;
    }

    @Override
    @Transactional
    public void deletar(Long id) throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        Usuario usuario = buscarUsuarioPessoa(pessoa);
        usuarioPerfilService.deletar(usuario);
    }

    @Override
    public Usuario salvar(Usuario usuario) throws Exception {
        return repository.save(usuario);
    }

    @Override
    public UsuarioDTO buscarUsuarioId(Long id) throws Exception {
        return repository.buscarUsuarioFiltroId(id);
    }

    @Override
    public Usuario buscarUsuarioPessoa(Pessoa pessoa) throws Exception {
        return repository.findByPessoa(pessoa);
    }

    @Override
    public Usuario buscarUsuarioLogin(String longin) throws Exception {
        return repository.findByLogin(longin);
    }

    @Override
    public List<UsuarioDTO> buscarUsuarioFiltro(String cpf, String nome) throws Exception {

        if(Objects.nonNull(cpf) && !cpf.isEmpty()){
            return repository.buscarUsuarioFiltroCpf(linparString(cpf));
        }else{
            return repository.buscarUsuarioFiltroNome(linparString(nome));
        }
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
