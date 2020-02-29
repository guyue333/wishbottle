package com.hust.software.wishbottle.controller.manage;

import com.github.pagehelper.PageInfo;
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

    //单条删除心愿
    @GetMapping("wishDeleteone/{id}")
    public String wishDeleteone(@PathVariable("id") int wish_id){
        wishService.deleteWish(wish_id);
        return "redirect:/wish/wishConditionList";
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
        return "redirect:/wish/wishConditionList";
    }
    //按条件查询心愿的相关信息
    @RequestMapping("wishConditionList")
    public String wishConditionList(@RequestParam(value="pageIndex",defaultValue="1") Integer pageIndex,
                                    @RequestParam(value="pageSize",defaultValue="9") Integer pageSize,
                                    @RequestParam(value="modules",defaultValue="0") String modules,
                                      @RequestParam(value="keywordtxt",defaultValue="") String keywordtxt,
                                      @RequestParam(value="keywordnum",defaultValue="0") String keywordnum,
                                      @RequestParam(value="keywordnum1",defaultValue="-1") String keywordnum1,
                                      Model model){
        int value = Integer.valueOf(modules);
        int keynum = Integer.valueOf(keywordnum);
        int keynum1 = Integer.valueOf(keywordnum1);
        PageInfo<Wish> wishes = wishService.selectAll(pageIndex,pageSize);//无条件
        if (keynum != 0 && value == 1){//id
            wishes = wishService.selectAllByID(pageIndex,pageSize,keynum);
        }else if (keywordtxt != "" && value == 2){//内容
            wishes = wishService.selectAllByContent(pageIndex,pageSize,keywordtxt);
        }else if (keynum1 != -1 && value == 3){//状态
            wishes = wishService.selectAllByStatus(pageIndex,pageSize,keynum1);
        }
        List<WishView> wishViews = new ArrayList<>();
        for (int i=0;i<wishes.getList().size();i++){
            WishView wishView = new WishView();
            wishView.setWishId(wishes.getList().get(i).getWishId());
            wishView.setWriterName(userService.selestOneByID(wishes.getList().get(i).getWriterId()).getUserName());
            wishView.setTagMeaning(wishService.selectTagByID(wishes.getList().get(i).getTagId()).getTagMeaning());
            wishView.setCreateTime(wishes.getList().get(i).getCreateTime());
            wishView.setWishContent(wishes.getList().get(i).getWishContent());
            wishView.setWishStatus(wishes.getList().get(i).getWishStatus());
            wishView.setPickerName(userService.selestOneByID(wishes.getList().get(i).getPickerId()).getUserName());
            wishViews.add(wishView);
        }
        model.addAttribute("wishViews",wishViews);
        model.addAttribute("info",wishes);
        return "wishlist";
    }

    //---------------------------------
    //单条删除心愿举报信息
    @GetMapping("wishReportDeleteone/{id}")
    public String wishReportDeleteone(@PathVariable("id") int report_id){
        wishService.deleteReportWish(report_id);
        return "redirect:/wish/wishReportConditionList";
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
        return "redirect:/wish/wishReportConditionList";
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
    @RequestMapping("wishReportConditionList")
    public String wishReportConditionList(@RequestParam(value="pageIndex",defaultValue="1") Integer pageIndex,
                                          @RequestParam(value="pageSize",defaultValue="9") Integer pageSize,
                                          @RequestParam(value="modules",defaultValue="0") String modules,
                                          @RequestParam(value="keywordtxt",defaultValue="") String keywordtxt,
                                          @RequestParam(value="keywordnum",defaultValue="0") String keywordnum,
                                          Model model){
        int value = Integer.valueOf(modules);
        int keynum = Integer.valueOf(keywordnum);
        PageInfo<WishReport> wishReports = wishService.selectReportAll(pageIndex,pageSize);
        if (keynum != 0 && value == 1){//id
            wishReports = wishService.selectReportByID(pageIndex,pageSize,keynum);
        }else if (keywordtxt != "" && value == 2){//内容
            wishReports = wishService.selectReportByReason(pageIndex,pageSize,keywordtxt);
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
