package com.hust.software.wishbottle.controller.user;

import com.hust.software.wishbottle.pojo.user.WishReport;
import com.hust.software.wishbottle.service.user.UserWishReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserWishReportController {
    @Autowired
    UserWishReportService userWishReportService;

    /**
     * 新增一条心愿举报记录
     * @param wishReport
     * @return
     * @throws Exception
     */
    @PostMapping("/wishReports")
    public String reportWish(@RequestBody WishReport wishReport) throws Exception{
        userWishReportService.addWishReport(wishReport);

        return "report success";
    }
}
