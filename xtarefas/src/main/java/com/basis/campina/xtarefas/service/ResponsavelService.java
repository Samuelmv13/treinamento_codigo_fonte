package com.basis.campina.xtarefas.service;

import com.basis.campina.xtarefas.domain.Responsavel;
import com.basis.campina.xtarefas.repository.ResponsavelRepository;
import com.basis.campina.xtarefas.service.dto.ResponsavelDTO;
import com.basis.campina.xtarefas.service.mapper.ResponsavelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResponsavelService {

    private final ResponsavelRepository repository;
    private final ResponsavelMapper responsavelMapper;

    public List<ResponsavelDTO> listarTodos() {
        return repository.findAll().stream().map(responsavelMapper::toDto).collect(Collectors.toList());
    }

    public ResponsavelDTO buscarPorId(Integer id) {
        Responsavel responsavel = repository.findById(id).orElseThrow(()->new RuntimeException("Responsável não encontrado"));
        return responsavelMapper.toDto(responsavel);
    }

    public ResponsavelDTO salvar(ResponsavelDTO responsavelDTO) {
        Responsavel objeto = responsavelMapper.toEntity(responsavelDTO);
        objeto = repository.save(objeto);
        return responsavelMapper.toDto(objeto);
    }

    public void remover(Integer id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}
