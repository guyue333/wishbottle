package com.hust.software.wishbottle.service.user;


import com.hust.software.wishbottle.pojo.user.WishReply;

import java.util.List;

public interface UserWishReplyService {

    public int addReply(WishReply wishReply);

    public List<WishReply> findAllReplyById(int wishId);
}
