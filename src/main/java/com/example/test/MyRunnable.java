package com.example.test;

/**
 * volatile可见性
 *
 * @author hnn
 * @date 2021/01/27
 */
public class MyRunnable implements Runnable {
    private boolean flag = false;
    private int count;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("flag=" + isFlag());
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public synchronized void countPlus() {
        count++;
    }
}
