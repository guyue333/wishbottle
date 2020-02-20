package com.hust.software.wishbottle.service;

import com.hust.software.wishbottle.pojo.TreeHoleReply;

import java.util.List;

public interface TreeHoleReplyService {

    public int addReply(TreeHoleReply treeHoleReply);

    public List<TreeHoleReply> findAllReplyById(int treeholeId);
}
