package com.keepthinker.example.general.network;

import cn.hutool.http.HttpUtil;

public class HutoolHttpMain {
    public static void main(String[] args) {

        String resp = HttpUtil.post("http://localhost:8080/123", "{\"name\": \"my-name\"}",  10000);
        System.out.println("response: " + resp);
    }
}
