package com.indra.selecaoJava.model.entidade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
    @AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, length = 50)
    private String estado;

    @Column(nullable = true, length = 50)
    private String cidade;

    @Column(nullable = true, length = 50)
    private String bairro;

    @Column(nullable = true, length = 50)
    private String rua;

    @Column(nullable = true, length = 50)
    private String numero;

    @Column(nullable = true, length = 50)
    private String complemento;
}
