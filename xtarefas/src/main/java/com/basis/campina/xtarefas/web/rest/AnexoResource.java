package com.basis.campina.xtarefas.web.rest;

import com.basis.campina.xtarefas.domain.document.AnexoDocument;
import com.basis.campina.xtarefas.service.AnexoService;
import com.basis.campina.xtarefas.service.dto.AnexoDTO;
import com.basis.campina.xtarefas.service.dto.DocumentoDTO;
import com.basis.campina.xtarefas.service.elastic.AnexoElasticsearchService;
import com.basis.campina.xtarefas.service.filter.AnexoFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/anexos")
@RequiredArgsConstructor
public class AnexoResource {

    private final AnexoService anexoService;

    private final AnexoElasticsearchService anexoElasticsearchService;

    @GetMapping("/{id}")
    public ResponseEntity<AnexoDTO> obterPorId(@PathVariable Integer id){
        return ResponseEntity.ok(anexoService.obterPorId(id));
    }

    @GetMapping("/documento/{uuId}")
    public ResponseEntity<DocumentoDTO> obterDocumento(@PathVariable String uuId) {
        return ResponseEntity.ok(anexoService.obterDocumento(uuId));
    }

    @PostMapping("/_search")
    public ResponseEntity<Page<AnexoDocument>> search(@RequestBody AnexoFilter filter, Pageable pageable) {
        Page<AnexoDocument> anexos = anexoElasticsearchService.search(filter, pageable);
        return new ResponseEntity<>(anexos, HttpStatus.OK);
    }
}
