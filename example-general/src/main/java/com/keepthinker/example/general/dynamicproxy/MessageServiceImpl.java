package com.keepthinker.example.general.dynamicproxy;

public class MessageServiceImpl implements MessageService{

    public void sendMessage(String message, String target){
        System.out.println("send message " + message + " to " + target);
    }

    public void receiveMessage(String message, String target){
        System.out.println("receive message " + message + " from " + target);
    }
}
