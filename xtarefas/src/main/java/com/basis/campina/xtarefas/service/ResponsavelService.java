package com.basis.campina.xtarefas.service;

import com.basis.campina.xtarefas.domain.Responsavel;
import com.basis.campina.xtarefas.repository.ResponsavelRepository;
import com.basis.campina.xtarefas.service.dto.ResponsavelDTO;
import com.basis.campina.xtarefas.service.event.ResponsavelEvent;
import com.basis.campina.xtarefas.service.exception.ParametrizedMessageException;
import com.basis.campina.xtarefas.service.mapper.ResponsavelMapper;
import com.basis.campina.xtarefas.service.util.ConstantsUtil;
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

    public ResponsavelDTO buscarPorId(Integer id) {
        Responsavel responsavel = repository.findById(id).orElseThrow(()->new RuntimeException("Responsável não encontrado"));
        return responsavelMapper.toDto(responsavel);
    }

    public ResponsavelDTO salvar(ResponsavelDTO responsavelDTO) {
        Responsavel objeto = responsavelMapper.toEntity(responsavelDTO);
        objeto = repository.save(objeto);
        eventPublisher.publishEvent(new ResponsavelEvent(objeto.getId()));
        return responsavelMapper.toDto(objeto);
    }

    public void editar(ResponsavelDTO responsavelDTO) {
        verificarExistencia(responsavelDTO.getId());
        salvar(responsavelDTO);
    }

    public void verificarExistencia(Integer id) {
        if (!repository.existsById(id)) {
            throw new ParametrizedMessageException(ConstantsUtil.RESPONSAVEL_NAO_ENCONTRADO, ConstantsUtil.ERROR_TITLE);
        }
    }

    public void remover(Integer id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}
