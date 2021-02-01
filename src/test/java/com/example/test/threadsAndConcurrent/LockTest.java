package com.example.test.threadsAndConcurrent;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.example.test.ReentrantLockTest;
import com.example.test.SpinLock;
import com.example.test.ThreadDead;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hnn
 * @date 2021/02/01
 */
public class LockTest {

    @Test
    public void testSpinLock() throws InterruptedException {
        Lock lock = new SpinLock();
        System.out.println("start:" + LocalDateTimeUtil.now());
        Thread t1 = new Thread(() -> ReentrantLockTest.doWork(lock), "threadA");
        Thread t2 = new Thread(() -> ReentrantLockTest.doWork(lock), "threadB");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    @Test
    public void testReentrantLock() {
        Lock lock = new ReentrantLock();
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> ReentrantLockTest.doWork(lock), "thread" + i);
            thread.start();
            threadList.add(thread);
        }

        threadList.forEach(m -> {
            try {
                m.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 等待可中断
     */
    @Test
    public void testInterruptLock() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        Thread t1 = new Thread(new ThreadDead(lock, lock2), "threadA");
        Thread t2 = new Thread(new ThreadDead(lock2, lock), "threadB");
        t1.start();
        t2.start();
        Thread.sleep(300);
        t1.interrupt();
        t2.join();
    }
}
