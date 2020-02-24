package com.hust.software.wishbottle.service.impl;

import com.hust.software.wishbottle.mapper.WishReplyMapper;
import com.hust.software.wishbottle.pojo.WishReply;
import com.hust.software.wishbottle.service.WishReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishReplyServiceImpl implements WishReplyService {
    @Autowired
    WishReplyMapper wishReplyMapper;

    /**
     * 向数据库中插入一条心愿评论
     * @param wishReply
     * @return
     */
    @Override
    public int addReply(WishReply wishReply) {
        return wishReplyMapper.addReply(wishReply);
    }

    /**
     * 根据心愿id，查询对应的评论
     * @param wishId
     * @return
     */
    @Override
    public List<WishReply> findAllReplyById(int wishId) {
        return wishReplyMapper.findAllReplyById(wishId);
    }
}
