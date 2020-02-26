package com.hust.software.wishbottle.controller.manage;

import com.hust.software.wishbottle.pojo.user.TreeHole;
import com.hust.software.wishbottle.pojo.user.TreeHoleReport;
import com.hust.software.wishbottle.service.manager.TreeHoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.StringTokenizer;

@Controller
@RequestMapping("treehole")
public class TreeHoleController {

    @Autowired
    private TreeHoleService treeHoleService;

    //查询所有树洞信息
    @RequestMapping("treeholeList")
    public String treeholeList(Model model){
        List<TreeHole> treeHoles = treeHoleService.selectAllTreeHole();
        model.addAttribute("treeHoles",treeHoles);
        return "treeholelist";
    }
    //单条删除树洞信息
    @GetMapping("treeholeDeleteone/{id}")
    public String treeholeDeleteone(@PathVariable("id") int treehole_id){
        treeHoleService.deleteTreeHole(treehole_id);
        return "redirect:/treehole/treeholeList";
    }
    //批量删除树洞信息
    @PostMapping("treeholeDeletemore")
    public String treeholeDeletemore(@Param("listid") String listid){
        String str = listid.replace("\"","");
        str = str.substring(1,str.length()-1);
        int id;
        StringTokenizer st = new StringTokenizer(str,",");
        while (st.hasMoreTokens()){
            id = Integer.valueOf(st.nextToken());
            treeHoleService.deleteTreeHole(id);
        }
        return "redirect:/treehole/treeholeList";
    }
    //按条件查询心愿的相关信息
    @PostMapping("treeholeConditionList")
    public String treeholeConditionList(@RequestParam("modules") String modules,
                                    @RequestParam("keywordtxt") String keywordtxt,
                                    @RequestParam("keywordnum") String keywordnum,
                                    @RequestParam("keywordnum1") String keywordnum1,
                                    Model model){
        int value = Integer.valueOf(modules);
        List<TreeHole> treeHoles = treeHoleService.selectAllTreeHole();
        if (keywordnum != "" && value == 1){//id
            int keynum = Integer.valueOf(keywordnum);
            treeHoles = treeHoleService.selectAllByID(keynum);
        }else if (keywordtxt != "" && value == 2){//内容
            treeHoles = treeHoleService.selectAllByContent(keywordtxt);
        }else if (keywordnum1 != "" && value == 3){
            int keynum1 = Integer.valueOf(keywordnum1);//状态
            treeHoles = treeHoleService.selectAllByStatus(keynum1);
        }
        model.addAttribute("treeHoles",treeHoles);
        return "treeholelist";
    }

    //---------------------------------------------------------

    //查询所有举报树洞信息
    @RequestMapping("treeHoleReportList")
    public String treeHoleReportList(Model model){
        List<TreeHoleReport> treeHoleReports = treeHoleService.selectAllTreeHoleReport();
        model.addAttribute("treeHoleReports",treeHoleReports);
        return "treeholereportlist";
    }

    //单条删除树洞举报信息
    @GetMapping("treeholeReportDeleteone/{id}")
    public String treeholeReportDeleteone(@PathVariable("id") int report_id){
        treeHoleService.deleteReportTreeHole(report_id);
        return "redirect:/treehole/treeHoleReportList";
    }
    //批量删除树洞举报信息
    @PostMapping("treeholeReportDeleteone")
    public String treeholeReportDeletemore(@Param("listid") String listid){
        String str = listid.replace("\"","");
        str = str.substring(1,str.length()-1);
        int id;
        StringTokenizer st = new StringTokenizer(str,",");
        while (st.hasMoreTokens()){
            id = Integer.valueOf(st.nextToken());
            treeHoleService.deleteReportTreeHole(id);
        }
        return "redirect:/treehole/treeHoleReportList";
    }
    //条件查询
    @PostMapping("treeholeReportConditionList")
    public String treeholeReportConditionList(@RequestParam("modules") String modules,
                                          @RequestParam("keywordtxt") String keywordtxt,
                                          @RequestParam("keywordnum") String keywordnum,
                                          Model model){
        int value = Integer.valueOf(modules);
        List<TreeHoleReport> treeHoleReports = treeHoleService.selectAllTreeHoleReport();
        if (keywordnum != "" && value == 1){//id
            int keynum = Integer.valueOf(keywordnum);
            treeHoleReports = treeHoleService.selectReportByID(keynum);
        }else if (keywordtxt != "" && value == 2){//内容
            treeHoleReports = treeHoleService.selectReportByReason(keywordtxt);
        }
        model.addAttribute("treeHoleReports",treeHoleReports);
        return "treeholereportlist";
    }

    //查看被举报树洞详，可执行删除操作或者忽略
    @GetMapping("treeholeReportView/{id}")
    public String treeholeReportView(@PathVariable("id") int treehole_id,Model model){
        TreeHole treeHole = treeHoleService.selectOneByID(treehole_id);
        model.addAttribute("treeHole",treeHole);
        return "treeholeReView";
    }
    //删除被举报的树洞
    @PostMapping("treeholereportedDelete")
    public String treeholereportedDelete(@Param("treeholereportedid") String treeholereportedid,Model model){
        int treehole_id = Integer.valueOf(treeholereportedid);
        treeHoleService.deleteTreeHole(treehole_id);
        model.addAttribute("message","删除被举报树洞信息成功！");
        return "message";
    }
}
