package com.jdsbbmq.wjxbx.dao;


import com.jdsbbmq.wjxbx.bean.project.Project;
import com.jdsbbmq.wjxbx.dao.entity.ProjectEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.io.InputStream;

@SpringBootTest
public class ProjectEntityMapperTests {
    Logger log = Logger.getLogger(UserEntityMapperTests.class);

    @Test
    void contextLoads() {

    }

    //查询所有项目
    @Test
    public void selectAllProjectTest() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建ProjectMapper对象，mybatis自动生成mapper代理对象
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        //调用projectMapper的方法
        List<ProjectEntity> projectEntityList = projectEntityMapper.selectAllProject("53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f");
        if (CollectionUtils.isEmpty(projectEntityList)) {
            System.out.println("查询失败");
            // 记录error级别的信息
            log.error("ProjectEntityMapper: >>selectAllProject查询所有项目测试失败");
        } else {
            System.out.println(projectEntityList);
            // 记录info级别的信息
            log.info("ProjectEntityMapper: >>selectAllProject查询所有项目测试成功");
        }
        sqlSession.close();
    }

    //根据id查询项目
    @Test
    public void selectProjectByIdTest() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建ProjectMapper对象，mybatis自动生成mapper代理对象
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        //调用projectMapper的方法
        ProjectEntity projectEntity = projectEntityMapper.selectProjectById("0f796ce7-28f5-405e-b7f5-682e6cbca8e2");
        if (projectEntity == null) {
            System.out.println("查询失败");
            // 记录error级别的信息
            log.error("ProjectEntityMapper: >>selectProjectById根据id查询项目测试失败");
        } else {
            System.out.println(projectEntity);
            // 记录info级别的信息
            log.info("ProjectEntityMapper: >>selectProjectById根据id查询项目测试成功");
        }
        sqlSession.close();
    }

    @Test
    //根据名称查询项目
    public void selectProjectByNameTest() throws Exception{
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建ProjectMapper对象，mybatis自动生成mapper代理对象
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        //调用projectMapper的方法
        List<ProjectEntity> projectEntityList = projectEntityMapper.selectProjectByName("风筝审批");
        if (CollectionUtils.isEmpty(projectEntityList)) {
            System.out.println("查询失败");
            // 记录error级别的信息
            log.error("ProjectEntityMapper: >>selectProjectByName根据名称查询项目测试失败");
        } else {
            System.out.println(projectEntityList);
            // 记录info级别的信息
            log.info("ProjectEntityMapper: >>selectProjectByName根据名称查询项目测试成功");
        }
        sqlSession.close();
    }

    //增加项目
    @Test
    @Transactional
    public void insertProjectTest() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建ProjectMapper对象，mybatis自动生成mapper代理对象
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        //调用projectMapper的方法
        Project project=new Project("1","2","3","4","5",new Date(),"6",new Date());
        ProjectEntity projectEntity = new ProjectEntity(project);
        int result = projectEntityMapper.insertProject(projectEntity);
        if (result == 0) {
            System.out.println("增加失败");
            // 记录error级别的信息
            log.error("ProjectEntityMapper: >>insertProject增加项目测试失败");
        } else {
            System.out.println("增加成功");
            // 记录info级别的信息
            log.info("ProjectEntityMapper: >>insertProject增加项目测试成功");
        }
        sqlSession.close();
    }

    //删除项目
    @Test
    @Transactional
    public void deleteProjectByIdTest() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建ProjectMapper对象，mybatis自动生成mapper代理对象
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        //调用projectMapper的方法
        int result = projectEntityMapper.deleteProjectById("1");
        if (result == 0) {
            System.out.println("删除失败");
            // 记录error级别的信息
            log.error("ProjectEntityMapper: >>deleteProjectById删除项目测试失败");
        } else {
            System.out.println("删除成功");
            // 记录info级别的信息
            log.info("ProjectEntityMapper: >>deleteProjectById删除项目测试成功");
        }
        sqlSession.close();
    }

    //修改项目
    @Test
    @Transactional
    public void updateProjectByIdTest() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建ProjectMapper对象，mybatis自动生成mapper代理对象
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        //调用projectMapper的方法
        Project project=new Project("0f796ce7-28f5-405e-b7f5-682e6cbca8e2","2","3","4","5",new Date(),"6",new Date());
        ProjectEntity projectEntity=new ProjectEntity(project);
        int result = projectEntityMapper.updateProject(projectEntity);
        if (result == 0) {
            System.out.println("修改失败");
            // 记录error级别的信息
            log.error("ProjectEntityMapper: >>updateProjectById修改项目测试失败");
        } else {
            System.out.println("修改成功");
            // 记录info级别的信息
            log.info("ProjectEntityMapper: >>updateProjectById修改项目测试成功");
        }
        sqlSession.close();
    }
}
