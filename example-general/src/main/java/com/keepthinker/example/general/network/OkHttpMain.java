package com.keepthinker.example.general.network;

import kotlin.Pair;
import okhttp3.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.Iterator;

public class OkHttpMain {

    private static OkHttpClient client = new OkHttpClient(
            new OkHttpClient.Builder()
                    .connectTimeout(Duration.ofSeconds(3))
                    .readTimeout(Duration.ofSeconds(3))
                    .writeTimeout(Duration.ofSeconds(3)));


    public static void main(String[] args) {
        get();
        post();
    }

    public static void get() {

        Request request = new Request.Builder()
                .url("http://localhost:8080/phone")
                .get()
                .build();

        try (Response resp = client.newCall(request).execute()) {
            Headers headers = resp.headers();
            Iterator<Pair<String, String>> iterator = headers.iterator();
            System.out.println("response headers");
            while(iterator.hasNext()) {
                Pair<String, String> pair = iterator.next();
                System.out.printf("%s --> %s\n", pair.getFirst(), pair.getSecond());
            }
            System.out.println();
            System.out.format("body: %s\n", resp.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void post(){

        Request request = new Request.Builder()
                .url("http://localhost:8080/http-detail")
                .header("Content-Type", "application/json;charset=utf-8")
//                .post(RequestBody.create("{\"name\": \"my-name\"}", MediaType.get("application/json")))
                .post(RequestBody.create("{\"name\": \"my-name\"}".getBytes(Charset.forName("utf-8"))))
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
