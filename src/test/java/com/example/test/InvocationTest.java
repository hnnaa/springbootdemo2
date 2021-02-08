package com.example.test;

import com.example.interfaces.Hello;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 *
 * @author hnn
 * @date 2021/01/25
 */
public class InvocationTest {

    @Test
    public void testInvocation() {
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("hi " + method.getName());
                return null;
            }
        };

        Hello px = (Hello) Proxy.newProxyInstance(Hello.class.getClassLoader(), new Class[]{Hello.class}, invocationHandler);
        px.morning();
    }
}
