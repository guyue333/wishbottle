package com.hust.software.wishbottle.controller.manage;

import com.hust.software.wishbottle.pojo.manage.WishView;
import com.hust.software.wishbottle.pojo.user.Wish;
import com.hust.software.wishbottle.pojo.user.WishReport;
import com.hust.software.wishbottle.service.manager.UserService;
import com.hust.software.wishbottle.service.manager.WishService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("wish")
public class WishController {
    @Autowired
    private WishService wishService;
    @Autowired
    private UserService userService;

    //查询所有心愿
    @RequestMapping("wishList")
    public String wishList(Model model){
        List<Wish> wishes = wishService.selectAll();
        List<WishView> wishViews = new ArrayList<>();
        for (int i=0;i<wishes.size();i++){
            WishView wishView = new WishView();
            wishView.setWishId(wishes.get(i).getWishId());
            wishView.setWriterName(userService.selestOneByID(wishes.get(i).getWriterId()).getUserName());
            wishView.setTagMeaning(wishService.selectTagByID(wishes.get(i).getTagId()).getTagMeaning());
            wishView.setCreateTime(wishes.get(i).getCreateTime());
            wishView.setWishContent(wishes.get(i).getWishContent());
            wishView.setWishStatus(wishes.get(i).getWishStatus());
            wishView.setPickerName(userService.selestOneByID(wishes.get(i).getPickerId()).getUserName());
            wishViews.add(wishView);
        }
        model.addAttribute("wishViews",wishViews);
        return "wishlist";
    }
    //单条删除心愿
    @GetMapping("wishDeleteone/{id}")
    public String wishDeleteone(@PathVariable("id") int wish_id){
        wishService.deleteWish(wish_id);
        return "redirect:/wish/wishList";
    }
    //批量删除心愿
    @PostMapping("wishDeletemore")
    public String wishDeletemore(@Param("listid") String listid){
        String str = listid.replace("\"","");
        str = str.substring(1,str.length()-1);
        int id;
        StringTokenizer st = new StringTokenizer(str,",");
        while (st.hasMoreTokens()){
            id = Integer.valueOf(st.nextToken());
            wishService.deleteWish(id);
        }
        return "redirect:/wish/wishList";
    }
    //按条件查询心愿的相关信息
    @PostMapping("wishConditionList")
    public String wishConditionList(@RequestParam("modules") String modules,
                                      @RequestParam("keywordtxt") String keywordtxt,
                                      @RequestParam("keywordnum") String keywordnum,
                                      @RequestParam("keywordnum1") String keywordnum1,
                                      Model model){
        int value = Integer.valueOf(modules);
        List<Wish> wishes = wishService.selectAll();
        if (keywordnum != "" && value == 1){//id
            int keynum = Integer.valueOf(keywordnum);
            wishes = wishService.selectAllByID(keynum);
        }else if (keywordtxt != "" && value == 2){//内容
            wishes = wishService.selectAllByContent(keywordtxt);
        }else if (keywordnum1 != "" && value == 3){
            int keynum1 = Integer.valueOf(keywordnum1);//状态
            wishes = wishService.selectAllByStatus(keynum1);
        }
        List<WishView> wishViews = new ArrayList<>();
        for (int i=0;i<wishes.size();i++){
            WishView wishView = new WishView();
            wishView.setWishId(wishes.get(i).getWishId());
            wishView.setWriterName(userService.selestOneByID(wishes.get(i).getWriterId()).getUserName());
            wishView.setTagMeaning(wishService.selectTagByID(wishes.get(i).getTagId()).getTagMeaning());
            wishView.setCreateTime(wishes.get(i).getCreateTime());
            wishView.setWishContent(wishes.get(i).getWishContent());
            wishView.setWishStatus(wishes.get(i).getWishStatus());
            wishView.setPickerName(userService.selestOneByID(wishes.get(i).getPickerId()).getUserName());
            wishViews.add(wishView);
        }
        model.addAttribute("wishViews",wishViews);
        return "wishlist";
    }

    //---------------------------------
    //查询所有举报信息
    @RequestMapping("wishReportList")
    public String wishReportList(Model model){
        List<WishReport> wishReports = wishService.selectReportAll();
        model.addAttribute("wishReports",wishReports);
        return "wishreportlist";
    }
    //单条删除心愿举报信息
    @GetMapping("wishReportDeleteone/{id}")
    public String wishReportDeleteone(@PathVariable("id") int report_id){
        wishService.deleteReportWish(report_id);
        return "redirect:/wish/wishReportList";
    }
    //批量删除心愿举报信息
    @PostMapping("wishReportDeletemore")
    public String wishReportDeletemore(@Param("listid") String listid){
        String str = listid.replace("\"","");
        str = str.substring(1,str.length()-1);
        int id;
        StringTokenizer st = new StringTokenizer(str,",");
        while (st.hasMoreTokens()){
            id = Integer.valueOf(st.nextToken());
            wishService.deleteReportWish(id);
        }
        return "redirect:/wish/wishReportList";
    }
    //查看被举报心愿详，可执行删除操作或者忽略
    @GetMapping("wishReportView/{id}")
    public String wishReportView(@PathVariable("id") int wish_id,Model model){
        Wish wish = wishService.selectByID(wish_id);
        WishView wishView = new WishView();
        wishView.setWishId(wish.getWishId());
        wishView.setWriterName(userService.selestOneByID(wish.getWriterId()).getUserName());
        wishView.setTagMeaning(wishService.selectTagByID(wish.getTagId()).getTagMeaning());
        wishView.setCreateTime(wish.getCreateTime());
        wishView.setWishContent(wish.getWishContent());
        wishView.setWishStatus(wish.getWishStatus());
        wishView.setPickerName(userService.selestOneByID(wish.getPickerId()).getUserName());
        model.addAttribute("wishView",wishView);
        return "wishReView";
    }
    //删除被举报的心愿
    @PostMapping("wishreportedDelete")
    public String wishreportedDelete(@Param("wishreportedid") String wishreportedid,Model model){
        int wish_id = Integer.valueOf(wishreportedid);
        wishService.deleteWish(wish_id);
        model.addAttribute("message","删除被举报心愿信息成功！");
        return "message";
    }
    //条件查询
    @PostMapping("wishReportConditionList")
    public String wishReportConditionList(@RequestParam("modules") String modules,
                                    @RequestParam("keywordtxt") String keywordtxt,
                                    @RequestParam("keywordnum") String keywordnum,
                                    Model model){
        int value = Integer.valueOf(modules);
        List<WishReport> wishReports = wishService.selectReportAll();
        if (keywordnum != "" && value == 1){//id
            int keynum = Integer.valueOf(keywordnum);
            wishReports = wishService.selectReportByID(keynum);
        }else if (keywordtxt != "" && value == 2){//内容
            wishReports = wishService.selectReportByReason(keywordtxt);
        }
        model.addAttribute("wishReports",wishReports);
        return "wishreportlist";
    }

    //根据用户喜欢的标签进行分类
    @RequestMapping("wishchartturn")
    public String wishchartturn(){
        return "wishcharts";
    }
    @PostMapping("wishCard")
    @ResponseBody
    public List<HashMap> wishCard(){
        List<HashMap> map = wishService.classifyTag();
        List<HashMap> maps = new ArrayList<>();
        for (int i=0;i<map.size();i++){
            HashMap hashMap = new HashMap();
            hashMap.put("name",map.get(i).get("tag_meaning"));
            hashMap.put("value",map.get(i).get("COUNT(*)"));
            maps.add(hashMap);
        }
        return maps;
    }
}
