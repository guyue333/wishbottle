package com.hust.software.wishbottle.controller;

import com.hust.software.wishbottle.pojo.TreeholeReport;
import com.hust.software.wishbottle.service.TreeholeReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TreeholeReportController {
    @Autowired
    TreeholeReportService treeholeReportService;

    /**
     * 新增一条树洞举报记录
     * @param treeholeReport
     * @return
     * @throws Exception
     */
    @PostMapping("/treeholeReports")
    public String reportTreehole(@RequestBody TreeholeReport treeholeReport) throws Exception{
        treeholeReportService.addTreeholeReport(treeholeReport);
        return "report success";
    }
}
