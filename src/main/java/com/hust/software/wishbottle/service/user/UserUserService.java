package com.hust.software.wishbottle.service.user;

import com.hust.software.wishbottle.pojo.user.User;

import java.util.List;

public interface UserUserService {

    List<User> findAll();

    public int add(User user);

    public int update(User user);

    public User isRegister(String open);

    public User getUserInfoById(int userId);
}
