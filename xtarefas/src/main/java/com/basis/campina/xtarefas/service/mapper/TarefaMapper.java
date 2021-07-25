package com.basis.campina.xtarefas.service.mapper;

import com.basis.campina.xtarefas.domain.Tarefa;
import com.basis.campina.xtarefas.service.dto.TarefaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TarefaMapper extends EntityMapper<TarefaDTO, Tarefa> {

    @Override
    @Mapping(source = "responsavel", target = "responsavel.id")
    Tarefa toEntity(TarefaDTO tarefaDTO);

    @Override
    @Mapping(source = "responsavel.id", target = "responsavel")
    TarefaDTO toDto(Tarefa tarefa);
}
