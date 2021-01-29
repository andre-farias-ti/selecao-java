package com.indra.selecaoJava.model.dto;

import com.indra.selecaoJava.model.entidade.Endereco;
import com.indra.selecaoJava.model.entidade.Pessoa;
import com.indra.selecaoJava.model.entidade.Usuario;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UsuarioDTO {

    Pessoa pessoa;
    String login;
    String senha;
    Long idPerfil;

    public UsuarioDTO() {
    }

    @Builder
    public UsuarioDTO(Pessoa pessoa, String login, String senha, Long idPerfil){
        this.pessoa = pessoa;
        this.login =  login;
        this.senha = senha;
        this.idPerfil = idPerfil;
    }
}
