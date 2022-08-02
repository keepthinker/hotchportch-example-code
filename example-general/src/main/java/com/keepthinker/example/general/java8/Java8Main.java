package com.keepthinker.example.general.java8;

public class Java8Main {
    public static void main(String[] args) {
        Pluser pluser = Integer::sum;
        System.out.println(pluser.plus(1, 2));

    }
}
