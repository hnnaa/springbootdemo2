package com.example;

import org.junit.Test;
import org.springframework.scheduling.annotation.Async;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class AATest {
    @Test
    public void tempTest() throws Exception {
        System.out.println("begin test");
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> result = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("begin thread " + Thread.currentThread().getId());
                Thread.sleep(1000);
                System.out.println("begin end " + Thread.currentThread().getId());
                return "yo";
            }
        });
        System.out.println("after execute");
        System.out.println("result=" + result.get());
        System.out.println("end test");
    }

    @Test
    public void tempTest2() throws Exception {
        System.out.println("begin test");
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        CompletableFuture<String> result = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                System.out.println("begin thread " + Thread.currentThread().getId());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("begin end " + Thread.currentThread().getId());
                return "yo";
            }
        }, executorService);

        System.out.println("after execute");
        result.thenAccept(e -> {
            System.out.println("result=" + e);
        });
        System.out.println("end test");

        Thread.sleep(2000);
    }
}
