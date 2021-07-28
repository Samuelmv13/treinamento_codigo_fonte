package com.basis.campina.xtarefas.service.filter;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DefaultFilter implements Serializable {

    private static final long serialVersionUID = 9104631289031180586L;

    protected String query;

    protected Boolean status;
}
