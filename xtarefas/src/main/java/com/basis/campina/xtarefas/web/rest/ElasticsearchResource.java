package com.basis.campina.xtarefas.web.rest;

import com.basis.campina.xtarefas.service.elastic.ElasticSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/elasticsearch/reindex")
@RequiredArgsConstructor
public class ElasticsearchResource {

    private final ElasticSearchService elasticSearchService;

    @GetMapping
    public ResponseEntity<Void> reindex() throws Exception {
        this.elasticSearchService.reindex();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{entity}")
    public ResponseEntity<Void> reindex(@PathVariable("entity") String entity) throws Exception {
        this.elasticSearchService.reindexEntity(entity);
        return ResponseEntity.ok().build();
    }

}