package com.example.test;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author hnn
 * @date 2021/01/25
 */
public class ArrayTest {
    @Test
    public void testMutiArrays() {
        //测试多维数组
        int[][] mutiA = {
                {1, 2, 3},
                {4, 5, 6}
        };
        System.out.println(Arrays.deepToString(mutiA));
    }
}
