package com.example.test;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class MyTest1 {
    public int add(int a, int b) {
        return a + b;
    }

    @Async
    public void longTime() throws InterruptedException {
        System.out.println("longTime begin");
        Thread.sleep(1000);
        System.out.println("longTime end");
    }

    @Async
    public Future<String> longTime2() throws InterruptedException {
        System.out.println("longTime2 begin");
        Thread.sleep(1000);
        System.out.println("longTime2 end");
        return AsyncResult.forValue("yoyo");
    }


}
