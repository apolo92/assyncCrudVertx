package com.vertx.crud.domain.issues;

import java.io.Serializable;
import java.util.List;

/**
 * Created by alejandro on 30/08/2016.
 */
public class MandatoryFieldMissingIssue extends ErrorIssue implements Serializable{

    public MandatoryFieldMissingIssue(List<String> vars) {
        super(vars, "El campo {0} es obligatorio.");
    }
}
