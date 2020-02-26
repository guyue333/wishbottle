package com.hust.software.wishbottle.service.user.impl;

import com.hust.software.wishbottle.mapper.user.UserUserMapper;
import com.hust.software.wishbottle.pojo.user.User;
import com.hust.software.wishbottle.service.user.UserUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserUserServiceImpl implements UserUserService {
    @Autowired
    UserUserMapper userUserMapper;

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<User> findAll() {
        return userUserMapper.findAll();
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @Override
    public int add(User user) {
        return userUserMapper.add(user);
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @Override
    public int update(User user) {
        return userUserMapper.update(user);
    }

    /**
     * 根据openid判断用户是否存在，若不存在，则将其存入到数据库中
     * @param openid
     * @return
     */
    @Override
    public User isRegister(String openid) {
        return userUserMapper.isRegister(openid);
    }

    /**
     * 根据userid查询对应的用户信息
     * @param userId
     * @return
     */
    @Override
    public User getUserInfoById(int userId) {
        return userUserMapper.getUserInfoById(userId);
    }
}
