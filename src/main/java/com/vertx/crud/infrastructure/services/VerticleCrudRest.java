package com.vertx.crud.infrastructure.services;

import com.vertx.crud.infrastructure.services.controller.ServiceController;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;

/**
 * Created by apolo on 18/08/2016.
 */
public class VerticleCrudRest extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);

        router.route().handler(CorsHandler.create("*")
                .allowedMethod(HttpMethod.GET)
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.PUT)
                .allowedMethod(HttpMethod.DELETE)
                .allowedHeader("Content-Type")
                .allowedMethod(HttpMethod.OPTIONS));

        router.route().handler(BodyHandler.create());

        router.post("/crud/entity").handler(ServiceController::insert);


    }

    //TODO stop verticle
}
