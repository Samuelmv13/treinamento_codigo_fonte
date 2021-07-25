package com.basis.campina.xtarefas.service.feign;

import com.basis.campina.xtarefas.service.dto.DocumentoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "xdocs", url = "${application.feign.url-documents}")
public interface DocumentoClient {

    @GetMapping("/api/documents")
    String getAll();

    @GetMapping("/api/documents/{uuId}")
    ResponseEntity<DocumentoDTO> obterDocumento(@PathVariable("uuId") String uuId);

    @PostMapping("/api/documents")
    ResponseEntity<Void> salvar(List<DocumentoDTO> documentoDTOS);
}
