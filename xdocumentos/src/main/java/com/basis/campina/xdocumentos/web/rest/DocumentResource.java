package com.basis.campina.xdocumentos.web.rest;

import com.basis.campina.xdocumentos.service.DocumentoService;
import com.basis.campina.xdocumentos.service.dto.DocumentoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentResource {

    private final DocumentoService documentoService;

    @GetMapping()
    ResponseEntity<String> listar() {
        return new ResponseEntity<String>("Deu certo", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> salvarDocumento(@RequestBody List<DocumentoDTO> documentoDTOS) {
        documentoService.salvarDocumentos(documentoDTOS);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{uuId}")
    public ResponseEntity<DocumentoDTO> obterDocumento(@PathVariable("uuId") String uuId) {
        DocumentoDTO documentoDTO = documentoService.getDocument(uuId);
        return ResponseEntity.ok(documentoDTO);
    }
}
