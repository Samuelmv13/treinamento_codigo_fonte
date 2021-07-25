package com.basis.campina.xtarefas.service.mapper;

import com.basis.campina.xtarefas.domain.Tarefa;
import com.basis.campina.xtarefas.service.dto.TarefaDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TarefaMapper extends EntityMapper<TarefaDTO, Tarefa> {

    @Override
    @Mapping(source = "responsavel", target = "responsavel.id")
    Tarefa toEntity(TarefaDTO tarefaDTO);

    @Override
    @Mapping(source = "responsavel.id", target = "responsavel")
    TarefaDTO toDto(Tarefa tarefa);

    @AfterMapping
    default void converterListaParaEntity(@MappingTarget Tarefa tarefa) {
        tarefa.getAnexos().forEach(anexo -> anexo.setTarefa(tarefa));
    }
}
