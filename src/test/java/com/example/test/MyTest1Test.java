package com.example.test;

import com.example.Springbootdemo2Application;
import com.example.pojo.InitDestroyBean;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Springbootdemo2Application.class, MyTest1.class})
public class MyTest1Test {

    private Logger log = LoggerFactory.getLogger(MyTest1Test.class);
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private MyTest1 myTest1;

    @Autowired
    private InitDestroyBean initDestroyBean;

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
        log.info("testlongTime2 begin");
        Future<String> future = myTest1.longTime2();
        log.info("testlongTime2 end");
        log.info(future.get());
    }

    @Test
    public void test() throws Exception {
        StringUtils.isBlank("");
        StringUtils.length("");
        StringUtils.leftPad("abc", 9, " ");
        System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        Date dt = DateUtils.parseDate("2020-01-01 00:00:01", "yyyy-MM-dd HH:mm:ss");
        //注解反射
        Method method = TestAnnotation.class.getMethod("sayHello", null);
        Hello hello = method.getAnnotation(Hello.class);
        System.out.println(hello.value());

        //异步
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "jojo";
            }
        });
        System.out.println(completableFuture.get());
    }

    @Target(value = {ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Hello {
        String value() default "";
    }

    public class TestAnnotation {
        @Hello("hello")
        public void sayHello() {
            System.out.println("hello");
        }
    }

    @Test
    public void TestInitDestroyScope() {
        initDestroyBean.Test();
        System.out.println("----------initDestroyBean分割线---------");
        initDestroyBean.Test();
    }

    @Test
    public void TestLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Long tt = localDateTime.toEpochSecond(ZoneOffset.of("+8"));
    }

    @Test
    public void TestRedis() {
        redisTemplate.opsForHash().put("yo", "1", "2");
    }



}