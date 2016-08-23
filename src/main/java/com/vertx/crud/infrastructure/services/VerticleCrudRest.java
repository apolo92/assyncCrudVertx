package com.vertx.crud.infrastructure.services;

import com.vertx.crud.domain.entities.register.ServiceRegister;
import com.vertx.crud.infrastructure.repositories.controller.MongoController;
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

        ConfigMongoDB.getInstance().initMongoClient(Vertx.vertx());

        HttpServer server = vertx.createHttpServer();

        ServiceRegister.registerService();

        router.route().handler(CorsHandler.create("*")
                .allowedMethod(HttpMethod.GET)
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.PUT)
                .allowedMethod(HttpMethod.DELETE)
                .allowedHeader("Content-Type")
                .allowedMethod(HttpMethod.OPTIONS));

        router.route().handler(BodyHandler.create());

        router.get("/crud/:entity").handler(ServiceController::verifyEntityExists);
        router.get("/crud/:entity").handler(MongoController::readAll);

        router.post("/crud/:entity").handler(ServiceController::verifyEntityExists);
//        router.post("/crud/:entity").handler(ServiceController::verifyEntity);
        router.post("/crud/:entity").handler(MongoController::insert);

        router.get("/crud/:entity/:id").handler(ServiceController::verifyEntityExists);
        router.get("/crud/:entity/:id").handler(MongoController::readOne);

        router.delete("/crud/:entity/:id").handler(ServiceController::verifyEntityExists);
        router.delete("/crud/:entity/:id").handler(MongoController::delete);

        router.put("/crud/:entity/:id").handler(ServiceController::verifyEntityExists);
//        router.put("/crud/:entity/:id").handler(ServiceController::verifyEntity);
        router.put("/crud/:entity/:id").handler(MongoController::update);

        router.get("/schema/:entity").handler(ServiceController::verifyEntityExists);
//        router.get("/schema/:entity").handler(MongoController::schema);

        router.get("/discovery/entities/").handler(ServiceController::discovery);

        server.requestHandler(router::accept).listen(8081);
        System.out.println("Start Server!");

    }

    //TODO stop verticle
}
