package com.vertx.crud.infrastructure.services;

import com.vertx.crud.infrastructure.repositories.datasource.ConfigMongoDB;
import com.vertx.crud.infrastructure.services.controller.ServiceController;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.rxjava.core.Vertx;

/**
 * Created by apolo on 18/08/2016.
 */
public class VerticleCrudRest extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);

        //TODO MongoVerticle deploy and manage DB
//        vertx.deployVerticle("com.vertx.crud.infrastructure.repositories.MongoVerticle");

        ConfigMongoDB.getInstance().initMongoClient(Vertx.vertx());

        router.route().handler(CorsHandler.create("*")
                .allowedMethod(HttpMethod.GET)
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.PUT)
                .allowedMethod(HttpMethod.DELETE)
                .allowedHeader("Content-Type")
                .allowedMethod(HttpMethod.OPTIONS));

        router.route().handler(BodyHandler.create());

        router.post("/crud/:entity").handler(ServiceController::insert);

        router.get("/crud/:entity").handler(ServiceController::readAll);

        router.get("/crud/:entity/:id").handler(ServiceController::readOne);

        router.put("/crud/:entity/:id").handler(ServiceController::update);

        router.delete("/crud/:entity/:id").handler(ServiceController::delete);

        HttpServer server = vertx.createHttpServer();
        server.requestHandler(router::accept).listen(8081);
        System.out.println("Start Server! 8081");

    }

    //TODO stop verticle
}
