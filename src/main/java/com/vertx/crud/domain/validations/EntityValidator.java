package com.vertx.crud.domain.validations;

import com.vertx.crud.domain.entities.Entity;
import com.vertx.crud.domain.issues.Issue;
import com.vertx.crud.domain.issues.MandatoryFieldMissingIssue;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by alejandro on 30/08/2016.
 */
public class EntityValidator implements BaseValidator {

    @Override
    public List<Issue> validate(Entity entity) {
        List<Issue> issues = new ArrayList<Issue>();

        ValidatorFactory validatorFactory = Validation
                .buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator
                .validate(entity);

        for (ConstraintViolation<Object> violation : violations) {
            List<String> parametersIssue = new ArrayList<>();
            parametersIssue.add(violation.getPropertyPath().toString());
            Issue issue = new MandatoryFieldMissingIssue(parametersIssue);
            issues.add(issue);
        }
        return issues;
    }
}
