package com.keepthinker.example.general.generic.test;

import java.io.Serializable;
import java.util.List;

public class PersonMapper implements BaseMapper<Person, Long>, Serializable {
    @Override
    public Person getById(Long id) {
        return null;
    }

    public void log(List<?> list) {

    }

    public <T extends Number> void test(List<T> list1, List<? extends Comparable<T>> list2, T[] array, T item, String param5) {
    }


    public <T> void test2(List<T> list1, T[] array, T item, String param5) {
    }

    public void test3(String param) {
    }

}
