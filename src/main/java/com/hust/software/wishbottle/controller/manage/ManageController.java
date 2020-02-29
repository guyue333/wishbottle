package com.hust.software.wishbottle.controller.manage;

import com.github.pagehelper.PageInfo;
import com.hust.software.wishbottle.pojo.manage.Manage;
import com.hust.software.wishbottle.service.manager.ManageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

@Controller
@RequestMapping("/manage")
public class ManageController {

    @Autowired
    private ManageService manageService;

    //进入初始页面
    @RequestMapping("index")
    public String index(){
        return "login";
    }
    //localhost:8080/manage/index

    //管理员登录，登录成功进入主页，失败给出提示
    @PostMapping("manageLogin")
    public String manageLogin(@RequestParam("account") String account,
                              @RequestParam("password") String password, Model model){
        Manage manage = manageService.verifyLogin(account,password);
        //System.out.println("manage: " + manage);
        if (manage == null){
            model.addAttribute("error","用户名错误或不存在");
            return "login";
        }
        model.addAttribute("verify",manage);
        return "index";
    }

    //管理员退出登录
    @GetMapping("manageOut")
    public String manageOut(){
        System.out.println("管理员：成功退出登录");
        return "login";
    }

    //跳转管理员添加页面
    @GetMapping("manageAdd")
    public String manageAdd(Model model){
        Manage manage = new Manage();
        manage.setManagerId(0);
        model.addAttribute("manage",manage);
        return "addmanager";
    }

    //添加、修改管理员信息
    @PostMapping("manageAddInfo")
    public String manageAddInfo(@RequestParam("manageUpdateName") String nameValue,
                                @RequestParam("manageUpdatePwd") String pwdValue,
                                Manage manage,Model model){
        Manage managetest = manageService.selectOneByName(manage.getManagerAccount());
        if (manage.getManagerId() == 0){//添加
            if(managetest == null) {
                manage.setManagerType(0);
                manageService.addManager(manage);
                model.addAttribute("message", "添加管理员 " + manage.getManagerAccount() + " 信息成功，刷新后查看详情");
            }else {
                model.addAttribute("message","管理员用户名已经存在，请重新添加信息！");
            }
        }else{//根据id修改
            if (managetest == null){
                manageService.updateManager(manage);
                model.addAttribute("message","修改管理员信息成功！");
            }else{
                if (manage.getManagerAccount().equals(nameValue)){
                    if(manage.getManagerPassword().equals(pwdValue)){
                        model.addAttribute("message","对管理员信息未做修改！");
                    }else {
                        manageService.updateManager(manage);
                        model.addAttribute("message","修改管理员信息成功！");
                    }
                }else {
                    model.addAttribute("message","管理员用户名已经存在，请重新填写修改信息!");
                }
            }
        }
        return "message";
    }

    //跳转管理员修改页面
    @GetMapping("manageUpdate/{id}")
    public String manageUpdate(@PathVariable("id") int manager_id,Model model){
        Manage manage = manageService.selectOne(manager_id);
        model.addAttribute("manage",manage);
        return "addmanager";
    }

    //删除单条管理员信息
    @GetMapping("manageDeleteone/{id}")
    public String manageDeleteone(@PathVariable("id") int manager_id){
        manageService.deleteManager(manager_id);
        return "redirect:/manage/manageConditionList";
    }

    //批量删除
    @PostMapping("manageDeletemore")
    public String manageDeletemore(@Param("listid") String listid){
        String str = listid.replace("\"","");
        str = str.substring(1,str.length()-1);
        int id;
        StringTokenizer st = new StringTokenizer(str,",");
        while (st.hasMoreTokens()){
            id = Integer.valueOf(st.nextToken());
            manageService.deleteManager(id);
        }
        return "redirect:/manage/manageConditionList";
    }

    //管理员条件查询
    @RequestMapping("manageConditionList")
    public String manageConditionList(@RequestParam(value="pageIndex",defaultValue="1") Integer pageIndex,
                                      @RequestParam(value="pageSize",defaultValue="9") Integer pageSize,
                                      @RequestParam(value="modules",defaultValue="0") String modules,
                                      @RequestParam(value="keywordtxt",defaultValue="") String keywordtxt,
                                      @RequestParam(value="keywordnum",defaultValue="0") String keywordnum,
                                      @RequestParam(value="keywordnum1",defaultValue="-1") String keywordnum1,
                                      Model model){
        int value = Integer.valueOf(modules);
        int keynum = Integer.valueOf(keywordnum);
        int keynum1 = Integer.valueOf(keywordnum1);
        PageInfo<Manage> manages = manageService.selectAll(pageIndex,pageSize);//无条件
        if (value == 1 && keynum != 0){
            manages = manageService.selectListByID(pageIndex,pageSize,keynum);
        }else if (value == 2 && keywordtxt != ""){
            manages = manageService.selectListByName(pageIndex,pageSize,keywordtxt);
        }else if (value == 3 && keynum1 != -1){
            manages = manageService.selectListByType(pageIndex,pageSize,keynum1);
        }
        model.addAttribute("manages",manages);
        return "managelist";
    }
}
