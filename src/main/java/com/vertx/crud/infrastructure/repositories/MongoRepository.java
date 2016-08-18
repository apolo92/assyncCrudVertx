package com.vertx.crud.infrastructure.repositories;

import com.vertx.crud.domain.entities.Entity;
import com.vertx.crud.domain.repository.Repository;

/**
 * Created by apolo on 18/08/2016.
 */
public class MongoRepository implements Repository {

    @Override
    public Entity store(Entity entity) {
        return insertInMongoDB(entity);
    }

    private Entity insertInMongoDB(Entity entity) {
        return null;
    }
}
