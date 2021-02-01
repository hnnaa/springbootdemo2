package com.example.test;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Properties;
import java.util.Queue;
import java.util.StringJoiner;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author hnn
 * @date 2021/01/25
 */
public class StringTest {

    @Test
    public void testStringJoiner() {
        String[] names = {"jack", "bob", "lina"};
        StringJoiner sj = new StringJoiner(",", "Hello ", "!");
        Arrays.stream(names).forEach(sj::add);
        System.out.println(sj.toString());
    }

    @Test
    public void testBigDecimal() {
        BigDecimal bd1 = new BigDecimal("123.23");
        BigDecimal bd2 = new BigDecimal("0.22");
        BigDecimal bd3 = bd1.divide(bd2, 5, RoundingMode.HALF_UP);
        System.out.println(bd3);
        BigDecimal[] bb = bd1.divideAndRemainder(bd2);
        System.out.println(Arrays.toString(bb));
    }

    @Test
    public void testMath() {
        System.out.println(Math.log10(100000));
        System.out.println(Math.log(100000));
        System.out.println(Math.asin(1.0 / 2) / Math.PI);
    }

    @Test
    public void testRandom() {
        System.out.println(new SecureRandom().nextInt());
    }

    @Test
    public void testProperty() throws Exception {
        Properties properties = new Properties();
        properties.load(ThreadDead.class.getResourceAsStream("/test.properties"));
        String test = properties.getProperty("test");
        System.out.println(test);
    }

}
