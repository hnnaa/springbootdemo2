package com.example.test;

import com.example.Springbootdemo2Application;
import com.example.pojo.InitDestroyBean;
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
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Springbootdemo2Application.class, TestFuture.class})
public class Test1Test {

    private Logger log = LoggerFactory.getLogger(Test1Test.class);
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private InitDestroyBean initDestroyBean;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
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