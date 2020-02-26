package com.hust.software.wishbottle.controller.user;

import com.hust.software.wishbottle.pojo.user.Tag;
import com.hust.software.wishbottle.pojo.user.User;
import com.hust.software.wishbottle.pojo.user.Wish;
import com.hust.software.wishbottle.pojo.user.WishInfo;
import com.hust.software.wishbottle.service.user.UserTagService;
import com.hust.software.wishbottle.service.user.UserUserService;
import com.hust.software.wishbottle.service.user.UserWishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserWishController {

    @Autowired
    UserWishService userWishService;

    @Autowired
    UserTagService userTagService;

    @Autowired
    UserUserService userUserService;

    /**
     * 根据userId查询用户发布的所有心愿
     * @return
     * @throws Exception
     */
    @GetMapping("/wishesByUserId/{id}")
    public List<WishInfo> listAllWishByUserId(@PathVariable("id") int userId) throws Exception{
        List<WishInfo> wishInfoList = new ArrayList<>();

        //根据userId查询数据表中的用户发布的所有心愿
        List<Wish> wishes = userWishService.findAllByUserId(userId);

        //遍历心愿信息
        for (Wish wish : wishes) {


            //1、根据心愿信息，查询该心愿对应的用户信息和标签信息
            User userByWishId = userUserService.getUserInfoById(wish.getWriterId());
            Tag tagByWishId = userTagService.selectTagById(wish.getTagId());

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
        List<Wish> wishes = userWishService.findAllByPickerId(pickerId);

        //遍历心愿信息
        for (Wish wish : wishes) {
            //1、根据心愿信息，查询该心愿对应的用户信息和标签信息
            User userByWishId = userUserService.getUserInfoById(wish.getWriterId());
            Tag tagByWishId = userTagService.selectTagById(wish.getTagId());

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

        userWishService.addWish(wish);
        return "add success";
    }

    /**
     * 根据心愿id获取心愿详情
     * @param wishId
     * @return
     * @throws Exception
     */
    @GetMapping("/wish/{id}")
    public WishInfo findOneByWishId(@PathVariable("id") int wishId) throws Exception{
        //根据心愿id查询心愿信息
        Wish wish = userWishService.findOneById(wishId);
        //查询这条心愿对应的用户信息
        User user = userUserService.getUserInfoById(wish.getWriterId());
        //查询这条心愿对应的标签信息
        Tag tag = userTagService.selectTagById(wish.getTagId());

        WishInfo wishInfo = new WishInfo();
        //(2.1)、封装用户信息
        wishInfo.setUserId(user.getUserId());
        wishInfo.setUserName(user.getUserName());
        wishInfo.setUserAvatar(user.getUserAvatar());

        //(2.2)、封装心愿信息
        wishInfo.setWishId(wish.getWishId());
        wishInfo.setWishContent(wish.getWishContent());
        wishInfo.setWishStatus(wish.getWishStatus());
        wishInfo.setCreateTime(wish.getCreateTime());
        wishInfo.setPickerId(wish.getPickerId());

        //(2.3)、封装标签信息
        wishInfo.setTagId(tag.getTagId());
        wishInfo.setTagMeaning(tag.getTagMeaning());
        
        return wishInfo;
    }

    /**
     * 查询数据库中状态为0的非本人发布的最新一条心愿信息
     * @param pickerId
     * @return
     * @throws Exception
     */
    @GetMapping("/digWish/{id}")
    public WishInfo digWish(@PathVariable("id") int pickerId) throws Exception{
        //1.获取数据库中最近一条心愿信息
        Wish wish = userWishService.digWish(pickerId);
        if(wish == null){
            return null;
        }else{
            //1.1 查询这条心愿对应的作者信息
            User user = userUserService.getUserInfoById(wish.getWriterId());
            //1.2 查询这条心愿对应的标签信息
            Tag tag = userTagService.selectTagById(wish.getTagId());

            //2.设置心愿的拾取者id
            userWishService.updatePicker(wish.getWishId(),pickerId);

            //3.设置心愿的状态为1（被捞取）
            userWishService.updateStatus(wish.getWishId());

            //4.将作者、心愿、标签信息设置到wishInfo上
            WishInfo wishInfo = new WishInfo();
            //4.1 封装用户信息
            wishInfo.setUserId(user.getUserId());
            wishInfo.setUserName(user.getUserName());
            wishInfo.setUserAvatar(user.getUserAvatar());

            //4.2 封装心愿信息
            wishInfo.setWishId(wish.getWishId());
            wishInfo.setWishContent(wish.getWishContent());
            wishInfo.setWishStatus(1);
            wishInfo.setCreateTime(wish.getCreateTime());
            wishInfo.setPickerId(pickerId);

            //4.3 封装标签信息
            wishInfo.setTagId(tag.getTagId());
            wishInfo.setTagMeaning(tag.getTagMeaning());

            return wishInfo;
        }
    }

    /**
     * 如果心愿被作者删除，那么修改心愿的状态为2
     * @param wishId
     * @return
     */
    @DeleteMapping("/deleteBySelf/{id}")
    public String deleteWishBySef(@PathVariable("id") int wishId) throws Exception{
        userWishService.deleteBySelf(wishId);
        return "delete success";
    }

    /**
     * 如果心愿被捞到的人删除，那么修改心愿的状态为3
     * @param wishId
     * @return
     */
    @DeleteMapping("/deleteByPicker/{id}")
    public String deleteWishByPicker(@PathVariable("id") int wishId) throws Exception{
        userWishService.deleteByPicker(wishId);

        return "delete success";
    }
}
