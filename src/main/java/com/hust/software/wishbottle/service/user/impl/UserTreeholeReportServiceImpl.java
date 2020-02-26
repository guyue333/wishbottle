package com.hust.software.wishbottle.service.user.impl;

import com.hust.software.wishbottle.mapper.user.UserTreeholeReportMapper;
import com.hust.software.wishbottle.pojo.user.TreeHoleReport;
import com.hust.software.wishbottle.service.user.UserTreeholeReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTreeholeReportServiceImpl implements UserTreeholeReportService {
    @Autowired
    UserTreeholeReportMapper userTreeholeReportMapper;

    /**
     * 新增一条树洞举报记录
     * @param treeholeReport
     * @return
     */
    @Override
    public int addTreeholeReport(TreeHoleReport treeholeReport) {
        return userTreeholeReportMapper.addTreeholeReport(treeholeReport);
    }
}
