package com.jdsbbmq.wjxbx.common;

import com.jdsbbmq.wjxbx.common.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;

@SpringBootTest
public class RedisTemplateTests {
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test1(){
        redisUtil.set("name","jfz");
        System.out.println(redisUtil.get("name"));
    }
    @Test
    void contextLoads() {
//        RedisConnection connection = Objects.requireNonNull(redisTemplate.getConnectionFactory()).getConnection();
//        connection.flushDb();
//        connection.flushAll();
        redisTemplate.opsForValue().set("name1","jfz");
        System.out.println(redisTemplate.opsForValue().get("name1"));
    }
}
