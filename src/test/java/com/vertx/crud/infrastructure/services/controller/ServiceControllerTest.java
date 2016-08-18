package com.vertx.crud.infrastructure.services.controller;

import io.vertx.ext.web.RoutingContext;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by apolo on 18/08/2016.
 */
public class ServiceControllerTest {

    @Test
    public void callRepositoryStore() {
        RoutingContext routing = mock(RoutingContext.class);

        ServiceController.insert(routing);

//        verify(repository).store(entity);

    }
}
