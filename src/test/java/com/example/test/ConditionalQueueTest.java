package com.example.test;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author hnn
 * @date 2021/01/25
 */
public class ConditionalQueueTest {

    @Test
    public void testQueue() throws InterruptedException {
        ConditionalQueue<Integer> conditionalQueue = new ConditionalQueue<>(2);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    conditionalQueue.enqueue(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    int r = conditionalQueue.dequeue();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        Thread.sleep(10000);
    }

}