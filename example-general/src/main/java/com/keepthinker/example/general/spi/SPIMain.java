package com.keepthinker.example.general.spi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SPIMain {
    private static final Logger logger = LoggerFactory.getLogger(SPIMain.class);
    public static void main(String[] args) {
        ServiceLoader<MoveAction> shouts = ServiceLoader.load(MoveAction.class);
        Iterator<MoveAction> it = shouts.iterator();
        while (it.hasNext()) {
            logger.info("before Iterator.next()");
            MoveAction action = it.next();
            logger.info("after Iterator.next()");
            action.move();
        }
    }
}