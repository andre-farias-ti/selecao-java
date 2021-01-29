package com.indra.selecaoJava.model.entidade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idPessoa")
    private Pessoa pessoa;

    @Column(nullable = true)
    private String login;

    @Column(nullable = true)
    @JsonIgnore
    private String senha;

    @Builder
    Usuario(Pessoa pessoa, String login, String senha){
        this.pessoa = pessoa;
        this.login = login;
        this.senha = senha;
    }

}
