package com.hust.software.wishbottle.controller;

import com.hust.software.wishbottle.pojo.WishReport;
import com.hust.software.wishbottle.service.WishReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WishReportController {
    @Autowired
    WishReportService wishReportService;

    /**
     * 新增一条心愿举报记录
     * @param wishReport
     * @return
     * @throws Exception
     */
    @PostMapping("/wishReports")
    public String reportWish(@RequestBody WishReport wishReport) throws Exception{
        wishReportService.addWishReport(wishReport);

        return "report success";
    }
}
