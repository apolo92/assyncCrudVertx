package com.vertx.crud.infrastructure.services.controller;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.vertx.crud.domain.entities.Entity;
import com.vertx.crud.domain.entities.register.ServiceRegister;
import com.vertx.crud.domain.exception.NotFoundServiceException;
import com.vertx.crud.domain.issues.Issue;
import com.vertx.crud.domain.validations.BaseValidator;
import com.vertx.crud.domain.validations.EntityValidator;
import com.vertx.crud.infrastructure.services.constants.HttpStatusCode;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import java.util.List;

/**
 * Created by apolo on 18/08/2016.
 */
public class ServiceController {

    private static Class<?> entityClass;

    public static void verifyEntityExists(RoutingContext routingContext) {
        String entityName = routingContext.request().getParam("entity");

        try {
            entityClass = ServiceRegister.getEntityClass(entityName);
            routingContext.next();
        } catch (NotFoundServiceException e) {
            HttpServerResponse response = routingContext.response();
            response.setStatusCode(HttpStatusCode.ERROR_NOT_FOUND.getStatusCode());
            response.end();
        }
    }

    public static void discovery(RoutingContext routingContext) {
        List<String> listEntities = ServiceRegister.getEntityRegisterList();

        routingContext.response().end(Json.encode(listEntities));
    }

    public static void verifyEntity(RoutingContext routingContext) {

        Entity entityToInsert = (Entity) Json.decodeValue(routingContext.getBodyAsString(), entityClass);

        BaseValidator validator = new EntityValidator();

        HttpServerResponse response = routingContext.response();
        List<Issue> issues = validator.validate(entityToInsert);
        if (!issues.isEmpty()) {
            response.setStatusCode(HttpStatusCode.ERROR_BAD_REQUEST
                    .getStatusCode());
            Json.prettyMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            response.putHeader("content-type",
                    "application/json; charset=utf-8").end(
                    Json.encodePrettily(issues));
        } else
            routingContext.next();
    }
}
