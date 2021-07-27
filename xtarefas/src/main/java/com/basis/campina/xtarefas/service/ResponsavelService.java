package com.basis.campina.xtarefas.service;

import com.basis.campina.xtarefas.domain.Responsavel;
import com.basis.campina.xtarefas.repository.ResponsavelRepository;
import com.basis.campina.xtarefas.service.dto.ResponsavelDTO;
import com.basis.campina.xtarefas.service.event.ResponsavelEvent;
import com.basis.campina.xtarefas.service.mapper.ResponsavelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ResponsavelService {

    private final ResponsavelRepository repository;
    private final ResponsavelMapper responsavelMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional(readOnly = true)
    public ResponsavelDTO buscarPorId(Integer id) {
        Responsavel responsavel = repository.findById(id).orElseThrow(()->new RuntimeException("Responsável não encontrado"));
        return responsavelMapper.toDto(responsavel);
    }

    public ResponsavelDTO salvar(ResponsavelDTO responsavelDTO) {
        Responsavel objeto = repository.save(responsavelMapper.toEntity(responsavelDTO));
        emitirEvento(objeto.getId());
        return responsavelMapper.toDto(objeto);
    }

    public void remover(Integer id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
    public void emitirEvento(Integer id){
        eventPublisher.publishEvent(new ResponsavelEvent(id));
    }
}
