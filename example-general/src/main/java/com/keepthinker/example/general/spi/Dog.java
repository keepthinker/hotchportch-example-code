package com.keepthinker.example.general.spi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dog implements MoveAction{

    private static final Logger logger = LoggerFactory.getLogger(Dog.class);

    public Dog(){
        logger.info("initialized");
    }

    @Override
    public void move() {
        logger.info("I am running");
    }
}
