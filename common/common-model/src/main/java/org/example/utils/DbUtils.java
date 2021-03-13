package org.example.utils;

import org.example.model.db.events.BaseUpdated;

import java.lang.reflect.InvocationTargetException;

public class DbUtils {

    private DbUtils () {}

    public static <T extends BaseUpdated> T getEmptyEntity(Class<T> tClass, Long id) {
        if (id == null) {
            return null;
        }

        T entity;
        try {
            entity = tClass.getDeclaredConstructor().newInstance();
            entity.setId(id);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error");
        }
        return entity;
    }
}
