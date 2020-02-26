package com.hust.software.wishbottle.service.user;


import com.hust.software.wishbottle.pojo.user.TreeHoleReply;

import java.util.List;

public interface UserTreeHoleReplyService {

    public int addReply(TreeHoleReply treeHoleReply);

    public List<TreeHoleReply> findAllReplyById(int treeholeId);
}
