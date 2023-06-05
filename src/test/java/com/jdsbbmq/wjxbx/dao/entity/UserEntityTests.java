package com.jdsbbmq.wjxbx.dao.entity;

import org.junit.jupiter.api.Test;

import java.util.Date;

public class UserEntityTests {

    @Test
    void contextLoads() {

    }

    @Test
    //全参构造
    public void allArgsConstructorTest() {
        UserEntity userEntity = new UserEntity("1", "2", "3", new Date(), new Date(), 0, "4", new Date(), "5", new Date());
        System.out.println(userEntity);
    }

    @Test
    //canEqual
    public void canEqualTest() {
        UserEntity userEntity = new UserEntity();
        System.out.println(userEntity.canEqual(new UserEntity()));
    }

    @Test
    //equals
    public void equalsTest() {
        UserEntity userEntity = new UserEntity();
        System.out.println(userEntity.equals(new UserEntity()));
    }

    @Test
    //hashcode
    public void hashcodeTest() {
        UserEntity userEntity = new UserEntity();
        System.out.println(userEntity.hashCode());
    }
}
