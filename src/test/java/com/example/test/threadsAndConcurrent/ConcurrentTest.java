package com.example.test.threadsAndConcurrent;

import com.example.test.MyRunnable;
import org.junit.Test;

/**
 * @author hnn
 * @date 2021/01/25
 */
public class ConcurrentTest {

    @Test
    public void testSync() {

    }

    /**
     * flag加volatile 可以实现线程间可见
     */
    @Test
    public void testVolatile() {
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
}
