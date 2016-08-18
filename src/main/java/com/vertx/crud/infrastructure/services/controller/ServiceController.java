package com.vertx.crud.infrastructure.services.controller;

import com.vertx.crud.domain.entities.Entity;
import com.vertx.crud.domain.repository.Repository;
import com.vertx.crud.infrastructure.repositories.factory.RepositoryFactory;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by apolo on 18/08/2016.
 */
public class ServiceController {

    public static void insert(RoutingContext routingContext) {
        Entity entity =  Json.decodeValue(routingContext.getBodyAsString(),Entity.class);

        Repository repository = RepositoryFactory.getInstanceRepository();

        Entity entityRepository = repository.store(entity);

        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encode(entityRepository));
    }
}
