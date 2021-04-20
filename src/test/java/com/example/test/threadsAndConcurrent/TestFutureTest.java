package com.example.test.threadsAndConcurrent;

import com.example.test.TestFuture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

import static org.junit.Assert.*;

/**
 * 测试异步async
 *
 * @author hnn
 * @date 2021/02/01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestFutureTest {

    @Autowired
    private TestFuture testFuture;

    @Test
    public void longTime() throws Exception {
        System.out.println(Thread.currentThread().getName()+ " longTime test begin");
        testFuture.longTime();
        System.out.println(Thread.currentThread().getName()+ " longTime test end");
        Thread.sleep(1500);
    }

    @Test
    public void longTime2() throws Exception {
        System.out.println(Thread.currentThread().getName()+ " longTime2 test begin");
        Future<String> future = testFuture.longTime2();
        System.out.println(Thread.currentThread().getName()+ " longTime2 test end");
        System.out.println(future.get());
    }

    @Test
    public void add() {
        assertEquals(100, testFuture.add(50, 50));
    }

    @Test
    public void testFuture(){
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
    }
}