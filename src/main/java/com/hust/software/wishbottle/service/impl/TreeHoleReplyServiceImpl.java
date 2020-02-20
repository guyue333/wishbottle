package com.hust.software.wishbottle.service.impl;

import com.hust.software.wishbottle.mapper.TreeHoleReplyMapper;
import com.hust.software.wishbottle.pojo.TreeHoleReply;
import com.hust.software.wishbottle.service.TreeHoleReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeHoleReplyServiceImpl implements TreeHoleReplyService {

    @Autowired
    TreeHoleReplyMapper treeHoleReplyMapper;

    /**
     * 新增一条树洞回复
     * @param treeHoleReply
     * @return
     */
    @Override
    public int addReply(TreeHoleReply treeHoleReply) {
        return treeHoleReplyMapper.addReply(treeHoleReply);
    }

    /**
     * 根据树洞id，查询对应的树洞评论
     * @param treeholeId
     * @return
     */
    @Override
    public List<TreeHoleReply> findAllReplyById(int treeholeId) {
        return treeHoleReplyMapper.findAllReplyById(treeholeId);
    }
}
