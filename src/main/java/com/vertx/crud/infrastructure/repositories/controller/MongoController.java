package com.vertx.crud.infrastructure.repositories.controller;

import com.vertx.crud.infrastructure.repositories.datasource.ConfigMongoDB;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.rxjava.ext.mongo.MongoClient;

/**
 * Created by apolo on 19/08/2016.
 */
public class MongoController {

    public static void insert(RoutingContext routingContext) {
        JsonObject requestBody = routingContext.getBodyAsJsonArray().getJsonObject(0);
        MongoClient mongoClient = ConfigMongoDB.getInstance().getMongoClient();

        HttpServerResponse response = routingContext.response();

        mongoClient.insert(requestBody.getString("table"), requestBody, insert -> {
            if (insert.succeeded()) {
                response.end(insert.result());
            } else {
                response.setStatusCode(500).end(insert.cause().getMessage());
            }
        });
    }
}
