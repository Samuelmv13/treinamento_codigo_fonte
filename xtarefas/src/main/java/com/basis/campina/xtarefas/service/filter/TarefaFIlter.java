package com.basis.campina.xtarefas.service.filter;

import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.index.query.BoolQueryBuilder;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class TarefaFIlter implements Serializable, BaseFilter {

    private static final long serialVersionUID = -2819489366478872620L;

    private String nome;

    private LocalDate dataConclusao;

    private LocalDate dataInicio;

    private String status;

    private String responsavel;

    private String anexos;

    @Override
    public BoolQueryBuilder getFilter() {
        return null;
    }
}
