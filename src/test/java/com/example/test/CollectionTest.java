package com.example.test;

import cn.hutool.core.collection.CollectionUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author hnn
 * @date 2021/01/26
 */
public class CollectionTest {
    @Test
    public void testQueue() {

    }

    @Test
    public void testStack() {
        System.out.println(MyStack.toHex(15));
    }

    @Test
    public void testCollections() {
        //空集合
        List<String> emptyList = Collections.emptyList();
        //单元素集合
        List<String> list = Collections.singletonList("d");

        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            integers.add(i);
        }
        //排序
        Collections.sort(integers);
        //洗牌
        Collections.shuffle(integers);
        //不可变集合
        List<String> listA = new ArrayList<>();
        listA.add("1");
        listA.add("2");
        List<String> listB = Collections.unmodifiableList(listA);

        System.out.println(integers);
    }
}
