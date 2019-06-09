package com.keepthinker.example.general.reflection.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

public class CGLibProxyMain {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserDao.class);
        MethodInterceptor methodInterceptor = new TransactionProxy();
        enhancer.setCallback(methodInterceptor);

        // 生成代理类并实例化
        UserDao proxy = (UserDao) enhancer.create();

        // 用代理类调用方法
        proxy.insert();
    }
}
