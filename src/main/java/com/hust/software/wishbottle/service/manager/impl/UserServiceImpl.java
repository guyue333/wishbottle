package com.hust.software.wishbottle.service.manager.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hust.software.wishbottle.mapper.manager.UserMapper;
import com.hust.software.wishbottle.pojo.user.User;
import com.hust.software.wishbottle.service.manager.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selestOneByID(int user_id){
        return userMapper.selestOneByID(user_id);
    }
    @Override
    public PageInfo<User> selectByID(Integer pageIndex, Integer pageSize,int user_id){
        PageHelper.startPage(pageIndex,pageSize);
        List<User> users = userMapper.selectByID(user_id);
        PageInfo<User> info = new PageInfo<User>(users);
        return info;
    }
    @Override
    public PageInfo<User> selectByName(Integer pageIndex, Integer pageSize,String user_name){
        PageHelper.startPage(pageIndex,pageSize);
        List<User> users = userMapper.selectByName(user_name);
        PageInfo<User> info = new PageInfo<User>(users);
        return info;
    }
    @Override
    public PageInfo<User> selectByAge(Integer pageIndex, Integer pageSize,int user_age){
        PageHelper.startPage(pageIndex,pageSize);
        List<User> users = userMapper.selectByAge(user_age);
        PageInfo<User> info = new PageInfo<User>(users);
        return info;
    }
    @Override
    public PageInfo<User> selectByGender(Integer pageIndex, Integer pageSize,int user_gender){
        PageHelper.startPage(pageIndex,pageSize);
        List<User> users = userMapper.selectByGender(user_gender);
        PageInfo<User> info = new PageInfo<User>(users);
        return info;
    }
    @Override
    public PageInfo<User> selectByProvince(Integer pageIndex, Integer pageSize,String user_province){
        PageHelper.startPage(pageIndex,pageSize);
        List<User> users = userMapper.selectByProvince(user_province);
        PageInfo<User> info = new PageInfo<User>(users);
        return info;
    }

    @Override
    public List<User> selectAll(){
        return userMapper.selectAll();
    }

    @Override
    public Integer deleteUser(int user_id){
        return userMapper.deleteUser(user_id);
    }
    @Override
    public List<HashMap> classifyProvince(){return userMapper.classifyProvince();}
    @Override
    public List<HashMap> classifyAge(){return userMapper.classifyAge();}

    //测试分页

    @Override
    public PageInfo<User> selectPage(Integer pageIndex, Integer pageSize){
        PageHelper.startPage(pageIndex,pageSize);
        List<User> users = userMapper.selectPage();
        PageInfo<User> info = new PageInfo<User>(users);
        return info;
    }
}
