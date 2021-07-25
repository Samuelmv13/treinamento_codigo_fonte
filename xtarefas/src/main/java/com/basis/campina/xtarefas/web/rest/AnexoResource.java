package com.basis.campina.xtarefas.web.rest;

import com.basis.campina.xtarefas.service.feign.DocumentoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/anexos")
@RequiredArgsConstructor
public class AnexoResource {

    private final DocumentoClient documentoClient;

    @GetMapping("/string")
    public String getAll() {
        return documentoClient.getAll();
    }
}
