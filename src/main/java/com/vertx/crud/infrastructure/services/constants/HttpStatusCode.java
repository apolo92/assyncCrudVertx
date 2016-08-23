package com.vertx.crud.infrastructure.services.constants;

/**
 * Created by alejandro on 23/08/2016.
 */
public enum HttpStatusCode {
    ERROR_NOT_FOUND(404),
    ERROR_BAD_REQUEST(400),
    OK(200),
    OK_CREATE (201),
    OK_NO_CONTENT(204);
    private int statusCode;

    HttpStatusCode(int statusCode){
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}

