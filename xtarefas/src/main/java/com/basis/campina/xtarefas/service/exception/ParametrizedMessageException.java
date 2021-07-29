package com.basis.campina.xtarefas.service.exception;

import java.util.Arrays;
import java.util.List;

public class ParametrizedMessageException extends RuntimeException {
    private final String code;
    private final List<String> parameter;
    private final String titleCode;
    private static final long serialVersionUID = 1L;

    public ParametrizedMessageException(String code, String titleCode, String... parameter) {
        this.code = code;
        this.titleCode = titleCode;
        this.parameter = Arrays.asList(parameter);
    }

    public ParametrizedMessageException(String code, String titleCode, Exception exception, String... parameter) {
        super(exception);
        this.code = code;
        this.titleCode = titleCode;
        this.parameter = Arrays.asList(parameter);
    }

    public String getCode() {
        return this.code;
    }

    public String getTitleCode() {
        return this.titleCode;
    }
}
