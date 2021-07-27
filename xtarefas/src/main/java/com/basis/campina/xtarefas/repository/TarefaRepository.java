package com.basis.campina.xtarefas.repository;

import com.basis.campina.xtarefas.domain.Tarefa;
import com.basis.campina.xtarefas.domain.document.TarefaDocument;
import com.basis.campina.xtarefas.service.dto.DominioFixoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
    @Query("SELECT NEW com.basis.campina.xtarefas.domain.document.TarefaDocument(t.id, t.nome, t.dataConclusao, t.dataInicio, t.status, t.responsavel.nome)"
            + " FROM Tarefa t WHERE  t.id = :id")
    TarefaDocument getDocument(@Param("id") Integer id);

    @Query(value = "SELECT NEW com.basis.campina.xtarefas.service.dto.DominioFixoDTO(t.id, t.nome)"
            + " FROM Tarefa t WHERE t.responsavel.id = :id")
    List<DominioFixoDTO> buscarTarefasPorNomes(@Param("id") Integer id);
}
