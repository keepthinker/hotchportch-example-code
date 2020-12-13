package com.keepthinker.example.general.dynamicproxy;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

public class ProxyFilter implements CallbackFilter {

    @Override
    public int accept(Method arg0) {
        if("receiveMessage".equalsIgnoreCase(arg0.getName())) {
            return 0;
        } else if("sendMessage".equals(arg0.getName())) {
            return 0;
        } else {
            return 1;
        }
    }
}

