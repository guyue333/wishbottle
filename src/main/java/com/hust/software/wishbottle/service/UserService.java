package com.hust.software.wishbottle.service;

import com.hust.software.wishbottle.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    List<User> findAll();

    public int add(User user);

    public int update(User user);

    public User isRegister(String open);

    public User getUserInfoById(int userId);
}
