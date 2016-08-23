package com.vertx.crud.infrastructure.repositories.controller;

import com.vertx.crud.infrastructure.repositories.datasource.ConfigMongoDB;
import io.vertx.core.AsyncResult;
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
        JsonObject requestBody = new JsonObject(routingContext.getBodyAsString());

        MongoClient mongoClient = getMongoClient();

        HttpServerResponse response = routingContext.response();

        mongoClient.insert(routingContext.request().getParam("entity"), requestBody, insertHandler -> {
            if (insertHandler.succeeded()) {
                JsonObject result = jsonResponseId("id", insertHandler.result());
                response.end(result.encodePrettily());
            } else {
//                TODO loggerError insert.cause().getMessage()
                response.setStatusCode(500).end("Error al insertar");
            }
        });
    }

    public static void readAll(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();

        JsonObject query = new JsonObject();

        getMongoDB(routingContext.request().getParam("entity"), response, query);
    }

    public static void readOne(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();

        JsonObject query = getQueryID(routingContext);

        getMongoDB(routingContext.request().getParam("entity"), response, query);
    }

    public static void update(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        MongoClient mongoClient = getMongoClient();

        JsonObject query = getQueryID(routingContext);

        JsonObject queryUpdate = jsonResponseId("$set", routingContext.getBodyAsString());

        JsonObject jsonresponse = jsonResponseId("id", routingContext.request().getParam("id"));

        mongoClient.update(routingContext.request().getParam("entity"), query, queryUpdate, updateHandler -> {
            responseEnd(response, updateHandler,jsonresponse.encodePrettily(),"Error al modificar");
        });

    }

    public static void delete(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        MongoClient client = getMongoClient();

        JsonObject query = getQueryID(routingContext);

        String result = jsonResponseId("id",routingContext.request().getParam("id")).encodePrettily();

        client.remove(routingContext.request().getParam("entity"),query,deleteHandler->{
            responseEnd(response, deleteHandler,result,"Error al modificar");
        });
    }

    private static MongoClient getMongoClient() {
        return ConfigMongoDB.getInstance().getMongoClient();
    }

    private static JsonObject jsonResponseId(String idKey, String idValue) {
        JsonObject jsonresponse = new JsonObject();
        jsonresponse.put(idKey, idValue);
        return jsonresponse;
    }

    private static void responseEnd(HttpServerResponse response, AsyncResult updateHandler, String MessageOk, String MessageError) {
        if (updateHandler.succeeded()) {
            response.end(MessageOk);
        } else {
//                TODO loggerError insert.cause().getMessage()
            response.setStatusCode(500).end(MessageError);
        }
    }

    private static JsonObject getQueryID(RoutingContext routingContext) {
        return jsonResponseId("_id", routingContext.request().getParam("id"));
    }

    private static void getMongoDB(String table, HttpServerResponse response, JsonObject query) {
        MongoClient mongoClient = getMongoClient();

        mongoClient.find(table, query, getHandler -> {
            if (getHandler.succeeded()) {
                JsonArray array = new JsonArray();
                for (JsonObject json : getHandler.result()) {
                    array.add(json);
                }
                response.end(array.encodePrettily());
            } else {
//                TODO loggerError insert.cause().getMessage()
                response.setStatusCode(500).end("Error al leer");
            }
        });
    }
}
