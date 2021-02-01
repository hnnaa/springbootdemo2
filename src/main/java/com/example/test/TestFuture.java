package com.example.test;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class TestFuture {
    public int add(int a, int b) {
        return a + b;
    }

    @Async
    public void longTime() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+ " longTime begin");
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName()+ " longTime end");
    }

    @Async
    public Future<String> longTime2() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+ " longTime2 begin");
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName()+ " longTime2 end");
        return AsyncResult.forValue("yoyo");
    }
}
