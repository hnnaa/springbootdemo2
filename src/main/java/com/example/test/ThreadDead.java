package com.example.test;

import com.example.annotation.Report;

import java.util.concurrent.locks.Lock;

/**
 * 死锁
 *
 * @author hnn
 * @date 2021/01/25
 */
public class ThreadDead implements Runnable {
    private Lock lock1;
    private Lock lock2;

    public ThreadDead(Lock lock1, Lock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Report(name = "run1", value = 1)
    @Override
    public void run() {
        try {
            System.out.println("thread:" + Thread.currentThread().getName() + "获得锁1");
            this.lock1.lockInterruptibly();
            Thread.sleep(300);
            System.out.println("thread:" + Thread.currentThread().getName() + "获得锁2");
            this.lock2.lockInterruptibly();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.lock1.unlock();
            this.lock2.unlock();
            System.out.println("thread:" + Thread.currentThread().getName() + "释放锁");
        }
    }
}
