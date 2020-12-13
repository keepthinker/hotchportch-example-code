package com.keepthinker.example.general.dynamicproxy;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class CglibProxyMain {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MessageServiceImpl.class); //设置被代理的类
        enhancer.setCallbacks(new Callback[]{new CglibProxy(), NoOp.INSTANCE}); //根据SubClass中的实现对方法进行增强
        enhancer.setCallbackFilter(new ProxyFilter()); //使用过滤器
        MessageServiceImpl messageService = (MessageServiceImpl)enhancer.create();

        messageService.sendMessage("Hello", "ken");

        //使用enhancer产生的代理类不需要修改SuperClass类中的代码，就可以对bye()进行增强
        messageService.receiveMessage("Howdy", "Mary");
    }

}
