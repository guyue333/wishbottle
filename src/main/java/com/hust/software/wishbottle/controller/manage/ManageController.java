package com.hust.software.wishbottle.controller.manage;


import com.hust.software.wishbottle.pojo.manage.Manage;
import com.hust.software.wishbottle.service.manager.ManageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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

    //查询所有管理员信息
    @RequestMapping("manageList")
    public String manageList(Model model){
        List<Manage> manages = manageService.selectAll();
        model.addAttribute("manages",manages);
        return "managelist";
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
    public String manageAddInfo(Manage manage,Model model){
        //System.out.println(manage);
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
            }else {
                model.addAttribute("message","管理员用户名已经存在，请重新填写修改信息!");
            }
        }
        return "message";
    }

    //跳转管理员修改页面
    @GetMapping("manageUpdate/{id}")
    public String manageUpdate(@PathVariable("id") int manager_id,Model model){
        //System.out.println(manager_id);
        Manage manage = manageService.selectOne(manager_id);
        model.addAttribute("manage",manage);
        return "addmanager";
    }

    //删除单条管理员信息
    @GetMapping("manageDeleteone/{id}")
    public String manageDeleteone(@PathVariable("id") int manager_id){
        //System.out.println(manager_id);
        manageService.deleteManager(manager_id);
        return "redirect:/manage/manageList";
    }

    //批量删除
    @PostMapping("manageDeletemore")
    public String manageDeletemore(@Param("listid") String listid){
        //System.out.println("listid"+listid);
        String str = listid.replace("\"","");
        str = str.substring(1,str.length()-1);
        //System.out.println(str);
        int id;
        StringTokenizer st = new StringTokenizer(str,",");
        while (st.hasMoreTokens()){
            id = Integer.valueOf(st.nextToken());
            manageService.deleteManager(id);
        }
        return "redirect:/manage/manageList";
    }

    //管理员条件查询
    @PostMapping("manageConditionList")
    public String manageConditionList(@RequestParam("modules") String modules,
                                      @RequestParam("keywordtxt") String keywordtxt,
                                      @RequestParam("keywordnum") String keywordnum,
                                      @RequestParam("keywordnum1") String keywordnum1,
                                      Model model){
        int value = Integer.valueOf(modules);
        List<Manage> manages = manageService.selectAll();
        if (value == 1){
            if (keywordnum == ""){
                manages = manageService.selectAll();
            }else {
                int keynum = Integer.valueOf(keywordnum);
                manages = manageService.selectListByID(keynum);
            }
        }else if (value == 2){
            if (keywordtxt == ""){
                manages = manageService.selectAll();
            }else {
                manages = manageService.selectListByName(keywordtxt);
            }
        }else if (value == 3){
            if (keywordnum1 == ""){
                manages = manageService.selectAll();
            }else {
                int keynum1 = Integer.valueOf(keywordnum1);
                manages = manageService.selectListByType(keynum1);
            }
        }
        model.addAttribute("manages",manages);
        return "managelist";
    }
}
