package com.basis.campina.xtarefas.service.dto;

import com.basis.campina.xtarefas.domain.Tarefa;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResponsavelDTO implements Serializable {

    private static final long serialVersionUID = 7344635105070713606L;

    private Integer id;

    private String nome;

    private String email;

    private LocalDate dataNascimento;

}
