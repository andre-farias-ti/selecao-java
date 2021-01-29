package com.indra.selecaoJava.model.entidade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, length = 50)
    private String nome;

    @Column(nullable = true, length = 50)
    private String email;

    @Column(nullable = true, length = 50)
    private String cpf;

    @Column(nullable = true, length = 50)
    private Date dtNascimento;

    @Column(nullable = true, length = 50)
    private String telefone;

    @OneToOne()
    @JoinColumn(name = "idEndereco")
    private Endereco endereco;
}
