package com.keepthinker.example.spring.lifecircle;

import com.keepthinker.example.spring.lifecircle.zoo.Zookeeper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ComponentScan
public class LifeCircleMain {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Zookeeper zookeeper = context.getBean(Zookeeper.class);
        System.out.printf("zookeeper:%s", zookeeper.myDuty());
    }
}
