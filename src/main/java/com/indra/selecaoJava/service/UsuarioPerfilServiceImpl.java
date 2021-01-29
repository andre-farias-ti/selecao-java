package com.indra.selecaoJava.service;

import com.indra.selecaoJava.model.entidade.Perfil;
import com.indra.selecaoJava.model.entidade.Pessoa;
import com.indra.selecaoJava.model.entidade.UsrPerfil;
import com.indra.selecaoJava.model.entidade.Usuario;
import com.indra.selecaoJava.repository.PessoaRepository;
import com.indra.selecaoJava.repository.UsrPerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioPerfilServiceImpl implements UsuarioPerfilService{

    @Autowired
    private UsrPerfilRepository usrPerfilRepository;

    @Override
    public UsrPerfil salvar (Usuario usuario, Perfil perfil) throws Exception {
        return usrPerfilRepository.save(UsrPerfil.builder().perfil(perfil).usuario(usuario).build());
    }

    @Override
    public UsrPerfil salvar (UsrPerfil usrPerfil) throws Exception {
        return usrPerfilRepository.save(usrPerfil);
    }

    @Override
    public UsrPerfil buscar(Usuario usuario) throws Exception {
        return usrPerfilRepository.findByUsuario(usuario);
    }

    @Override
    public void deletar(Usuario usuario) throws Exception {
        UsrPerfil usrPerfil = usrPerfilRepository.findByUsuario(usuario);
        usrPerfilRepository.delete(usrPerfil);
    }
}
