package com.basis.campina.xtarefas.service;

import com.basis.campina.xtarefas.domain.Tarefa;
import com.basis.campina.xtarefas.repository.TarefaRepository;
import com.basis.campina.xtarefas.service.dto.TarefaDTO;
import com.basis.campina.xtarefas.service.mapper.TarefaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository repository;
    private final TarefaMapper tarefaMapper;

    public List<TarefaDTO> listarTodos() {
        return repository.findAll().stream().map(tarefaMapper::toDto).collect(Collectors.toList());
    }

    public TarefaDTO buscarPorId(Integer id) {
        Tarefa tarefa = repository.findById(id).orElseThrow(()->new RuntimeException("Tarefa não encontrada"));
        return tarefaMapper.toDto(tarefa);
    }

    public TarefaDTO salvar(TarefaDTO tarefaDTO) {
        Tarefa objeto = tarefaMapper.toEntity(tarefaDTO);
        objeto = repository.save(objeto);
        return tarefaMapper.toDto(objeto);
    }

    public void remover(Integer id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}
