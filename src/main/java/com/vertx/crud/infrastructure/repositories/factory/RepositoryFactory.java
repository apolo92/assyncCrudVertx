package com.vertx.crud.infrastructure.repositories.factory;

import com.vertx.crud.domain.repository.Repository;
import com.vertx.crud.infrastructure.repositories.MongoRepository;

/**
 * Created by apolo on 18/08/2016.
 */
public class RepositoryFactory {

    public static Repository getInstanceRepository(){
        return new MongoRepository();
    }
}
