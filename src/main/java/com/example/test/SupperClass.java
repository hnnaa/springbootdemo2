package com.example.test;

/**
 * @author hnn
 * @date 2021/01/26
 */
public class SupperClass<T> {
    private T t;

    public SupperClass(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }
}
