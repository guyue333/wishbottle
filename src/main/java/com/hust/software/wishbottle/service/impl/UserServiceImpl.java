package com.hust.software.wishbottle.service.impl;

import com.hust.software.wishbottle.mapper.UserMapper;
import com.hust.software.wishbottle.pojo.User;
import com.hust.software.wishbottle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @Override
    public int add(User user) {
        return userMapper.add(user);
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    /**
     * 根据openid判断用户是否存在，若不存在，则将其存入到数据库中
     * @param openid
     * @return
     */
    @Override
    public User isRegister(String openid) {
        return userMapper.isRegister(openid);
    }

    /**
     * 根据userid查询对应的用户信息
     * @param userId
     * @return
     */
    @Override
    public User getUserInfoById(int userId) {
        return userMapper.getUserInfoById(userId);
    }
}
