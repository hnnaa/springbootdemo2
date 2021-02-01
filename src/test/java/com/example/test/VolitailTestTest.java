package com.example.test;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * @author hnn
 * @date 2021/01/27
 */
public class VolitailTestTest {

    @Test
    public void test1() {
        MyRunnable td = new MyRunnable();
        new Thread(td).start();
        while (true) {
            if (td.isFlag()) {
                System.out.println("-----------");
                break;
            }
            td.countPlus();
        }
    }

    @Test
    public void test2() {
        VolitailTest volitailTest = new VolitailTest();
        volitailTest.start();
        new Thread(volitailTest).start();
        while (true) {
            if(volitailTest.isTestFlag()) {
                 System.out.println(Thread.currentThread().getName()+" " );
                 break;
            }
        }
    }

    @Test
    public void testCount() {
        VolitailTest volitailTest = new VolitailTest();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> volitailTest.countPlus()).start();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(volitailTest.getCount());
    }
}