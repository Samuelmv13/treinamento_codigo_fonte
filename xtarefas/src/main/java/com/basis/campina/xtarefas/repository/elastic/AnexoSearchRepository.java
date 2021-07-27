package com.basis.campina.xtarefas.repository.elastic;

import com.basis.campina.xtarefas.domain.document.AnexoDocument;

public interface AnexoSearchRepository extends ElasticEntity<AnexoDocument, Integer> {

    default Class<AnexoDocument> getEntityClass() {
        return AnexoDocument.class;
    }
}
