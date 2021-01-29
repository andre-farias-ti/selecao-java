package com.indra.selecaoJava.controller;

import com.indra.selecaoJava.model.entidade.Perfil;
import com.indra.selecaoJava.model.entidade.Usuario;
import com.indra.selecaoJava.model.dto.LoginDTO;
import com.indra.selecaoJava.repository.PerfilRepository;
import com.indra.selecaoJava.repository.UsuarioRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class AutenticacaoController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PerfilRepository perfilRepository;


    @ApiOperation(value = "Retorna obejeto de autenticação")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornada  objeto que permitirar fazer login na API"),
            @ApiResponse(code = 400, message = "Erro não exite usuario ou login e senha erradas"),
            @ApiResponse(code = 500, message = "Erro interno do servidor"),
    })
    @PostMapping(value = "/login")
    public ResponseEntity<LoginDTO> login(@RequestBody LoginDTO loginDTO) throws Exception {

        Usuario usuario = usuarioRepository.findByLogin(loginDTO.getLogin());

        LoginDTO usuarioLogado = null;

        if (Objects.isNull(usuario)) {
            return ResponseEntity.noContent().build();
        }

        if (usuario.getSenha().equals(loginDTO.getSenha())) {
            List<Perfil> listaPerfil = perfilRepository.buscarPerfisUsuario(usuario.getId());

            usuarioLogado =
                LoginDTO.builder()
                    .login(usuario.getLogin())
                    .senha(usuario.getSenha())
                    .perfil(listaPerfil)
                    .usuario(usuario).build();

            usuarioLogado.setPerfil(listaPerfil);
        }

        return ResponseEntity.ok().body(usuarioLogado);
    }

}
