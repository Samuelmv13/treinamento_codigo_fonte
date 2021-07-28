package com.basis.campina.xtarefas.service.elastic;

import com.basis.campina.xtarefas.domain.document.ResponsavelDocument;
import com.basis.campina.xtarefas.repository.ResponsavelRepository;
import com.basis.campina.xtarefas.repository.elastic.ResponsavelSearchRepository;
import com.basis.campina.xtarefas.service.event.ResponsavelEvent;
import com.basis.campina.xtarefas.service.filter.ResponsavelFiltro;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResponsavelElasticsearchService {

    private final ResponsavelRepository responsavelRepository;
    private final ResponsavelSearchRepository responsavelSearchRepository;

    public Page<ResponsavelDocument> search(ResponsavelFiltro filtro, Pageable pageable) {
        return responsavelSearchRepository.search(filtro.getFilter(), pageable);
    }

    @TransactionalEventListener(fallbackExecution = true)
    public void reindex(ResponsavelEvent event) {
        log.info("Iniciando Indexação de responsável a partir de: {}", event.getId());
        ResponsavelDocument responsavelDocument = responsavelRepository.getDocument(event.getId());
        responsavelSearchRepository.save(responsavelDocument);
    }
}