package com.keepthinker.example.netty.myproto.utils;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import io.netty.channel.Channel;

public class Utils {
    /**
     * private ip address(intranet) and port
     * @return
     */
    public static String getLocalAddress(Channel channel){
        InetSocketAddress address = (InetSocketAddress) channel.remoteAddress();
        InetAddress ip = address.getAddress();
        return ip.getHostAddress() + ":" + address.getPort();
    }
}
