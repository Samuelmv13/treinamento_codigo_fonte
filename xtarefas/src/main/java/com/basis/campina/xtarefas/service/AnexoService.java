package com.basis.campina.xtarefas.service;

import com.basis.campina.xtarefas.repository.AnexoRepository;
import com.basis.campina.xtarefas.service.dto.AnexoDTO;
import com.basis.campina.xtarefas.service.dto.DocumentoDTO;
import com.basis.campina.xtarefas.service.event.AnexoEvent;
import com.basis.campina.xtarefas.service.feign.DocumentoClient;
import com.basis.campina.xtarefas.service.mapper.AnexoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AnexoService {

    private final AnexoRepository repository;
    private final AnexoMapper anexoMapper;
    private final DocumentoClient documentoClient;
    private final ApplicationEventPublisher eventPublisher;

    public void salvarDocumento(List<AnexoDTO> anexoDTOs){
        List<DocumentoDTO> documentoDTOS = anexoDTOs.stream().map(AnexoDTO:: getDocumentoDTO).collect(Collectors.toList());
        documentoClient.salvar(documentoDTOS);
    }

    @Transactional
    public AnexoDTO obterPorId(Integer id) {
        return anexoMapper.toDto(repository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não existente")));
    }

    public DocumentoDTO obterDocumento(String uuId){
        return documentoClient.obterDocumento(uuId).getBody();
    }

    public void lancarEvento(List<AnexoDTO> anexos){
        anexos.forEach(anexo -> eventPublisher.publishEvent(new AnexoEvent(anexo.getId())));
    }
}