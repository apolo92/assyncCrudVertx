package com.vertx.crud.domain.repository;

import com.vertx.crud.domain.entities.Entity;

/**
 * Created by apolo on 18/08/2016.
 */
public interface Repository {

    public Entity store( Entity entity);
}
