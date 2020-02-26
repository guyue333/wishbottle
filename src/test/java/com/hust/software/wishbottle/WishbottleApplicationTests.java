package com.hust.software.wishbottle;

import com.hust.software.wishbottle.mapper.user.UserUserMapper;
import com.hust.software.wishbottle.pojo.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest()
class WishbottleApplicationTests {

    @Autowired
    UserUserMapper userUserMapper;

    @Test
    void contextLoads() {
        List<User> us = userUserMapper.findAll();
        for (User u : us) {
            System.out.println(u.toString());
        }
    }

}
