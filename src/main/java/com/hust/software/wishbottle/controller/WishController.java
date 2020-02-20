package com.hust.software.wishbottle.controller;

import com.hust.software.wishbottle.pojo.Tag;
import com.hust.software.wishbottle.pojo.User;
import com.hust.software.wishbottle.pojo.Wish;
import com.hust.software.wishbottle.pojo.WishInfo;
import com.hust.software.wishbottle.service.TagService;
import com.hust.software.wishbottle.service.UserService;
import com.hust.software.wishbottle.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WishController {

    @Autowired
    WishService wishService;

    @Autowired
    TagService tagService;

    @Autowired
    UserService userService;

    /**
     * 根据userId查询用户发布的所有心愿
     * @return
     * @throws Exception
     */
    @GetMapping("/wishesByUserId/{id}")
    public List<WishInfo> listAllWishByUserId(@PathVariable("id") int userId) throws Exception{
        List<WishInfo> wishInfoList = new ArrayList<>();

        //根据userId查询数据表中的用户发布的所有心愿
        List<Wish> wishes = wishService.findAllByUserId(userId);

        //遍历心愿信息
        for (Wish wish : wishes) {
            //1、根据心愿信息，查询该心愿对应的用户信息和标签信息
            User userByWishId = userService.getUserInfoById(wish.getWriterId());
            Tag tagByWishId = tagService.selectTagById(wish.getTagId());

            //2、将用户、心愿、标签的值封装到WishInfo实体类中
            WishInfo wishInfo = new WishInfo();
            //(2.1)、封装用户信息
            wishInfo.setUserId(userByWishId.getUserId());
            wishInfo.setUserName(userByWishId.getUserName());
            wishInfo.setUserAvatar(userByWishId.getUserAvatar());

            //(2.2)、封装心愿信息
            wishInfo.setWishId(wish.getWishId());
            wishInfo.setWishContent(wish.getWishContent());
            wishInfo.setWishStatus(wish.getWishStatus());
            wishInfo.setCreateTime(wish.getCreateTime());
            wishInfo.setPickerId(wish.getPickerId());

            //(2.3)、封装标签信息
            wishInfo.setTagId(tagByWishId.getTagId());
            wishInfo.setTagMeaning(tagByWishId.getTagMeaning());

            //3、将封装后的WishInfo放到List中
            wishInfoList.add(wishInfo);
        }

        //返回list列表
        return wishInfoList;
    }

    /**
     * 根据pickerId查询用户捞到的所有心愿
     * @param pickerId
     * @return
     * @throws Exception
     */
    @GetMapping("/wishesByPickerId/{id}")
    public List<WishInfo> listAllWishByPickerId(@PathVariable("id") int pickerId) throws Exception{
        List<WishInfo> wishInfoList = new ArrayList<>();

        //根据pickerId查询用户捞到的所有心愿
        List<Wish> wishes = wishService.findAllByPickerId(pickerId);

        //遍历心愿信息
        for (Wish wish : wishes) {
            //1、根据心愿信息，查询该心愿对应的用户信息和标签信息
            User userByWishId = userService.getUserInfoById(wish.getWriterId());
            Tag tagByWishId = tagService.selectTagById(wish.getTagId());

            //2、将用户、心愿、标签的值封装到WishInfo实体类中
            WishInfo wishInfo = new WishInfo();
            //(2.1)、封装用户信息
            wishInfo.setUserId(userByWishId.getUserId());
            wishInfo.setUserName(userByWishId.getUserName());
            wishInfo.setUserAvatar(userByWishId.getUserAvatar());

            //(2.2)、封装心愿信息
            wishInfo.setWishId(wish.getWishId());
            wishInfo.setWishContent(wish.getWishContent());
            wishInfo.setWishStatus(wish.getWishStatus());
            wishInfo.setCreateTime(wish.getCreateTime());
            wishInfo.setPickerId(wish.getPickerId());

            //(2.3)、封装标签信息
            wishInfo.setTagId(tagByWishId.getTagId());
            wishInfo.setTagMeaning(tagByWishId.getTagMeaning());

            //3、将封装后的WishInfo放到List中
            wishInfoList.add(wishInfo);
        }


        return wishInfoList;
    }

    /**
     * 增加一条心愿信息
     * @param wish
     * @return
     * @throws Exception
     */
    @PostMapping("/wishes")
    public String addWish(@RequestBody Wish wish) throws Exception{
        System.out.println("传入的时间为："+wish.getCreateTime());
        System.out.println("传入的标签id的值为："+wish.getTagId());

        wishService.addWish(wish);
        return "add success";
    }
}
