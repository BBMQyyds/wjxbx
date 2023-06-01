package com.jdsbbmq.wjxbx.dao;

import com.jdsbbmq.wjxbx.dao.entity.UserEntity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserEntityMapper {
    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR),
            @Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "start_time", property = "startTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "stop_time", property = "stopTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.VARCHAR),
            @Result(column = "created_by", property = "createdBy", jdbcType = JdbcType.CHAR),
            @Result(column = "creation_date", property = "creationDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "last_updated_by", property = "lastUpdatedBy", jdbcType = JdbcType.CHAR),
            @Result(column = "last_update_date", property = "lastUpdateDate", jdbcType = JdbcType.TIMESTAMP)
    })
    @Select("SELECT id, username, `password`, status FROM user_info WHERE id = #{id}")
    UserEntity selectByPrimaryKey(String id);

    @Delete("DELETE FROM user_info WHERE id = #{id}")
    int deleteByPrimaryKey(String id);

    @Insert("INSERT INTO user_info (id, username, `password`, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) " +
            "VALUES (#{id}, #{username}, #{password}, #{startTime}, #{stopTime}, #{status}, #{createdBy}, #{creationDate}, #{lastUpdatedBy}, #{lastUpdateDate})")
    int insert(UserEntity userEntity);

    @Insert("<script>" +
            "INSERT INTO user_info " +
            "<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">" +
            "<if test=\"id != null\">id,</if>" +
            "<if test=\"username != null\">username,</if>" +
            "<if test=\"password != null\">password,</if>" +
            "<if test=\"startTime != null\">start_time,</if>" +
            "<if test=\"stopTime != null\">stop_time,</if>" +
            "<if test=\"status == null\">status,</if>" +
            "<if test=\"createdBy != null\">created_by,</if>" +
            "<if test=\"creationDate != null\">creation_date,</if>" +
            "<if test=\"lastUpdatedBy != null\">last_updated_by,</if>" +
            "<if test=\"lastUpdateDate != null\">last_update_date,</if>" +
            "</trim>" +
            "<trim prefix=\"VALUES (\" suffix=\")\" suffixOverrides=\",\">" +
            "<if test=\"id != null\">#{id},</if>" +
            "<if test=\"username != null\">#{username},</if>" +
            "<if test=\"password != null\">#{password},</if>" +
            "<if test=\"startTime != null\">#{startTime},</if>" +
            "<if test=\"stopTime != null\">#{stopTime},</if>" +
            "<if test=\"status == null\">1,</if>" +
            "<if test=\"createdBy != null\">#{createdBy},</if>" +
            "<if test=\"creationDate != null\">#{creationDate},</if>" +
            "<if test=\"lastUpdatedBy != null\">#{lastUpdatedBy},</if>" +
            "<if test=\"lastUpdateDate != null\">#{lastUpdateDate},</if>" +
            "</trim>" +
            "</script>")
    int insertSelective(UserEntity userEntity);

    @Update("<script>" +
            "UPDATE user_info " +
            "<set>" +
            "<if test=\"username != null\">username = #{username},</if>" +
            "<if test=\"password != null\">password = #{password},</if>" +
            "<if test=\"startTime != null\">start_time = #{startTime},</if>" +
            "<if test=\"stopTime != null\">stop_time = #{stopTime},</if>" +
            "<if test=\"status != null\">status = #{status},</if>" +
            "<if test=\"createdBy != null\">created_by = #{createdBy},</if>" +
            "<if test=\"creationDate != null\">creation_date = #{creationDate},</if>" +
            "<if test=\"lastUpdatedBy != null\">last_updated_by = #{lastUpdatedBy},</if>" +
            "<if test=\"lastUpdateDate != null\">last_update_date = #{lastUpdateDate},</if>" +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    int updateByPrimaryKeySelective(UserEntity userEntity);

    @Update("UPDATE user_info SET username = #{username}, password = #{password}, start_time = #{startTime}, stop_time = #{stopTime}, " +
            "status = #{status}, created_by = #{createdBy}, creation_date = #{creationDate}, " +
            "last_updated_by = #{lastUpdatedBy}, last_update_date = #{lastUpdateDate} " +
            "WHERE id = #{id}")
    int updateByPrimaryKey(UserEntity userEntity);

    @Select("SELECT id, username, `password`, status FROM user_info WHERE username = #{username} AND `password` = #{password} AND status = '1'")
    UserEntity selectUserInfo(String username, String password);
}
