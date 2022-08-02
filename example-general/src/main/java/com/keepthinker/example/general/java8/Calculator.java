package com.keepthinker.example.general.java8;

public interface Calculator {
    default int plus(int param1, int param2) {
        return param1 + param2;
    }

    default int minus(int param1, int param2) {
        return param1 - param2;
    }

    static String getInterfaceName() {
        return Calculator.class.getName();
    }
}
