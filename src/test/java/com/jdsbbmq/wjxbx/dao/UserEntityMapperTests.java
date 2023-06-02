package com.jdsbbmq.wjxbx.dao;

import com.jdsbbmq.wjxbx.dao.entity.UserEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class UserEntityMapperTests {

    @Test
    void contextLoads() {

    }

    Logger log = Logger.getLogger(UserEntityMapperTests.class);

    @Test
    // 登录
    public void loginTest() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        UserEntity userEntity = userEntityMapper.login("卜部美琴", "123456");
        if (userEntity == null) {
            System.out.println("登录失败");
            // 记录error级别的信息
            log.error(">>login登录测试失败");
        } else {
            System.out.println(userEntity);
            // 记录info级别的信息
            log.info(">>login登录测试成功");
        }
    }

    @Test
    // 查询所有用户
    public void selectAllTest() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        List<UserEntity> list = userEntityMapper.selectAll();
        if (CollectionUtils.isEmpty(list)) {
            System.out.println("查询失败");
            // 记录error级别的信息
            log.error(">>selectAll所有用户查询测试失败");
        } else {
            System.out.println(list);
            // 记录info级别的信息
            log.info(">>selectAll所有用户查询测试成功");
        }
    }

    @Test
    // 根据id查询用户
    public void selectUserByIdTest() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        UserEntity userEntity = userEntityMapper.selectUserById("53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f");
        if (userEntity == null) {
            System.out.println("查询失败");
            // 记录error级别的信息
            log.error(">>selectUser用户查询测试失败");
        } else {
            System.out.println(userEntity);
            // 记录info级别的信息
            log.info(">>selectUser用户查询测试成功");
        }
    }

    @Test
    // 插入用户
    public void insertUserTest() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        UserEntity userEntity = new UserEntity();
        userEntity.setId(UUID.randomUUID().toString());
        userEntity.setStatus(1);
        userEntity.setUsername("LS");
        userEntity.setPassword("123");
        int i = userEntityMapper.insertUser(userEntity);
        if (i == 0) {
            System.out.println("插入失败");
            // 记录error级别的信息
            log.error(">>insertUser用户插入测试失败");
        } else {
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>insertUser用户插入测试成功");
        }
    }

    @Test
    // 根据id删除用户
    public void deleteUserByIdTest() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        int i = userEntityMapper.deleteUserById(UUID.randomUUID().toString());
        if (i == 0) {
            System.out.println("删除失败");
            // 记录error级别的信息
            log.error(">>deleteUser用户删除测试失败");
        } else {
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>deleteUser用户删除测试成功");
        }
    }

    @Test
    // 根据用户名查询用户id
    public void selectUserIdByUsernameTest() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        String id = userEntityMapper.selectIdByUsername("卜部美琴");
        if (id == null) {
            System.out.println("查询失败");
            // 记录error级别的信息
            log.error(">>selectUserIdByUsername用户查询测试失败");
        } else {
            System.out.println(id);
            // 记录info级别的信息
            log.info(">>selectUserIdByUsername用户查询测试成功");
        }
    }

}
