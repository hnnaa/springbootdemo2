package com.example.test;

import cn.hutool.core.collection.CollectionUtil;
import com.example.enums.ColorEnum;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author hnn
 * @date 2021/01/26
 */
public class CollectionTest {
    /**
     * 直接用enum定位到数组索引，不需要计算hashcode，效率高且没有空间浪费
     */
    @Test
    public void testEnumMap() {
        EnumMap<ColorEnum, String> enumMap = new EnumMap<>(ColorEnum.class);
        enumMap.put(ColorEnum.BLUE, "蓝色");
        enumMap.put(ColorEnum.YELLOW, "黄色");
        enumMap.put(ColorEnum.RED, "红色");
        System.out.println(enumMap);
        System.out.println("--------------");
        System.out.println(enumMap.get(ColorEnum.YELLOW));
    }

    @Test
    public void testProperty() throws Exception {
//        //Class.getResourceAsStream(name)  从当前包下去文件
//        InputStream is1= this.getClass().getResourceAsStream("test2.xml");
//        System.out.println("is1:"+is1);
//        ////Class.getResourceAsStream(/name) 有"/" 从classpath即src下去文件
//        InputStream is2= this.getClass().getResourceAsStream("/test.xml");
//        System.out.println("is2:"+is2);
//        //class().getclassLoader().getResorceAsstream(name)默认情况下是在src下去文件
//        InputStream is3=this.getClass().getClassLoader().getResourceAsStream("test.xml");
//        System.out.println("is3:"+is3);

        Properties properties = new Properties();
        properties.load(ThreadDead.class.getClassLoader().getResourceAsStream("test.properties"));
        String test = properties.getProperty("test");
        System.out.println(test);
    }

    @Test
    public void testQueue() {

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
