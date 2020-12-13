package com.keepthinker.example.general.dynamicproxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("---------------------------print log before invoke method--------------------------");

        methodProxy.invokeSuper(o, objects);

        System.out.println("---------------------------print log after invoke method---------------------------");

        return null;
    }

}

