package com.basis.campina.xtarefas.repository;

import com.basis.campina.xtarefas.domain.Responsavel;
import com.basis.campina.xtarefas.domain.document.ResponsavelDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Integer> {

    @Query("SELECT NEW com.basis.campina.xtarefas.domain.document.ResponsavelDocument(r.id, r.nome, r.email, r.dataNascimento)"
            + " FROM Responsavel r WHERE  r.id = :id")
    ResponsavelDocument getDocument(@Param("id") Integer id);

    @Query("SELECT NEW com.basis.campina.xtarefas.domain.document.ResponsavelDocument(r.id, r.nome, r.email, r.dataNascimento)"
            + "FROM Responsavel r ORDER BY r.id")
    Page<ResponsavelDocument> reindexPage(Pageable pageable);

}
