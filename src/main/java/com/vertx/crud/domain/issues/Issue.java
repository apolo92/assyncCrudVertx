package com.vertx.crud.domain.issues;

import java.util.List;

/**
 * Created by alejandro on 30/08/2016.
 */
public class Issue {

    private String type;
    private List<String> vars;
    private String msg;

    public Issue(String type, List<String> vars, String msg) {
        this.type = type;
        this.vars = vars;
        this.msg = msg;
    }
}
