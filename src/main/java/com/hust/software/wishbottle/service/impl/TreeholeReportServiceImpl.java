package com.hust.software.wishbottle.service.impl;

import com.hust.software.wishbottle.mapper.TreeholeReportMapper;
import com.hust.software.wishbottle.pojo.TreeholeReport;
import com.hust.software.wishbottle.service.TreeholeReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TreeholeReportServiceImpl implements TreeholeReportService {
    @Autowired
    TreeholeReportMapper treeholeReportMapper;

    /**
     * 新增一条树洞举报记录
     * @param treeholeReport
     * @return
     */
    @Override
    public int addTreeholeReport(TreeholeReport treeholeReport) {
        return treeholeReportMapper.addTreeholeReport(treeholeReport);
    }
}
