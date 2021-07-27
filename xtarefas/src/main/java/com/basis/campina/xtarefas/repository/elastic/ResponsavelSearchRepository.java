package com.basis.campina.xtarefas.repository.elastic;

import com.basis.campina.xtarefas.domain.document.ResponsavelDocument;

public interface ResponsavelSearchRepository extends  ElasticEntity<ResponsavelDocument, Integer> {

    default Class<ResponsavelDocument> getEntityClass() {
        return ResponsavelDocument.class;
    }
}
