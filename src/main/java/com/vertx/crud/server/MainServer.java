package com.vertx.crud.server;

import io.vertx.core.Vertx;

/**
 * Created by apolo on 18/08/2016.
 */
public class MainServer {

    public static void main(String[] args) throws Exception {
        Vertx.vertx().deployVerticle("com.vertx.crud.infrastructure.services.VerticleCrudRest");
    }
}
