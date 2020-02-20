package com.hust.software.wishbottle.controller;

import com.hust.software.wishbottle.pojo.TreeHoleReply;
import com.hust.software.wishbottle.service.TreeHoleReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TreeholeReplyController {
    @Autowired
    TreeHoleReplyService treeHoleReplyService;

    /**
     * 新增一条树洞评论
     * @param treeHoleReply
     * @return
     * @throws Exception
     */
    @PostMapping("/treeholeReply")
    public String addReply(@RequestBody TreeHoleReply treeHoleReply) throws Exception{
        treeHoleReplyService.addReply(treeHoleReply);
        return "Add success";
    }

    /**
     * 根据树洞id，查询对应的评论
     * @param treeholeId
     * @return
     * @throws Exception
     */
    @GetMapping("/treeholeReplies/{id}")
    public List<TreeHoleReply> findAllReply(@PathVariable("id") int treeholeId) throws Exception{
        List<TreeHoleReply> treeHoleReplies = treeHoleReplyService.findAllReplyById(treeholeId);
        return treeHoleReplies;
    }
}
