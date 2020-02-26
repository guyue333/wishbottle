package com.hust.software.wishbottle.mapper.manager;

import com.hust.software.wishbottle.pojo.manage.Manage;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ManageMapper {

    //验证管理员账号是否存在
    @Select("select * from manager where manager_account=#{account} and manager_password=#{password}")
    Manage verifyLogin(@Param("account") String account, @Param("password") String password);

    //查询是否是超级管理员
    @Select("select * from manager where manager_id=#{id} and manager_type=1")
    Integer isSuperManager(@Param("id") int id);

    //添加管理员账号信息
    @Insert("insert into manager (manager_account,manager_password,manager_type) values(#{managerAccount},#{managerPassword},#{managerType})")
    Integer addManager(Manage manage);

    //删除管理员账号信息
    @Delete("delete from manager where manager_id = #{id}")
    Integer deleteManager(@Param("id") int id);

    //修改管理员账号信息
    @Update("update manager set manager_account=#{managerAccount},manager_password=#{managerPassword},manager_type=#{managerType} where manager_id=#{managerId}")
    Integer updateManager(Manage manage);

    //根据id查询单个管理员信息
    @Select("select * from manager where manager_id=#{id}")
    Manage selectOne(@Param("id") int id);

    //根据管理员单个用户名查询信息
    @Select("select * from manager where manager_account=#{manager_account}")
    Manage selectOneByName(@Param("manager_account") String manager_account);

    //查询所有管理员信息
    @Select("select * from manager")
    List<Manage> selectAll();

    //根据id模糊查询“多”个管理员信息,主要考虑返回类型
    @Select("select * from manager where manager_id like '%${id}%'")
    List<Manage> selectListByID(@Param("id") int id);

    //根据用户名模糊查询“多”个管理员信息
    @Select("select * from manager where manager_account like '%${manager_account}%'")
    List<Manage> selectListByName(@Param("manager_account") String manager_account);

    //根据管理员类型模糊查询“多”个管理员信息
    @Select("select * from manager where manager_type like '%${manager_type}%'")
    List<Manage> selectListByType(@Param("manager_type") int manager_type);
}
