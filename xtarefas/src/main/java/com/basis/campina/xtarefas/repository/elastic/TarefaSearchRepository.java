package com.basis.campina.xtarefas.repository.elastic;

import com.basis.campina.xtarefas.domain.document.TarefaDocument;

public interface TarefaSearchRepository extends ElasticEntity<TarefaDocument, Integer> {

    default Class<TarefaDocument> getEntityClass() {
        return TarefaDocument.class;
    }
}
