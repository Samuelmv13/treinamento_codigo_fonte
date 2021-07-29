package com.basis.campina.xtarefas.test.builder;

import com.basis.campina.xtarefas.domain.Tarefa;
import com.basis.campina.xtarefas.service.TarefaService;
import com.basis.campina.xtarefas.service.dto.TarefaDTO;
import com.basis.campina.xtarefas.service.mapper.TarefaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;

@Component
public class TarefaBuilder extends ConstrutorEntidade<Tarefa> {

    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private TarefaMapper tarefaMapper;

    @Autowired
    private ResponsavelBuilder responsavelBuilder;

    @Override
    public Tarefa construirEntidade() throws ParseException {
        Tarefa tarefa = new Tarefa();
        tarefa.setNome("Tarefa nome exemplo");
        tarefa.setDataInicio(LocalDate.now());
        tarefa.setDataConclusao(LocalDate.now());
        tarefa.setStatus("Ativo");
        tarefa.setResponsavel(responsavelBuilder.construir());
        tarefa.setAnexos(null);
        return tarefa;
    }

    public TarefaDTO construirObjetoDTO() throws ParseException {
        return this.tarefaMapper.toDto(this.construirEntidade());
    }

    public TarefaDTO construirDTO() throws ParseException {
        return this.tarefaMapper.toDto(this.construir());
    }

    @Override
    protected Tarefa persistir(Tarefa entidade) {
        TarefaDTO tarefaDTO = tarefaService.salvar(tarefaMapper.toDto(entidade));
        return tarefaMapper.toEntity(tarefaDTO);
    }

    @Override
    protected Collection<Tarefa> obterTodos() {
        return null;
    }

    @Override
    protected Tarefa obterPorId(Integer id) {
        return null;
    }
}
