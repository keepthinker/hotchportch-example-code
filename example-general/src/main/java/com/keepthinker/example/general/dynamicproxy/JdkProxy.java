package com.keepthinker.example.general.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JdkProxy implements InvocationHandler {

    private MessageService messageService;

    public JdkProxy(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        System.out.println("print log before: " + method.getName());
        if("sendMessage".equals(method.getName())) {
            messageService.sendMessage((String)args[0], (String)args[1]);
        } else if("receiveMessage".equals(method.getName())) {
            messageService.sendMessage((String)args[0], (String)args[1]);
        }
        System.out.println("print log after: " + method.getName());
        return null;
    }

}
