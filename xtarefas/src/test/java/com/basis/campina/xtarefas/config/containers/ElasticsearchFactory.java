package com.basis.campina.xtarefas.config.containers;

import org.testcontainers.junit.jupiter.Container;

import java.util.Objects;

public class ElasticsearchFactory {

    @Container
    private static CustomElasticContainer customElasticContainer;

    public static CustomElasticContainer getInstance(){
        if(Objects.isNull(customElasticContainer)){
            customElasticContainer = new CustomElasticContainer();
            customElasticContainer.start();
        }
        return customElasticContainer;
    }

}