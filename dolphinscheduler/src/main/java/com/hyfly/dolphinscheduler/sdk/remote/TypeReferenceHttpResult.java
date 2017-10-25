package com.hyfly.dolphinscheduler.sdk.remote;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Type;

public class TypeReferenceHttpResult<T> extends TypeReference<HttpRestResult<T>> {

    protected final Type type;

    public TypeReferenceHttpResult(Class<?>... clazz) {
        ObjectMapper mapper = new ObjectMapper();
        type = mapper
                .getTypeFactory()
                .constructParametricType(HttpRestResult.class, clazz);
    }

    @Override
    public Type getType() {
        return type;
    }
}
