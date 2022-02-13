package com.keepthinker.example.general.spi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bird implements MoveAction{
    private static final Logger logger = LoggerFactory.getLogger(Bird.class);

    public Bird(){
        logger.info("initialized");
    }

    @Override
    public void move() {
        logger.info("I am flying");
    }
}
