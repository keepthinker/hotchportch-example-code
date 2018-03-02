package com.keepthinker.example.general.network;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * Created by keepthinker on 2017/12/27.
 */
public class NetworkMain {
    public static void main(String[] args){
        try {
            System.out.println(Inet4Address.getByName("www.baidu.com"));
            System.out.println(Inet4Address.getByName("localhost"));
            System.out.println(Inet4Address.getByName("127.0.0.1"));
            System.out.println(Inet4Address.getByName("192.168.31.114"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
