package com.basis.campina.xtarefas.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AnexoDTO implements Serializable {

    private static final long serialVersionUID = 487749103618080019L;

    private Integer id;

    private String file;

    private String fileName;

    private Integer idTarefa;

    private DocumentoDTO documentoDTO;
}
