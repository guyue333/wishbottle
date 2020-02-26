package com.hust.software.wishbottle.controller.user;

import com.hust.software.wishbottle.pojo.user.User;
import com.hust.software.wishbottle.pojo.user.WishReply;
import com.hust.software.wishbottle.pojo.user.WishReplyInfo;
import com.hust.software.wishbottle.service.user.UserUserService;
import com.hust.software.wishbottle.service.user.UserWishReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserWishReplyController {
    @Autowired
    UserWishReplyService userWishReplyService;

    @Autowired
    UserUserService userUserService;

    /**
     * 新增一条心愿评论
     * @param wishReply
     * @return
     * @throws Exception
     */
    @PostMapping("/wishreplies")
    public String addReply(@RequestBody WishReply wishReply) throws Exception{
        userWishReplyService.addReply(wishReply);
        return "success";
    }

    /**
     * 获取数据库中所有的心愿评论，将其与用户信息一起封装后返回
     * @param wishId
     * @return
     * @throws Exception
     */
    @GetMapping("/wishReplies/{id}")
    public List<WishReplyInfo> findAllReplyByWishId(@PathVariable("id") int wishId) throws Exception{

        //1、获取所有心愿回复
        List<WishReply> wishReplies = userWishReplyService.findAllReplyById(wishId);
        if(wishReplies == null){
            return null;
        }else{
            List<WishReplyInfo> wishReplyInfoList = new ArrayList<>();
            for (WishReply wishReply : wishReplies) {
                //2、获取每一条心愿回复的用户信息
                User user = userUserService.getUserInfoById(wishReply.getReplyerId());

                //3、把心愿信息和用户信息封装
                WishReplyInfo wishReplyInfo = new WishReplyInfo();

                //3.1 封装用户信息
                wishReplyInfo.setUserId(user.getUserId());
                wishReplyInfo.setUserName(user.getUserName());
                wishReplyInfo.setUserAvatar(user.getUserAvatar());

                //3.2 封装心愿信息
                wishReplyInfo.setReplyId(wishReply.getReplyId());
                wishReplyInfo.setWishId(wishReply.getWishId());
                wishReplyInfo.setReplyContent(wishReply.getReplyContent());
                wishReplyInfo.setCreateTime(wishReply.getCreateTime());

                wishReplyInfoList.add(wishReplyInfo);
            }

            return wishReplyInfoList;
        }
    }
}
