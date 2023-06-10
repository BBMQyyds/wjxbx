package com.jdsbbmq.wjxbx.common;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootTest
public class RedisTests {

    private final Jedis jedis = new Jedis("101.43.255.130", 6379);

    @Test
    void contextLoads() {
    }

    @Test
    //测试redis字符串
    public void RedisStringTest() {
        // 若没有密码注释掉下面这行
        jedis.auth("20030321");
        jedis.set("zzz", "11");
        String value = jedis.get("zzz");
        System.out.println(value);
        jedis.incr("zzz");
        System.out.println("自增之后的zzz的值为" + jedis.get("zzz"));
        System.out.println("decr之后的值" + jedis.decr("zzz"));

        jedis.append("zzz", "append");
        System.out.println(jedis.get("zzz"));

        jedis.del("zzz");
        System.out.println("is exist?" + jedis.exists("zzz"));
        jedis.close();
    }

    @Test
    //测试redis列表
    public void RedisListTest() {
        // 若没有密码注释掉下面这行
        jedis.auth("20030321");
        //将数据存储到list数据中的时候，先存储在list中的数据，是在最左面，删除的话，删除的是最左面的。
        jedis.lpush("list5", "1", "2", "3");
        List<String> lrange = jedis.lrange("list5", 0, -1);
        for (String string2 : lrange) {
            System.out.println(string2);
        }

        String lpop = jedis.lpop("list5");
        String rpop = jedis.rpop("list5");
        System.out.println("删除的左边的元素是" + lpop + "删除的右边的元素是" + rpop);

        jedis.del("list5");
        System.out.println("is list5 exist?" + jedis.exists("list5"));
    }

    @Test
    //测试redis集合
    public void RedisSetTest() {
        // 若没有密码注释掉下面这行
        jedis.auth("20030321");
        jedis.sadd("set1", "e", "a", "b", "c", "d");
        Set<String> smembers = jedis.smembers("set1");
        System.out.println(smembers);

        //弹出右边的一个
        String lpop = jedis.spop("set1");
        System.out.println(lpop);
    }

    @Test
    //测试redis散列
    public void RedisHashTest() {
        // 若没有密码注释掉下面这行
        jedis.auth("20030321");
        Map<String, String> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");

        jedis.hmset("map1", map);
        Map<String, String> map2 = jedis.hgetAll("map1");
        System.out.println(map2.toString());
    }

}
