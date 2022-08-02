package com.keepthinker.example.general.java8;

public class SimpleCalcutor implements Calculator {

    @Override
    public int plus(int param1, int param2) {
        return param1 + param2;
    }

    // you don't have to implement default method minus from Calculator interface

    public String getName(){
        return Calculator.getInterfaceName();
    }

}
