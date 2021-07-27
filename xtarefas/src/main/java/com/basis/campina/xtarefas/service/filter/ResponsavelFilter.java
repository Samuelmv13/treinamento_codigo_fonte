package com.basis.campina.xtarefas.service.filter;

import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResponsavelFilter extends DefaultFilter implements Serializable, BaseFilter {

    private static final long serialVersionUID = 9202654014841507901L;

    private String nome;

    private String email;

    private LocalDate dataNasc;

    private String tarefas;

    @Override
    public BoolQueryBuilder getFilter() {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        List<String> fields = new ArrayList<>();
        filterFields(fields, query, queryBuilder, "nome", "email", "tarefas");
        addShouldTermQuery(queryBuilder, "dataNasc", query);

        return queryBuilder;
    }
}
