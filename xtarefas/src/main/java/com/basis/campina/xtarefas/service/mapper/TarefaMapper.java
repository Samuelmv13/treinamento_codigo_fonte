package com.basis.campina.xtarefas.service.mapper;

import com.basis.campina.xtarefas.domain.Tarefa;
import com.basis.campina.xtarefas.service.dto.TarefaDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {AnexoMapper.class})
public interface TarefaMapper extends EntityMapper<TarefaDTO, Tarefa> {

    @AfterMapping
    default void converterListaParaEntity(@MappingTarget Tarefa tarefa) {
        tarefa.getAnexos().forEach(anexo -> anexo.setTarefa(tarefa));
    }
}
