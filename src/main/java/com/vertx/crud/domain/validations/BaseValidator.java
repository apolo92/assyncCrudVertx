package com.vertx.crud.domain.validations;

import com.vertx.crud.domain.entities.Entity;
import com.vertx.crud.domain.issues.Issue;

import java.util.List;

/**
 * Created by alejandro on 30/08/2016.
 */
public interface BaseValidator {

    public List<Issue> validate(Entity entityToInsert);
}
