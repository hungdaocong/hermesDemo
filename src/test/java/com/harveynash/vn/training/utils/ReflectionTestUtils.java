package com.harveynash.vn.training.utils;

import java.lang.reflect.Field;

import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

public final class ReflectionTestUtils {

    private ReflectionTestUtils() {
    }

    public static void setField(Object target, String name, Object value) {
        setField(target, name, value, null);
    }

    public static void setField(Object target, String name, Object value, Class<?> type) {
        Assert.notNull(target, "Target object must not be null");
        Field field = ReflectionUtils.findField(target.getClass(), name, type);

        if (field == null) {
            throw new IllegalArgumentException(String.format("Could not find field [%s] of type [%s] on target [%s]", name, type, target));
        }

        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field, target, value);
    }
}
