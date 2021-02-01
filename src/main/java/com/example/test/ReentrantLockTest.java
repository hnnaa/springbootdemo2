package com.example.test;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hnn
 * @date 2021/01/25
 */
public class ReentrantLockTest {
    private final static Lock lock = new SpinLock();
    private final static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        System.out.println("start:" + LocalDateTimeUtil.now());
//        Thread t1 = new Thread(new ThreadDead(lock, lock2), "threadA");
//        Thread t2 = new Thread(new ThreadDead(lock2, lock), "threadB");
//        t1.start();
//        t2.start();
//        t1.interrupt();
        new Thread(() -> doWork(), "threadA").start();
        new Thread(() -> doWork(), "threadB").start();

//        new Thread(() -> doWork(), "threadC").start();
//        new Thread(() -> doWork(), "threadD").start();
//        new Thread(() -> doWork(), "threadE").start();
    }

    public static void doWork() {
        int i = 1;
        while (i > 0) {
            lock.lock();
            try {
                System.out.println("thread:" + Thread.currentThread().getName() + "获得锁");
                Thread.sleep(10);
            } catch (Exception e) {

            } finally {
                lock.unlock();
                System.out.println(LocalDateTimeUtil.now() + " thread:" + Thread.currentThread().getName() + "释放锁");
            }
            i--;
        }
    }
}
