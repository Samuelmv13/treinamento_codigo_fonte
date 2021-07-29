package com.basis.campina.xtarefas.service;

import com.basis.campina.xtarefas.domain.Tarefa;
import com.basis.campina.xtarefas.repository.TarefaRepository;
import com.basis.campina.xtarefas.service.dto.TarefaDTO;
import com.basis.campina.xtarefas.service.event.TarefaEvent;
import com.basis.campina.xtarefas.service.mapper.TarefaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository repository;
    private final TarefaMapper tarefaMapper;
    private final ApplicationEventPublisher eventPublisher;


    public TarefaDTO buscarPorId(Integer id) {
        Tarefa tarefa = repository.findById(id).orElseThrow(()->new RuntimeException("Tarefa não encontrada"));
        return tarefaMapper.toDto(tarefa);
    }

    public TarefaDTO salvar(TarefaDTO tarefaDTO) {
        Tarefa objeto = tarefaMapper.toEntity(tarefaDTO);
        objeto = repository.save(objeto);
        eventPublisher.publishEvent(new TarefaEvent(objeto.getId()));
        return tarefaMapper.toDto(objeto);
    }

    public void remover(Integer id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}
