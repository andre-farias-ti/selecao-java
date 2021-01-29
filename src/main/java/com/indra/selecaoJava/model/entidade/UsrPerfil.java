package com.indra.selecaoJava.model.entidade;

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
@Table(name = "usrPerfil")
public class UsrPerfil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idPerfil")
    private Perfil perfil;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @Builder
    UsrPerfil(Usuario usuario, Perfil perfil){
        this.perfil = perfil;
        this.usuario = usuario;
    }

}
