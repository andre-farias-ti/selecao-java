package com.indra.selecaoJava.model.dto;

import com.indra.selecaoJava.model.entidade.Perfil;
import com.indra.selecaoJava.model.entidade.Usuario;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LoginDTO {

    private String login;
    private String senha;
    private Usuario usuario;
    private List<Perfil> perfil;

    @Builder
    LoginDTO(String login, String senha, Usuario usuario, List<Perfil> perfil) {
        this.login = login;
        this.perfil = perfil;
        this.senha = senha;
        this.usuario = usuario;

    }

}
