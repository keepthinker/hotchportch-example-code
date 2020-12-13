package com.keepthinker.example.general.dynamicproxy;

import java.lang.reflect.Proxy;

public class JdkProxyMain {
    public static void main(String[] args) {

        MessageService messageService = (MessageService)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), MessageServiceImpl.class.getInterfaces(), new JdkProxy(new MessageServiceImpl()));
        messageService.sendMessage("hello", "Jane");

        messageService.receiveMessage("hello", "kate");
    }
}
