package com.basis.campina.xtarefas.service.mapper;

import com.basis.campina.xtarefas.domain.Anexo;
import com.basis.campina.xtarefas.service.dto.AnexoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnexoMapper extends EntityMapper<AnexoDTO, Anexo> {

    @Override
    @Mapping(source = "idTarefa", target = "tarefa.id")
    Anexo toEntity(AnexoDTO anexoDTO);

    @Override
    @Mapping(source = "tarefa.id", target = "idTarefa")
    AnexoDTO toDto(Anexo anexo);
}
