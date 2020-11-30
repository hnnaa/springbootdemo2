package com.example.config;

import com.example.pojo.LombokTest;
import lombok.val;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.keyvalue.core.KeyValueOperations;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisConfigTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() {
        stringRedisTemplate.opsForValue().set("id", "1");
        Assert.assertEquals("1", stringRedisTemplate.opsForValue().get("id"));
    }

    /**
     * 测试存储对象，redis 需要对对象进行序列化，取出对象数据后比对，又要进行反序列化
     * 所以注册了 RedisTemplate ，专门处理这类情况
     */
    @Test
    public void test1() {
        LombokTest lmk = new LombokTest(1, "a");

        ValueOperations<String, LombokTest> operations = redisTemplate.opsForValue();
        operations.set("user1", lmk);
        Assert.assertThat(lmk, Matchers.equalTo(operations.get("user1")));

        ListOperations listOperations=stringRedisTemplate.opsForList();
        listOperations.rightPush("list1", "fff");
        listOperations.rightPush("list1", "fff2");

        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put("lmkMap", "lmk1", lmk);
        Assert.assertThat(lmk, Matchers.equalTo(hashOperations.get("lmkMap", "lmk1")));
    }
}