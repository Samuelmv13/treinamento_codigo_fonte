package com.basis.campina.xtarefas.test.builder;

import com.basis.campina.xtarefas.domain.Responsavel;
import com.basis.campina.xtarefas.service.ResponsavelService;
import com.basis.campina.xtarefas.service.dto.ResponsavelDTO;
import com.basis.campina.xtarefas.service.mapper.ResponsavelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;

@Component
public class ResponsavelBuilder extends ConstrutorEntidade<Responsavel> {

    @Autowired
    private ResponsavelService responsavelService;

    @Autowired
    private ResponsavelMapper responsavelMapper;

    @Override
    public Responsavel construirEntidade() throws ParseException {
        Responsavel responsavel = new Responsavel();
        responsavel.setNome("Samuel");
        responsavel.setEmail("samuel@gmail.com");
        responsavel.setDataNascimento(LocalDate.now());
        return responsavel;
    }

    public ResponsavelDTO construirObjetoDTO() throws ParseException{
        return this.responsavelMapper.toDto(this.construirEntidade());
    }

    public ResponsavelDTO construirDTO() throws ParseException {
        return this.responsavelMapper.toDto(this.construir());
    }

    @Override
    protected Responsavel persistir(Responsavel entidade) {
        ResponsavelDTO responsavelDTO = responsavelService.salvar(responsavelMapper.toDto(entidade));
        return responsavelMapper.toEntity(responsavelDTO);
    }

    @Override
    protected Collection<Responsavel> obterTodos() {
        return null;
    }

    @Override
    protected Responsavel obterPorId(Integer id) {
        return null;
    }
}
