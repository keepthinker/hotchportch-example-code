package com.keepthinker.example.general.generic.test;

import java.io.Serializable;

public class Person implements Serializable, Comparable<Person> {
    private String personName;

    public String getPersonName() {
        return personName;
    }

    @Override
    public int compareTo(Person o) {
        return this.personName.compareTo(o.getPersonName());

    }
}
