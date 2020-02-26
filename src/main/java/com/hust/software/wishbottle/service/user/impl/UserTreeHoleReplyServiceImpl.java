package com.hust.software.wishbottle.service.user.impl;

import com.hust.software.wishbottle.mapper.user.UserTreeHoleReplyMapper;
import com.hust.software.wishbottle.pojo.user.TreeHoleReply;
import com.hust.software.wishbottle.service.user.UserTreeHoleReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTreeHoleReplyServiceImpl implements UserTreeHoleReplyService {

    @Autowired
    UserTreeHoleReplyMapper userTreeHoleReplyMapper;

    /**
     * 新增一条树洞回复
     * @param treeHoleReply
     * @return
     */
    @Override
    public int addReply(TreeHoleReply treeHoleReply) {
        return userTreeHoleReplyMapper.addReply(treeHoleReply);
    }

    /**
     * 根据树洞id，查询对应的树洞评论
     * @param treeholeId
     * @return
     */
    @Override
    public List<TreeHoleReply> findAllReplyById(int treeholeId) {
        return userTreeHoleReplyMapper.findAllReplyById(treeholeId);
    }

}
