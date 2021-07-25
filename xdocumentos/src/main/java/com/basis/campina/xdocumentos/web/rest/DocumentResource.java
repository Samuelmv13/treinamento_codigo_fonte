package com.basis.campina.xdocumentos.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentResource {

    @GetMapping()
    ResponseEntity<String> listar() {
        return new ResponseEntity<String>("Deu certo", HttpStatus.OK);
    }
}
