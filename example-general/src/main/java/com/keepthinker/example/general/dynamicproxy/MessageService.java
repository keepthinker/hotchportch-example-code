package com.keepthinker.example.general.dynamicproxy;

public interface MessageService {

    void sendMessage(String message, String target);

    void receiveMessage(String message, String target);

}
