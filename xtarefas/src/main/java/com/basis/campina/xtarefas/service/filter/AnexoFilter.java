package com.basis.campina.xtarefas.service.filter;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AnexoFilter extends DefaultFilter implements Serializable, BaseFilter {

    private static final long serialVersionUID = 2806940463718567025L;

    private String file;

    private String fileName;

    @Override
    public BoolQueryBuilder getFilter() {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        List<String> fields = new ArrayList<>();
        filterFields(fields, query, queryBuilder, "file", "filename");

        return  queryBuilder;
    }
}
