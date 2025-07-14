package com.keepthinker.example.general.generic.spring;

import java.util.List;

public class DualGenericBaseServiceImpl extends DualGenericBaseService<String, Integer>{

    @Override
    public Integer process(String param) {
        if (param == null) {
            return 0;
        }
        return param.hashCode();
    }

    @Override
    public List<String> say() {
        return List.of("hello");
    }
}
