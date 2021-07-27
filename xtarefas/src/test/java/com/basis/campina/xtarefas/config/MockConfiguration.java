package com.basis.campina.xtarefas.config;

import com.basis.campina.xtarefas.service.feign.DocumentoClient;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class MockConfiguration {

    @MockBean
    private DocumentoClient documentoClient;

    @PostConstruct
    public void mock() {
        Mockito.when(documentoClient.getString()).thenReturn("Deu certo");
    }
}
