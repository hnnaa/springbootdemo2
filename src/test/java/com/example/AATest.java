package com.example;

import com.example.pojo.B;
import com.example.pojo.C;
import com.example.test.TestFuture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class AATest {

    @Test
    public void tempTest() throws Exception {
        System.out.println("begin test");
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> result = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("begin thread " + Thread.currentThread().getId());
                Thread.sleep(1000);
                System.out.println("begin end " + Thread.currentThread().getId());
                return "yo";
            }
        });
        System.out.println("after execute");
        System.out.println("result=" + result.get());
        System.out.println("end test");
    }

    @Test
    public void tempTest2() throws Exception {
        System.out.println("begin test");
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        CompletableFuture<String> result = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                System.out.println("begin thread " + Thread.currentThread().getId());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("begin end " + Thread.currentThread().getId());
                return "yo";
            }
        }, executorService);

        System.out.println("after execute");
        result.thenAccept(e -> {
            System.out.println("result=" + e);
        });
        System.out.println("end test");

        Thread.sleep(2000);
    }

    @Test
    public void testClassLoader() throws Exception {
        File jarFile = new File("D:\\work\\project\\java\\workspace\\spring\\target\\spring-0.0.1-SNAPSHOT.jar");
        URL url = jarFile.toURI().toURL();
        ClassLoader classLoader = new URLClassLoader(new URL[]{url});
        Class<?> myClass = classLoader.loadClass("com.how2java.util.StringUtil");
        Method m = myClass.getMethod("isValid", String.class);
        Object obj = m.invoke(null, "iioi");
    }

    @Test
    public void initList() {
        List<Object> lst = Arrays.asList(1, 2, 3, "s");
        long r = lst.stream().mapToLong(m -> (long) m.hashCode()).max().getAsLong();
    }

    @Test
    public void testExtendSerialize() throws Exception {
        B b = new B();
        b.setBv("yoyo");
        b.setNum(1);
        b.setStr("str:s");
        List<C> lst = new ArrayList<>();
        lst.add(new C("d", 333));
        lst.add(new C("dd", 4444));
        b.setData(lst);
        ObjectMapper objectMapper = new ObjectMapper();
        String ss = objectMapper.writeValueAsString(b);

        String srcJson = "{\"num\":1,\"data\":[{\"c1\":\"d\",\"c2\":333},{\"c1\":\"dd\",\"c2\":4444}],\"bv\":\"yoyo\",\"str\":\"str:s\"}";
        B bb = objectMapper.readValue(srcJson, B.class);
        System.out.println("");
    }

    @Test
    public void TestColl() {
        List<String> stringList = new ArrayList<>();
        for (String s : stringList) {
            System.out.println(s);
        }
        System.out.println("");
    }

    @Test
    public void TestA() throws CloneNotSupportedException {
        System.out.println(System.currentTimeMillis());
    }
}
