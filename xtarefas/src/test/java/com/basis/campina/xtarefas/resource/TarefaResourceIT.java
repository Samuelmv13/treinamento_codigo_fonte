package com.basis.campina.xtarefas.resource;

import com.basis.campina.xtarefas.config.containers.ContainersFactory;
import com.basis.campina.xtarefas.domain.Tarefa;
import com.basis.campina.xtarefas.service.dto.TarefaDTO;
import com.basis.campina.xtarefas.service.elastic.ElasticSearchService;
import com.basis.campina.xtarefas.service.event.TarefaEvent;
import com.basis.campina.xtarefas.service.filter.TarefaFiltro;
import com.basis.campina.xtarefas.test.IntTestComum;
import com.basis.campina.xtarefas.test.builder.TarefaBuilder;
import com.basis.campina.xtarefas.util.TestUtil;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Container;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@RunWith(SpringRunner.class)
public class TarefaResourceIT extends IntTestComum {

    private static final String API_TAREFA = "/api/tarefas";
    private static final String API_TAREFA_ID = API_TAREFA + "/{id}";
    private static final String LISTAR_TAREFA = API_TAREFA + "/search";

    @Autowired
    private TarefaBuilder tarefaBuilder;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @Container
    private static ContainersFactory containersFactory = ContainersFactory.getInstance();

    @Test
    @DisplayName("Salvar Tarefa com sucesso")
    public void salvarTarefa() throws Exception {
        TarefaDTO tarefaDTO = tarefaBuilder.construirObjetoDTO();

        getMockMvc().perform(post(API_TAREFA).contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(com.basis.campina.xtarefas.util.TestUtil.convertObjectToJsonBytes(tarefaDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Buscar Tarefa por id com sucesso")
    public void buscarPorId() throws Exception {
        Tarefa tarefa = tarefaBuilder.construir();

        getMockMvc().perform(get(API_TAREFA_ID, tarefa.getId()).contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(com.basis.campina.xtarefas.util.TestUtil.convertObjectToJsonBytes(tarefa)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Editar tarefa com sucesso")
    public void editarTarefa() throws Exception {
        TarefaDTO tarefaDTO = tarefaBuilder.construirDTO();
        tarefaDTO.setNome("Tarefa nome editado");

        getMockMvc().perform(put(API_TAREFA_ID, tarefaDTO.getId()).contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(com.basis.campina.xtarefas.util.TestUtil.convertObjectToJsonBytes(tarefaDTO)))
                .andExpect(status().isOk());
    }


    @Test
    @Transactional
    @DisplayName("Listar Tarefa com sucesso")
    public void listarTarefas() throws Exception {
        TarefaDTO tarefaDTO= this.tarefaBuilder.construirDTO();

        this.elasticSearchService.reindex();
        new TarefaEvent(tarefaDTO.getId());

        TarefaFiltro filtro = new TarefaFiltro();
        filtro.setQuery(tarefaDTO.getNome());

        getMockMvc().perform(post(LISTAR_TAREFA).contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(com.basis.campina.xtarefas.util.TestUtil.convertObjectToJsonBytes(filtro)))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @DisplayName("Excluír responsável com sucesso")
    public void excluirTarefa() throws Exception {
        Tarefa tarefa = tarefaBuilder.construir();

        getMockMvc().perform(delete(API_TAREFA_ID, tarefa.getId()))
                .andExpect(status().isOk());
    }
}
