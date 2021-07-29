package com.basis.campina.xtarefas.web.rest;

import com.basis.campina.xtarefas.domain.document.ResponsavelDocument;
import com.basis.campina.xtarefas.domain.document.TarefaDocument;
import com.basis.campina.xtarefas.service.TarefaService;
import com.basis.campina.xtarefas.service.dto.TarefaDTO;
import com.basis.campina.xtarefas.service.elastic.TarefaElasticsearchService;
import com.basis.campina.xtarefas.service.filter.ResponsavelFiltro;
import com.basis.campina.xtarefas.service.filter.TarefaFiltro;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
@RequiredArgsConstructor
@Slf4j
public class TarefaResource {

    private final TarefaService tarefaService;
    private final TarefaElasticsearchService elasticsearchService;

    @PostMapping("/search")
    public ResponseEntity<Page<TarefaDocument>> search(@RequestBody TarefaFiltro filter, Pageable pageable) {
        Page<TarefaDocument> tarefas = elasticsearchService.search(filter, pageable);
        return new ResponseEntity<>(tarefas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(tarefaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<TarefaDTO> inserir(@RequestBody TarefaDTO tarefaDTO) throws URISyntaxException {
        TarefaDTO dto = tarefaService.salvar(tarefaDTO);
        return ResponseEntity.created(new URI("/api/tarefas")).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaDTO> atualizar(@PathVariable Integer id, @RequestBody TarefaDTO tarefaDTO) {
        tarefaDTO.setId(id);
        TarefaDTO dto = tarefaService.salvar(tarefaDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TarefaDTO> deletar(@PathVariable Integer id) {

        tarefaService.remover(id);
        return ResponseEntity.ok().build();
    }
}
