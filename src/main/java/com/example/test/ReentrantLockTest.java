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
    public static void doWork(Lock lock) {
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
