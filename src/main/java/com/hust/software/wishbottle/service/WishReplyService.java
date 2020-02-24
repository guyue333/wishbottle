package com.hust.software.wishbottle.service;

import com.hust.software.wishbottle.pojo.WishReply;

import java.util.List;

public interface WishReplyService {

    public int addReply(WishReply wishReply);

    public List<WishReply> findAllReplyById(int wishId);
}
