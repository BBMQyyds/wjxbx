package com.jdsbbmq.wjxbx.dao;

import com.jdsbbmq.wjxbx.dao.entity.UserEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class UserEntityMapperTests {

    Logger log = Logger.getLogger(UserEntityMapperTests.class);

    @Test
    void contextLoads() {

    }

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
            log.error("UserEntityMapper: >>login登录测试失败");
        } else {
            System.out.println(userEntity);
            // 记录info级别的信息
            log.info("UserEntityMapper: >>login登录测试成功");
        }
        sqlSession.close();
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
            log.error("UserEntityMapper: >>selectAll所有用户查询测试失败");
        } else {
            System.out.println(list);
            // 记录info级别的信息
            log.info("UserEntityMapper: >>selectAll所有用户查询测试成功");
        }
        sqlSession.close();
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
            log.error("UserEntityMapper: >>selectUser用户查询测试失败");
        } else {
            System.out.println(userEntity);
            // 记录info级别的信息
            log.info("UserEntityMapper: >>selectUser用户查询测试成功");
        }
        sqlSession.close();
    }

//    @Test
//    @Transactional
//    // 插入用户
//    public void insertUserTest() throws Exception {
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        //创建UserMapper对象，mybatis自动生成mapper代理对象
//        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
//        //调用userMapper的方法
//        UserEntity userEntity = new UserEntity();
//        userEntity.setId(UUID.randomUUID().toString());
//        userEntity.setStatus(1);
//        userEntity.setUsername("LS");
//        userEntity.setPassword("123");
//        int i = userEntityMapper.insertUser(userEntity);
//        if (i == 0) {
//            System.out.println("插入失败");
//            // 记录error级别的信息
//            log.error("UserEntityMapper: >>insertUser用户插入测试失败");
//        } else {
//            System.out.println(i);
//            // 记录info级别的信息
//            log.info("UserEntityMapper: >>insertUser用户插入测试成功");
//        }
//        sqlSession.close();
//    }

    @Test
    @Transactional
    // 更新用户
    public void updateUserByIdTest() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        UserEntity userEntity = new UserEntity();
        userEntity.setId("216c6b4f-12e2-4012-a029-37b15c965547");
        userEntity.setStatus(1);
        userEntity.setUsername("LS");
        userEntity.setPassword("123");
        int i = userEntityMapper.updateUser(userEntity);
        if (i == 0) {
            System.out.println("更新失败");
            // 记录error级别的信息
            log.error("UserEntityMapper: >>updateUser用户更新测试失败");
        } else {
            System.out.println(i);
            // 记录info级别的信息
            log.info("UserEntityMapper: >>updateUser用户更新测试成功");
        }
        sqlSession.close();
    }

    @Test
    @Transactional
    // 根据id删除用户
    public void deleteUserByIdTest() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        int i = userEntityMapper.deleteUserById("4f48ba43-e933-4cef-ac7d-094e1e70c555");
        if (i == 0) {
            System.out.println("删除失败");
            // 记录error级别的信息
            log.error("UserEntityMapper: >>deleteUser用户删除测试失败");
        } else {
            System.out.println(i);
            // 记录info级别的信息
            log.info("UserEntityMapper: >>deleteUser用户删除测试成功");
        }
        sqlSession.close();
    }

    @Test
    // 根据用户名查询密码
    public void selectPasswordByUsernameTest() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        String password = userEntityMapper.selectPasswordByUsername("风筝追风");
        if (password != null) {
            System.out.println("查询成功");
            // 记录info级别的信息
            log.info("UserEntityMapper: >>selectPasswordByUsername用户密码查询测试成功");
        } else {
            System.out.println("查询失败");
            // 记录error级别的信息
            log.error("UserEntityMapper: >>selectPasswordByUsername用户密码查询测试失败");
        }
    }

    @Test
    @Transactional
    //修改密码
    public void updatePasswordTest() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        int i = userEntityMapper.updatePassword("风筝追风", "e10adc3949ba59abbe56e057f20f883e");
        if (i != 0) {
            System.out.println("修改成功");
            // 记录info级别的信息
            log.info("UserEntityMapper: >>updatePasswordByUsername用户密码修改测试成功");
        } else {
            System.out.println("修改失败");
            // 记录error级别的信息
            log.error("UserEntityMapper: >>updatePasswordByUsername用户密码修改测试失败");
        }
    }
}
