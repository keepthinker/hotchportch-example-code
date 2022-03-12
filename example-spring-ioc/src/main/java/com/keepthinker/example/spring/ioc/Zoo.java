package com.keepthinker.example.spring.ioc;

import com.keepthinker.example.spring.ioc.model.Panda;
import com.keepthinker.example.spring.ioc.model.Tiger;
import org.springframework.beans.factory.annotation.Autowired;

public class Zoo {
    @Autowired
    private Tiger tiger;

    private Panda panda;

    @Autowired
    private void setPanda(Panda panda){
        this.panda = panda;
    }
}
