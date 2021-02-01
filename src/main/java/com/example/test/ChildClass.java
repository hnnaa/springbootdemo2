package com.example.test;

/**
 * 泛型继承
 *
 * @author hnn
 * @date 2021/01/26
 */
public class ChildClass extends SupperClass<Integer> {

    public ChildClass(Integer integer) {
        super(integer);
    }
}
