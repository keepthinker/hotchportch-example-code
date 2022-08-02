package com.keepthinker.example.spring.lifecircle.zoo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Service;

@Service
public class Zookeeper implements Lifecycle {
    private static Logger logger = LoggerFactory.getLogger(Zookeeper.class);

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

    @Override
    public void start() {
        logger.info("Lifecycle.start()");
    }

    @Override
    public void stop() {
        logger.info("Lifecycle.stop()");
    }

    @Override
    public boolean isRunning() {
        logger.info("Lifecycle.isRunning()");
        return false;
    }
}
