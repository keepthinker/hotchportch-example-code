package com.keepthinker.example.general.generic.test;

import java.io.Serializable;

public interface BaseMapper<T extends Serializable & Comparable<T>, K extends Serializable> {

    T getById(K id);

}


