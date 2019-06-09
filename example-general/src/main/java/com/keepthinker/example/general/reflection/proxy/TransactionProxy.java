package com.keepthinker.example.general.reflection.proxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TransactionProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("start transaction");
        methodProxy.invokeSuper(o, objects);
        System.out.println("end transaction");

        return null;
    }
}
