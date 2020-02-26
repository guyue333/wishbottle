package com.hust.software.wishbottle.service.manager;


import com.hust.software.wishbottle.pojo.user.TreeHole;
import com.hust.software.wishbottle.pojo.user.TreeHoleReport;

import java.util.List;

public interface TreeHoleService {

    Integer deleteTreeHole(int treehole_id);
    List<TreeHole> selectAllTreeHole();
    TreeHole selectOneByID(int treehole_id);
    List<TreeHole> selectAllByID(int treehole_id);
    List<TreeHole> selectAllByContent(String treehole_content);
    List<TreeHole> selectAllByStatus(int treehole_status);

    Integer deleteReportTreeHole(int report_id);
    List<TreeHoleReport> selectAllTreeHoleReport();
    List<TreeHoleReport> selectReportByID(int report_id);
    List<TreeHoleReport> selectReportByReason(String report_reason);

}
