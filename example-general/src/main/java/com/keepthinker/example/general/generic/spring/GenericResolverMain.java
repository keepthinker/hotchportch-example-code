package com.keepthinker.example.general.generic.spring;


import org.springframework.core.GenericTypeResolver;
import org.springframework.core.ResolvableType;

import java.lang.reflect.Type;
import java.util.List;

public class GenericResolverMain {

    public static void main(String[] args) throws NoSuchMethodException {
        Object obj = new DualGenericBaseServiceImpl();

        Class returnType = GenericTypeResolver.resolveReturnType(obj.getClass().getMethod("say"), SingleGenericInterface.class);
        System.out.println("return type:" + returnType);

        returnType = GenericTypeResolver.resolveReturnTypeArgument(obj.getClass().getMethod("say"), returnType);
        System.out.println("return type argument:" + returnType);

        Class[] classes = GenericTypeResolver.resolveTypeArguments(obj.getClass(), DualGenericBaseService.class);
        for (Class clz : classes) {
            System.out.println("dual classes:" + clz);
        }

        Class clazz = GenericTypeResolver.resolveTypeArgument(obj.getClass(), SingleGenericInterface.class);
        System.out.println("single class:" + clazz);

        Type resolveType = GenericTypeResolver.resolveType(SingleGenericInterface.class.getMethod("say").getGenericReturnType(), DualGenericBaseServiceImpl.class);
        System.out.println("resolve type:" + resolveType);

    }
}
