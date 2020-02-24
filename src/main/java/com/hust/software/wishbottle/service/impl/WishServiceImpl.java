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

    /**
     * 根据心愿id查询心愿信息
     * @param wishId
     * @return
     */
    @Override
    public Wish findOneById(int wishId) {
        return wishMapper.findOneById(wishId);
    }

    /**
     * 查询数据库中状态为0的非本人发布的最新一条心愿信息
     * @param userId
     * @return
     */
    @Override
    public Wish digWish(int userId) {
        return wishMapper.digWish(userId);
    }

    /**
     * 根据心愿id修改心愿状态
     * @param wishId
     * @return
     */
    @Override
    public int updateStatus(int wishId) {
        return wishMapper.updateStatus(wishId);
    }

    /**
     * 根据心愿id，修改心愿拾取者的id
     * @param wishId
     * @param pickerId
     * @return
     */
    @Override
    public int updatePicker(int wishId, int pickerId) {
        return wishMapper.updatePicker(wishId,pickerId);
    }

    /**
     * 如果心愿被作者删除，那么修改心愿的状态为2
     * @param wishId
     * @return
     */
    @Override
    public int deleteBySelf(int wishId) {
        return wishMapper.deleteBySelf(wishId);
    }

    /**
     * 如果心愿被捞到的人删除，那么修改心愿的状态为3
     * @param wishId
     * @return
     */
    @Override
    public int deleteByPicker(int wishId) {
        return wishMapper.deleteByPicker(wishId);
    }
}
