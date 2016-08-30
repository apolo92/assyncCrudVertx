package com.vertx.crud.domain.issues;

import java.util.List;

/**
 * Created by alejandro on 30/08/2016.
 */
public class ErrorIssue extends Issue {

    public ErrorIssue(List<String> vars, String msg) {
        super("Error", vars, msg);
    }
}
