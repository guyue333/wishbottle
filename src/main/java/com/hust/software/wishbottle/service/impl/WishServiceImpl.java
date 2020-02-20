package com.hust.software.wishbottle.service.impl;

import com.hust.software.wishbottle.mapper.UserMapper;
import com.hust.software.wishbottle.mapper.WishMapper;
import com.hust.software.wishbottle.pojo.Wish;
import com.hust.software.wishbottle.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishServiceImpl implements WishService {

    @Autowired
    WishMapper wishMapper;

    /**
     * 根据userId查询用户发布的所有心愿
     * @param userId
     * @return
     */
    @Override
    public List<Wish> findAllByUserId(int userId) {
        return wishMapper.findAllByUserId(userId);
    }

    /**
     * 根据pickerId查询用户捞到的所有心愿
     * @param pickerId
     * @return
     */
    @Override
    public List<Wish> findAllByPickerId(int pickerId) {
        return wishMapper.findAllByPickerId(pickerId);
    }

    /**
     * 增加一条心愿
     * @param wish
     * @return
     */
    @Override
    public int addWish(Wish wish) {
        return wishMapper.addWish(wish);
    }
}
