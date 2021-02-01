package com.example.test;

import java.time.LocalDateTime;

/**
 * @author hnn
 * @date 2021/01/27
 */
public class VolitailTest implements Runnable {

    private boolean testFlag;
    private int count;

    public void stop() {
        testFlag = false;
        System.out.println(LocalDateTime.now() + " " + Thread.currentThread().getName());
    }

    public void start() {
        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            testFlag = true;
            System.out.println(LocalDateTime.now() + " " + Thread.currentThread().getName() + " " + testFlag);
        }).start();

    }

    @Override
    public void run() {
        while (true) {
            if (isTestFlag()) {
                System.out.println("-----------");
                break;
            }
        }
    }


    public void countPlus() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isTestFlag() {
        return testFlag;
    }

    public void setTestFlag(boolean testFlag) {
        this.testFlag = testFlag;
    }
}
