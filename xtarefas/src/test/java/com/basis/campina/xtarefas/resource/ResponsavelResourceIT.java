package com.basis.campina.xtarefas.resource;

import com.basis.campina.xtarefas.domain.Responsavel;
import com.basis.campina.xtarefas.service.dto.ResponsavelDTO;
import com.basis.campina.xtarefas.service.elastic.ElasticSearchService;
import com.basis.campina.xtarefas.service.event.ResponsavelEvent;
import com.basis.campina.xtarefas.service.filter.ResponsavelFiltro;
import com.basis.campina.xtarefas.test.IntTestComum;
import com.basis.campina.xtarefas.test.builder.ResponsavelBuilder;
import com.basis.campina.xtarefas.util.TestUtil;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@RunWith(SpringRunner.class)
public class ResponsavelResourceIT extends IntTestComum {

    private static final String API_RESPONSAVEL = "/api/responsaveis";
    private static final String API_RESPONSAVEL_ID = API_RESPONSAVEL + "/{id}";
    private static final String LISTAR_RESPONSAVEL = API_RESPONSAVEL + "/search";

    @Autowired
    private ResponsavelBuilder responsavelBuilder;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @Test
    @DisplayName("Salvar Responsável com sucesso")
    public void salvarResponsavel() throws Exception {
        ResponsavelDTO responsavelDTO = responsavelBuilder.construirObjetoDTO();

        getMockMvc().perform(post(API_RESPONSAVEL).contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(com.basis.campina.xtarefas.util.TestUtil.convertObjectToJsonBytes(responsavelDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Buscar responsável por id com sucesso")
    public void buscarPorId() throws Exception {
        Responsavel responsavel = responsavelBuilder.construir();

        getMockMvc().perform(get(API_RESPONSAVEL_ID, responsavel.getId()).contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(com.basis.campina.xtarefas.util.TestUtil.convertObjectToJsonBytes(responsavel)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Editar responsável com sucesso")
    public void editarResponsavel() throws Exception {
        ResponsavelDTO responsavelDTO = responsavelBuilder.construirDTO();
        responsavelDTO.setNome("Responsavel nome editado");

        getMockMvc().perform(put(API_RESPONSAVEL_ID, responsavelDTO.getId()).contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(com.basis.campina.xtarefas.util.TestUtil.convertObjectToJsonBytes(responsavelDTO)))
                .andExpect(status().isOk());
    }


    @Test
    @Transactional
    @DisplayName("Listar Responsável com sucesso")
    public void listarResponsaveis() throws Exception {
        ResponsavelDTO responsavelDTO= this.responsavelBuilder.construirDTO();

        this.elasticSearchService.reindex();
        new ResponsavelEvent(responsavelDTO.getId());

        ResponsavelFiltro filtro = new ResponsavelFiltro();
        filtro.setQuery(responsavelDTO.getNome());

        getMockMvc().perform(post(LISTAR_RESPONSAVEL).contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(com.basis.campina.xtarefas.util.TestUtil.convertObjectToJsonBytes(filtro)))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @DisplayName("Excluír responsável com sucesso")
    public void excluirResponsavel() throws Exception {
        Responsavel responsavel = responsavelBuilder.construir();

        getMockMvc().perform(delete(API_RESPONSAVEL_ID, responsavel.getId()))
                .andExpect(status().isOk());
    }

}
