package com.hust.software.wishbottle.service.user.impl;

import com.hust.software.wishbottle.mapper.user.UserWishReportMapper;
import com.hust.software.wishbottle.pojo.user.WishReport;
import com.hust.software.wishbottle.service.user.UserWishReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserWishReportServiceImpl implements UserWishReportService {
    @Autowired
    UserWishReportMapper userWishReportMapper;

    /**
     * 新增一条心愿举报记录
     * @param wishReport
     * @return
     */
    @Override
    public int addWishReport(WishReport wishReport) {
        return userWishReportMapper.addWishReport(wishReport);
    }
}
