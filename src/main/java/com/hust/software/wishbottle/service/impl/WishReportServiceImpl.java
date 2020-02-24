package com.hust.software.wishbottle.service.impl;

import com.hust.software.wishbottle.mapper.WishReportMapper;
import com.hust.software.wishbottle.pojo.WishReport;
import com.hust.software.wishbottle.service.WishReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishReportServiceImpl implements WishReportService {
    @Autowired
    WishReportMapper wishReportMapper;

    /**
     * 新增一条心愿举报记录
     * @param wishReport
     * @return
     */
    @Override
    public int addWishReport(WishReport wishReport) {
        return wishReportMapper.addWishReport(wishReport);
    }
}
