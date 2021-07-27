package com.basis.campina.xtarefas.web.rest;

import com.basis.campina.xtarefas.domain.document.ResponsavelDocument;
import com.basis.campina.xtarefas.service.ResponsavelService;
import com.basis.campina.xtarefas.service.dto.ResponsavelDTO;
import com.basis.campina.xtarefas.service.elastic.ResponsavelElasticsearchService;
import com.basis.campina.xtarefas.service.filter.ResponsavelFilter;
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

@RestController
@RequestMapping("/api/responsaveis")
@RequiredArgsConstructor
@Slf4j
public class ResponsavelResource {

    private final ResponsavelService responsavelService;
    private final ResponsavelElasticsearchService responsavelElasticsearchService;

    @PostMapping("/_search")
    public ResponseEntity<Page<ResponsavelDocument>> search(@RequestBody ResponsavelFilter filter, Pageable pageable) {
        Page<ResponsavelDocument> responsaveis = responsavelElasticsearchService.search(filter, pageable);
        return new ResponseEntity<>(responsaveis, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsavelDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(responsavelService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ResponsavelDTO> inserir(@RequestBody ResponsavelDTO responsavelDTO) throws URISyntaxException {
        ResponsavelDTO dto = responsavelService.salvar(responsavelDTO);
        return ResponseEntity.created(new URI("/api/responsaveis")).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsavelDTO> atualizar(@PathVariable Integer id, @RequestBody ResponsavelDTO responsavelDTO) {
        responsavelDTO.setId(id);
        ResponsavelDTO dto = responsavelService.salvar(responsavelDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponsavelDTO> deletar(@PathVariable Integer id) {

        responsavelService.remover(id);
        return ResponseEntity.ok().build();
    }
}
