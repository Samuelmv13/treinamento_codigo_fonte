package com.basis.campina.xtarefas.service.filter;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TarefaFiltro extends DefaultFilter implements Serializable, BaseFilter {
    @Override
    public BoolQueryBuilder getFilter() {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        List<String> fields = new ArrayList<>();
        filterFields(fields, query, queryBuilder, "nome");
        addShouldTermQuery(queryBuilder, "dataInicio", query);
        addShouldTermQuery(queryBuilder, "dataConclusao", query);
        return queryBuilder;
    }
}
