package com.vertx.crud.infrastructure.repositories.datasource;

import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.ext.mongo.MongoClient;

/**
 * Created by apolo on 19/08/2016.
 */
public class ConfigMongoDB {


    private static JsonObject config;
    private static ConfigMongoDB uniqueInstance;
    private MongoClient mongoClient;

    public static synchronized ConfigMongoDB getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ConfigMongoDB();
            config = new JsonObject();
        }
        return uniqueInstance;
    }

    public MongoClient getMongoClient(){
        return mongoClient;
    }

    private JsonObject getConfig() {
        config.put("host", "localhost");
        config.put("port", 32768);
        return config;
    }

    private ConfigMongoDB() {

    }


    public void initMongoClient(Vertx vertx) {
        mongoClient = MongoClient.createShared(vertx, getConfig());
    }
}
