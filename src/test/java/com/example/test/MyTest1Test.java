package com.example.test;

import com.example.Springbootdemo2Application;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Springbootdemo2Application.class, MyTest1.class})
public class MyTest1Test {

    @Autowired
    private MyTest1 myTest1;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void add() {
        assertEquals(100, myTest1.add(50, 50));
    }

    @Test
    public void longTime() throws Exception {
        System.out.println("testSpringAsync begin");
        myTest1.longTime();
        System.out.println("testSpringAsync end");
        Thread.sleep(1500);
    }

    @Test
    public void longTime2() throws Exception {
        System.out.println("testlongTime2 begin");
        Future<String> future = myTest1.longTime2();
        System.out.println("testlongTime2 end");
        System.out.println(future.get());
    }
}