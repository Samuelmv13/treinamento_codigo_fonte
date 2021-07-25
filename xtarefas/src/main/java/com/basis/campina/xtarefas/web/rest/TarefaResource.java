package com.basis.campina.xtarefas.web.rest;

import com.basis.campina.xtarefas.service.TarefaService;
import com.basis.campina.xtarefas.service.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
@RequiredArgsConstructor
@Slf4j
public class TarefaResource {

    private final TarefaService tarefaService;

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> listarTodos() {
        return ResponseEntity.ok(tarefaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(tarefaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<TarefaDTO> inserir(@RequestBody TarefaDTO tarefaDTO) throws URISyntaxException {
        TarefaDTO dto = tarefaService.salvar(tarefaDTO);
        return ResponseEntity.created(new URI("/api/tarefas")).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaDTO> atualizar(@PathVariable Integer id, @RequestBody TarefaDTO tarefaDTO) {
        tarefaDTO.setId(id);
        TarefaDTO dto = tarefaService.salvar(tarefaDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TarefaDTO> deletar(@PathVariable Integer id) {

        tarefaService.remover(id);
        return ResponseEntity.ok().build();
    }
}
