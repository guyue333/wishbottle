package com.hust.software.wishbottle.controller.user;

import com.hust.software.wishbottle.pojo.user.TreeHoleReport;
import com.hust.software.wishbottle.service.user.UserTreeholeReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserTreeholeReportController {
    @Autowired
    UserTreeholeReportService userTreeholeReportService;

    /**
     * 新增一条树洞举报记录
     * @param treeholeReport
     * @return
     * @throws Exception
     */
    @PostMapping("/treeholeReports")
    public String reportTreehole(@RequestBody TreeHoleReport treeholeReport) throws Exception{
        userTreeholeReportService.addTreeholeReport(treeholeReport);
        return "report success";
    }
}
