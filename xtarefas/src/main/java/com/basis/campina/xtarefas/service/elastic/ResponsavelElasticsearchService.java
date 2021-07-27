package com.basis.campina.xtarefas.service.elastic;

import com.basis.campina.xtarefas.domain.document.ResponsavelDocument;
import com.basis.campina.xtarefas.repository.ResponsavelRepository;
import com.basis.campina.xtarefas.repository.TarefaRepository;
import com.basis.campina.xtarefas.repository.elastic.Reindexer;
import com.basis.campina.xtarefas.repository.elastic.ResponsavelSearchRepository;
import com.basis.campina.xtarefas.service.dto.DominioFixoDTO;
import com.basis.campina.xtarefas.service.event.ResponsavelEvent;
import com.basis.campina.xtarefas.service.filter.ResponsavelFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResponsavelElasticsearchService implements Reindexer{

    private final ResponsavelRepository responsavelRepository;
    private final ResponsavelSearchRepository responsavelSearchRepository;
    private final TarefaRepository tarefaRepository;

    public Page<ResponsavelDocument> search(ResponsavelFilter filtro, Pageable pageable) {
        return responsavelSearchRepository.search(filtro.getFilter(), pageable);
    }

    @TransactionalEventListener(fallbackExecution = true)
    @Transactional(readOnly = true)
    public void indexar(ResponsavelEvent event) {
        log.info("[XTAREFAS] Indexando Responsavel: {}", event.getId());
        ResponsavelDocument document = responsavelRepository.getDocument(event.getId());
        processarResponsavelDocument(document, event.getId());
        responsavelSearchRepository.save(document);
    }

    @Override
    public Page<ResponsavelDocument> reindexPage(Pageable pageable) throws IllegalAccessException {
        Page<ResponsavelDocument> documentsPage = responsavelRepository.reindexPage(pageable);
        documentsPage.getContent().forEach(document -> {
            processarResponsavelDocument(document, document.getId());
        });
        return Reindexer.super.reindexPage(documentsPage.getPageable());
    }

    private void processarResponsavelDocument(ResponsavelDocument document, Integer id){
        List<DominioFixoDTO> tarefas = tarefaRepository.buscarTarefasPorNomes(id);
        document.setTarefas(Objects.isNull(tarefas) ? "" : tarefas.stream().map(DominioFixoDTO::getLabel).collect(Collectors.joining(", ")));
    }

    @Override
    public String getEntity() {
        return "responsaveis";
    }
}