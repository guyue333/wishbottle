package com.hust.software.wishbottle.controller.manage;

import com.github.pagehelper.PageInfo;
import com.hust.software.wishbottle.pojo.user.User;
import com.hust.software.wishbottle.service.manager.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //按条件查询
    @RequestMapping("userConditionList")
    public String userConditionList(@RequestParam(value="pageIndex",defaultValue="1") Integer pageIndex,
                                    @RequestParam(value="pageSize",defaultValue="9") Integer pageSize,
                                    @RequestParam(value="modules",defaultValue="0") String modules,
                                      @RequestParam(value="usertxt",defaultValue="") String usertxt,
                                      @RequestParam(value="usernum",defaultValue="0") String usernum,
                                      @RequestParam(value="usersex",defaultValue="0") String usersex,
                                      Model model){
        int value = Integer.valueOf(modules);
        int keynum = Integer.valueOf(usernum);
        int keynum1 = Integer.valueOf(usersex);
        PageInfo<User> users = userService.selectPage(pageIndex,pageSize);//无条件
        if(keynum != 0 && (value == 1 || value == 3)){//id,age
            if(value == 1){
                users = userService.selectByID(pageIndex,pageSize,keynum);
            }else {
                users = userService.selectByAge(pageIndex,pageSize,keynum);
            }
        }else if (usertxt != "" && (value == 2 || value == 5)){//用户名,省份
            if(value== 2){
                users = userService.selectByName(pageIndex,pageSize,usertxt);
            }else {
                users = userService.selectByProvince(pageIndex,pageSize,usertxt);
            }
        }else if (keynum1 != 0 && value == 4){
            users = userService.selectByGender(pageIndex,pageSize,keynum1);
        }
        model.addAttribute("info",users);
        return "userlist";
    }

    //进行图表绘制----------------------
    @RequestMapping("userchartturn")
    public String userchartturn(){
        return "usercharts";
    }
    //用户性别,原始方法
    @PostMapping("userchartTest")
    @ResponseBody
    public List<User> userchartTest(){
        List<User> users = userService.selectAll();
        return users;
    }
    //所在省份
    @PostMapping("userProvince")
    @ResponseBody
    public List<HashMap> userProvince(){
        List<HashMap> map = userService.classifyProvince();
        List<HashMap> maps = new ArrayList<>();
        for (int i=0;i<map.size();i++){
            HashMap hashMap = new HashMap();
            hashMap.put("name",map.get(i).get("user_province"));
            hashMap.put("value",map.get(i).get("count(*)"));
            maps.add(hashMap);
        }
        return maps;
    }
    //用户年龄
    @PostMapping("userAge")
    @ResponseBody
    public List<HashMap> userAge(){
        List<HashMap> map = userService.classifyAge();
        List<HashMap> maps = new ArrayList<>();
        for (int i=0;i<map.size();i++){
            HashMap hashMap = new HashMap();
            hashMap.put("name",map.get(i).get("user_age"));
            hashMap.put("value",map.get(i).get("count(*)"));
            maps.add(hashMap);
        }
        return maps;
    }

}
