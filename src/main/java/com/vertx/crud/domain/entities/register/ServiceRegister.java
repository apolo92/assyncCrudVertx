package com.vertx.crud.domain.entities.register;

import com.vertx.crud.domain.entities.Entity;
import com.vertx.crud.domain.exception.NotFoundServiceException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alejandro on 23/08/2016.
 */
public class ServiceRegister {

    private static Map<String, Class<?>> entityRegisterMapper = new HashMap<String, Class<?>>();
    private static List<String> listRegisterEntities = new ArrayList<String>();

    public static void registerService() {
        registerService("entities", Entity.class);
    }

    public static Class<?> getEntityClass(String entityEndpoint) {
        if (entityRegisterMapper.containsKey(entityEndpoint)) {
            return entityRegisterMapper.get(entityEndpoint);
        }
        throw new NotFoundServiceException();
    }

    public static void registerService(String entityName, Class<?> entityClass) {
        entityRegisterMapper.put(entityName, entityClass);
        listRegisterEntities.add(entityName);
    }

    public static List<String> getEntityRegisterList() {
        return listRegisterEntities;
    }

    public static String getEndPointRegister(Class<?> entityRegistred) {
        for (String key : entityRegisterMapper.keySet()) {
            if (entityRegisterMapper.get(key).equals(entityRegistred)) {
                return key;
            }
        }
        return null;
    }
}
