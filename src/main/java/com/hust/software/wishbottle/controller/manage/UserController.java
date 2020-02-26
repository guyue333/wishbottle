package com.hust.software.wishbottle.controller.manage;

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

    //查询所有用户信息
    @RequestMapping("userList")
    public String userList(Model model){
        List<User> users = userService.selectAll();

        model.addAttribute("users",users);
        return "userlist";
    }
    //按条件查询
    //管理员条件查询
    @PostMapping("userConditionList")
    public String userConditionList(@RequestParam("modules") String modules,
                                      @RequestParam("usertxt") String usertxt,
                                      @RequestParam("usernum") String usernum,
                                      @RequestParam("usersex") String usersex,
                                      Model model){
        int value = Integer.valueOf(modules);
        List<User> users = userService.selectAll();
        if(usernum != "" && (value == 1 || value == 3)){//id,age
            int keynum = Integer.valueOf(usernum);
            if(value == 1){
                users = userService.selectByID(keynum);
            }else {
                users = userService.selectByAge(keynum);
            }
        }else if (usertxt != "" && (value == 2 || value == 5)){//用户名,省份
            if(value== 2){
                users = userService.selectByName(usertxt);
            }else {
                users = userService.selectByProvince(usertxt);
            }
        }else if (usersex != "" && value == 4){
            int keynum1 = Integer.valueOf(usersex);
            users = userService.selectByGender(keynum1);
        }
        model.addAttribute("users",users);
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
