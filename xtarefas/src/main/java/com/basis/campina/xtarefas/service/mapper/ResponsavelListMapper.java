package com.basis.campina.xtarefas.service.mapper;

import com.basis.campina.xtarefas.domain.document.ResponsavelDocument;
import com.basis.campina.xtarefas.service.dto.ResponsavelDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponsavelListMapper extends EntityMapper<ResponsavelDTO, ResponsavelDocument> {
}
