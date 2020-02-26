package com.hust.software.wishbottle.service.user.impl;

import com.hust.software.wishbottle.mapper.user.UserWishReplyMapper;
import com.hust.software.wishbottle.pojo.user.WishReply;
import com.hust.software.wishbottle.service.user.UserWishReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserWishReplyServiceImpl implements UserWishReplyService {
    @Autowired
    UserWishReplyMapper userWishReplyMapper;

    /**
     * 向数据库中插入一条心愿评论
     * @param wishReply
     * @return
     */
    @Override
    public int addReply(WishReply wishReply) {
        return userWishReplyMapper.addReply(wishReply);
    }

    /**
     * 根据心愿id，查询对应的评论
     * @param wishId
     * @return
     */
    @Override
    public List<WishReply> findAllReplyById(int wishId) {
        return userWishReplyMapper.findAllReplyById(wishId);
    }
}
