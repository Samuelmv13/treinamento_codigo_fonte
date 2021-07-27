package com.basis.campina.xtarefas.service.elastic;

import com.basis.campina.xtarefas.domain.document.TarefaDocument;
import com.basis.campina.xtarefas.repository.TarefaRepository;
import com.basis.campina.xtarefas.repository.elastic.TarefaSearchRepository;
import com.basis.campina.xtarefas.service.event.TarefaEvent;
import com.basis.campina.xtarefas.service.filter.TarefaFIlter;
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
public class TarefaElasticsearchService {

    private final TarefaRepository tarefaRepository;
    private final TarefaSearchRepository tarefaSearchRepository;

    public Page<TarefaDocument> search(TarefaFIlter filtro, Pageable pageable) {
        return tarefaSearchRepository.search(filtro.getFilter(), pageable);
    }

    @TransactionalEventListener(fallbackExecution = true)
    public void indexar(TarefaEvent event) {
        log.info("Iniciando Indexação de tarefa a partir de: {}", event.getId());
        TarefaDocument tarefaDocument = tarefaRepository.getDocument(event.getId());
        tarefaSearchRepository.save(tarefaDocument);
    }
}
