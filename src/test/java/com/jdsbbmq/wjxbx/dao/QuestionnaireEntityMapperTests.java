package com.jdsbbmq.wjxbx.dao;

import com.jdsbbmq.wjxbx.dao.entity.QuestionnaireEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class QuestionnaireEntityMapperTests {
    Logger log = Logger.getLogger(QuestionnaireEntityMapperTests.class);

    @Test
    void contextLoads() {

    }

    @Test
    // 查询所有问卷
    public void selectAllTest() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建QuestionnaireEntityMapper
        QuestionnaireEntityMapper questionnaireEntityMapper = sqlSession.getMapper(QuestionnaireEntityMapper.class);
        //调用QuestionnaireEntityMapper的方法
        List<QuestionnaireEntity> questionnaireEntityList = questionnaireEntityMapper.selectAllQuestionnaire("0f796ce7-28f5-405e-b7f5-682e6cbca8e2");
        if (questionnaireEntityList == null) {
            System.out.println("查询所有问卷失败");
            // 记录error级别的信息
            log.error("QuestionnaireEntityMapper: >>selectAllTest查询所有问卷测试失败");
        } else {
            System.out.println(questionnaireEntityList);
            // 记录info级别的信息
            log.info("QuestionnaireEntityMapper: >>selectAllTest查询所有问卷测试成功");
        }
        sqlSession.close();
    }

    @Test
    // 查询一个问卷
    public void selectOneTest() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建QuestionnaireEntityMapper
        QuestionnaireEntityMapper questionnaireEntityMapper = sqlSession.getMapper(QuestionnaireEntityMapper.class);
        //调用QuestionnaireEntityMapper的方法
        QuestionnaireEntity questionnaireEntity = questionnaireEntityMapper.selectQuestionnaireById("0f796ce7-28f5-405e-b7f5-682e6cbca8e2");
        if (questionnaireEntity == null) {
            System.out.println("查询一个问卷失败");
            // 记录error级别的信息
            log.error("QuestionnaireEntityMapper: >>selectOneTest查询一个问卷测试失败");
        } else {
            System.out.println(questionnaireEntity);
            // 记录info级别的信息
            log.info("QuestionnaireEntityMapper: >>selectOneTest查询一个问卷测试成功");
        }
        sqlSession.close();
    }

    @Test
    @Transactional
    // 添加问卷
    public void insertOneTest() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建QuestionnaireEntityMapper
        QuestionnaireEntityMapper questionnaireEntityMapper = sqlSession.getMapper(QuestionnaireEntityMapper.class);
        //调用QuestionnaireEntityMapper的方法
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity("2", "1779cd18-cc34-47b2-a453-419fcff8df3b", "3", "4", new Date(),new Date(),new Date(),0,0,0,0);
        int result = questionnaireEntityMapper.insertQuestionnaire(questionnaireEntity);
        if (result == 0) {
            System.out.println("添加问卷失败");
            // 记录error级别的信息
            log.error("QuestionnaireEntityMapper: >>insertOneTest添加问卷测试失败");
        } else {
            System.out.println("添加问卷成功");
            // 记录info级别的信息
            log.info("QuestionnaireEntityMapper: >>insertOneTest添加问卷测试成功");
        }
        sqlSession.close();
    }

    @Test
    @Transactional
    // 更新问卷
    public void updateOneTest() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建QuestionnaireEntityMapper
        QuestionnaireEntityMapper questionnaireEntityMapper = sqlSession.getMapper(QuestionnaireEntityMapper.class);
        //调用QuestionnaireEntityMapper的方法
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity("2", "1779cd18-cc34-47b2-a453-419fcff8df3b", "3","4",  new Date(),new Date(),new Date(),0,0,0,0);
        int result = questionnaireEntityMapper.updateQuestionnaire(questionnaireEntity);
        if (result == 0) {
            System.out.println("更新问卷失败");
            // 记录error级别的信息
            log.error("QuestionnaireEntityMapper: >>updateOneTest更新问卷测试失败");
        } else {
            System.out.println("更新问卷成功");
            // 记录info级别的信息
            log.info("QuestionnaireEntityMapper: >>updateOneTest更新问卷测试成功");
        }
        sqlSession.close();
    }

    @Test
    @Transactional
    // 删除问卷
    public void deleteOneTest() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建QuestionnaireEntityMapper
        QuestionnaireEntityMapper questionnaireEntityMapper = sqlSession.getMapper(QuestionnaireEntityMapper.class);
        //调用QuestionnaireEntityMapper的方法
        int result = questionnaireEntityMapper.deleteQuestionnaireById("1");
        if (result == 0) {
            System.out.println("删除问卷失败");
            // 记录error级别的信息
            log.error("QuestionnaireEntityMapper: >>deleteOneTest删除问卷测试失败");
        } else {
            System.out.println("删除问卷成功");
            // 记录info级别的信息
            log.info("QuestionnaireEntityMapper: >>deleteOneTest删除问卷测试成功");
        }
        sqlSession.close();
    }
}
