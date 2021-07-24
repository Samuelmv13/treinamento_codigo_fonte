package com.basis.campina.xtarefas.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "TB_RESPONSAVEL")
@Getter
@Setter
public class Responsavel implements Serializable {

    private static final long serialVersionUID = 6559288294111953644L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_responsavel")
    @SequenceGenerator(name = "seq_responsavel", allocationSize = 1, sequenceName = "seq_responsavel")
    @Column(name = "id_responsavel")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
}
