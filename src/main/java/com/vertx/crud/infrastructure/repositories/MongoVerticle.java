package com.vertx.crud.infrastructure.repositories;

import com.vertx.crud.infrastructure.repositories.datasource.ConfigMongoDB;
import com.vertx.crud.infrastructure.repositories.controller.MongoController;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.rxjava.core.Vertx;

/**
 * Created by apolo on 18/08/2016.
 */
public class MongoVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        ConfigMongoDB.getInstance().initMongoClient(Vertx.vertx());

        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());

        router.post("/storeMongoDB").handler(MongoController::insert);

        HttpServer server = vertx.createHttpServer();
        server.requestHandler(router::accept).listen(8082);
        System.out.println("Start Server! 8081");
    }
}
