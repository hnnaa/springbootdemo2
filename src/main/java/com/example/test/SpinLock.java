package com.example.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自旋锁
 *
 * @author hnn
 * @date 2021/01/25
 */
public class SpinLock implements Lock {
    private AtomicReference<Thread> owner = new AtomicReference<>();

    private int count = 0;

    @Override
    public void lock() {
        Thread t = Thread.currentThread();
        if (t == owner.get()) {
            ++count;
            return;
        }
        while (!owner.compareAndSet(null, t)) {
            System.out.println(t.getName() + " 自旋");
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        Thread t = Thread.currentThread();
        //only the owner could do unlock;
        if (t == owner.get()) {
            if (count > 0) {
                // reentrant count not zero, just decrease the counter.
                --count;
            } else {
                // compareAndSet is not need here, already checked
                owner.set(null);
            }
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
