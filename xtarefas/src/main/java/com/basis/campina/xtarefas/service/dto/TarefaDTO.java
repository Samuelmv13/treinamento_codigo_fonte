package com.basis.campina.xtarefas.service.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class TarefaDTO implements Serializable {

    private static final long serialVersionUID = 1829357490246486671L;

    private Integer id;

    @NotNull
    private String nome;

    @NotNull
    private LocalDate dataConclusao;

    @NotNull
    private LocalDate dataInicio;

    @NotNull
    private String status;

    private Integer responsavel;

}