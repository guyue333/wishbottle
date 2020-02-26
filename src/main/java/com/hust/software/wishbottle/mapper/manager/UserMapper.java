package com.hust.software.wishbottle.mapper.manager;

import com.hust.software.wishbottle.pojo.user.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {

    //删除用户信息,需要将用户的一切相关都删除
    @Delete("delete from user where user_id=#{user_id}")
    Integer deleteUser(@Param("user_id") int user_id);

    //根据用户id精准查询用户信息
    @Select("select * from user where user_id=#{user_id}")
    User selestOneByID(@Param("user_id") int user_id);

    //根据用户id模糊查询用户信息
    @Select("select * from user where user_id like '%${user_id}%'")
    List<User> selectByID(@Param("user_id") int user_id);

    //根据用户名模糊查询用户信息
    @Select("select * from user where user_name like '%${user_name}%'")
    List<User> selectByName(@Param("user_name") String user_name);

    //根据用户年龄查询用户信息
    @Select("select * from user where user_age=#{user_age}")
    List<User> selectByAge(@Param("user_age") int user_age);

    //根据用户性别查询用户信息
    @Select("select * from user where user_gender=#{user_gender}")
    List<User> selectByGender(@Param("user_gender") int user_gender);

    //根据用户所在省份模糊查询用户信息
    @Select("select * from user where user_province like '%${user_province}%'")
    List<User> selectByProvince(@Param("user_province") String user_province);

    //查询所有用户信息
    @Select("select * from user")
    List<User> selectAll();

    //根据信息进行分类,省份、城市、年龄
    @Select("select user_province,count(*) from user group by user_province")
    List<HashMap> classifyProvince();

    @Select("select user_age,count(*) from user group by user_age order by user_age")
    List<HashMap> classifyAge();
}
