package com.example.test.threadsAndConcurrent;

import com.example.test.TestFuture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

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
}