package com.basis.campina.xtarefas.service.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ResponsavelDTO implements Serializable {

    private static final long serialVersionUID = 7344635105070713606L;

    private Integer id;

    @NotNull
    private String nome;

    @NotNull
    private String email;

    @NotNull
    private LocalDate dataNascimento;
}
