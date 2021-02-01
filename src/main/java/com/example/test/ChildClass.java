package com.example.test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * @author hnn
 * @date 2021/01/26
 */
public class ChildClass extends SupperClass<Integer> {

    public ChildClass(Integer integer) {
        super(integer);
    }


    public static void main(String[] args) {
        Type t1= ChildClass.class.getGenericSuperclass();
        if(t1 instanceof ParameterizedType){
            ParameterizedType t2=(ParameterizedType)t1;
            Type[] ts= t2.getActualTypeArguments();
        }
        Class c1= ChildClass.class.getSuperclass();
        System.out.println("");

    }
}
