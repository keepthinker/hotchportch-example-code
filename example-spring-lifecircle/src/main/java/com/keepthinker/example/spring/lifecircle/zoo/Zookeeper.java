package com.keepthinker.example.spring.lifecircle.zoo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class Zookeeper {

    private Tiger tiger;

    @Autowired
    @Required
    private void setTiger(Tiger tiger){
        this.tiger = tiger;
    }

    public String myDuty() {
        return String.format("I manage %s", tiger);
    }

    @Override
    public String toString() {
        return "Zookeeper{" +
                ", tiger=" + tiger +
                '}';
    }
}
