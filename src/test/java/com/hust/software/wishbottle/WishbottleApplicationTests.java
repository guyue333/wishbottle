package com.hust.software.wishbottle;

import com.hust.software.wishbottle.mapper.UserMapper;
import com.hust.software.wishbottle.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest()
class WishbottleApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> us = userMapper.findAll();
        for (User u : us) {
            System.out.println(u.toString());
        }
    }

}
