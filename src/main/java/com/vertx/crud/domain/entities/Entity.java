package com.vertx.crud.domain.entities;

import javax.validation.constraints.NotNull;

/**
 * Created by apolo on 18/08/2016.
 */
public class Entity {

    @NotNull
    private Long id;

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
