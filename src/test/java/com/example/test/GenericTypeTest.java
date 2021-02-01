package com.example.test;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 泛型
 * @author hnn
 * @date 2021/02/01
 */
public class GenericTypeTest {

    /**
     * 继承后如何将泛型类型取出来
     */
    @Test
    public void testExtend(){
        //获取泛型参数
        Type t1= ChildClass.class.getGenericSuperclass();
        if(t1 instanceof ParameterizedType){
            ParameterizedType t2=(ParameterizedType)t1;
            Type[] ts= t2.getActualTypeArguments();
        }
        //仅仅获取父类
        Class c1= ChildClass.class.getSuperclass();
        System.out.println("");
    }
}
