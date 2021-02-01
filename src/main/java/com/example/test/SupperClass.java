package com.example.test;

/**
 * 泛型父类
 *
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
