package com.basis.campina.xtarefas.service.elastic;

import com.basis.campina.xtarefas.domain.elasticsearch.ResponsavelDocument;
import com.basis.campina.xtarefas.repository.ResponsavelRepository;
import com.basis.campina.xtarefas.repository.elastic.ResponsavelSearchRepository;
import com.basis.campina.xtarefas.service.event.ResponsavelEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @TransactionalEventListener(fallbackExecution = true)
    public void reindex(ResponsavelEvent event) {
        log.info("Iniciando Indexação de responsável a partir de: {}", event.getId());
        ResponsavelDocument responsavelDocument = responsavelRepository.getDocument(event.getId());
        responsavelSearchRepository.save(responsavelDocument);
    }
}