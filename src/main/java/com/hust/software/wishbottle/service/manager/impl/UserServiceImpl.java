package com.hust.software.wishbottle.service.manager.impl;

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
    public List<User> selectByID(int user_id){
        return userMapper.selectByID(user_id);
    }
    @Override
    public List<User> selectByName(String user_name){
        return userMapper.selectByName(user_name);
    }
    @Override
    public List<User> selectByAge(int user_age){
        return userMapper.selectByAge(user_age);
    }
    @Override
    public List<User> selectByGender(int user_gender){
        return userMapper.selectByGender(user_gender);
    }
    @Override
    public List<User> selectByProvince(String user_province){
        return userMapper.selectByProvince(user_province);
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
}
