package com.basis.campina.xtarefas.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "xdocs", url = "${application.feign.url-documents}")
public interface DocumentoClient {

    @GetMapping("/api/documents")
    String getAll();
}
