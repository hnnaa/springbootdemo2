package com.example.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hnn
 * @date 2021/01/25
 */
public class ConditionalQueue<E> {
    int size;//阻塞队列最大容量

    ReentrantLock lock = new ReentrantLock();

    private LinkedList<E> list = new LinkedList<>();

    private Condition conEmpty = lock.newCondition();
    private Condition conFull = lock.newCondition();

    public ConditionalQueue(int size) {
        this.size = size;
    }

    public void enqueue(E e) throws Exception {
        lock.lock();
        try {
            while (size == list.size()) {
                conFull.await();
            }
            System.out.println("入队：" + e);
            list.add(e);
            conEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public E dequeue() throws InterruptedException {
        E e;
        lock.lock();
        try {
            while (list.size() == 0) {
                conEmpty.await();
            }
            e = list.removeFirst();
            System.out.println("出队:" + e);
            conFull.signal();
            return e;
        } finally {
            lock.unlock();
        }
    }
}
