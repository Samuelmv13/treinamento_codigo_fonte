package com.basis.campina.xtarefas.service;

import com.basis.campina.xtarefas.domain.Tarefa;
import com.basis.campina.xtarefas.repository.TarefaRepository;
import com.basis.campina.xtarefas.service.dto.AnexoDTO;
import com.basis.campina.xtarefas.service.dto.TarefaDTO;
import com.basis.campina.xtarefas.service.event.TarefaEvent;
import com.basis.campina.xtarefas.service.mapper.TarefaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository repository;
    private final TarefaMapper tarefaMapper;
    private final ApplicationEventPublisher eventPublisher;
    private final AnexoService anexoService;
    private final ResponsavelService responsavelService;

    public TarefaDTO buscarPorId(Integer id) {
        Tarefa tarefa = repository.findById(id).orElseThrow(()->new RuntimeException("Tarefa não encontrada"));
        return tarefaMapper.toDto(tarefa);
    }

    public TarefaDTO salvar(TarefaDTO dto) {
        gerarChaveArquivo(dto.getAnexos());
        salvarDocumentos(dto.getAnexos());
        Tarefa tarefa = repository.save(tarefaMapper.toEntity(dto));
        eventPublisher.publishEvent(new TarefaEvent(tarefa.getId()));

        TarefaDTO tarefaDTO = tarefaMapper.toDto(tarefa);
        lancarEventoAnexo(tarefaDTO.getAnexos());
        emitirEventoResponsavel(tarefaDTO.getResponsavel().getId());
        return tarefaDTO;
    }

    public void remover(Integer id){
        buscarPorId(id);
        repository.deleteById(id);
    }

    private void gerarChaveArquivo(List<AnexoDTO> anexoDTOS){
        anexoDTOS.forEach(anexoDTO -> anexoDTO.getDocumentoDTO().setUuId(UUID.randomUUID().toString()));
    }

    private void emitirEventoResponsavel(Integer idResponsavel){
        responsavelService.emitirEvento(idResponsavel);
    }

    private void salvarDocumentos(List<AnexoDTO> anexoDTOS){
        anexoService.salvarDocumento(anexoDTOS);
    }

    private void lancarEventoAnexo(List<AnexoDTO> anexos){
        anexoService.lancarEvento(anexos);
    }

}
