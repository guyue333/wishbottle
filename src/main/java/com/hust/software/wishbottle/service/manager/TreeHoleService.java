package com.hust.software.wishbottle.service.manager;


import com.github.pagehelper.PageInfo;
import com.hust.software.wishbottle.pojo.user.TreeHole;
import com.hust.software.wishbottle.pojo.user.TreeHoleReport;

public interface TreeHoleService {

    Integer deleteTreeHole(int treehole_id);
    PageInfo<TreeHole> selectAllTreeHole(Integer pageIndex, Integer pageSize);
    TreeHole selectOneByID(int treehole_id);
    PageInfo<TreeHole> selectAllByID(Integer pageIndex, Integer pageSize, int treehole_id);
    PageInfo<TreeHole> selectAllByContent(Integer pageIndex, Integer pageSize, String treehole_content);
    PageInfo<TreeHole> selectAllByStatus(Integer pageIndex, Integer pageSize, int treehole_status);

    Integer deleteReportTreeHole(int report_id);
    PageInfo<TreeHoleReport> selectAllTreeHoleReport(Integer pageIndex, Integer pageSize);
    PageInfo<TreeHoleReport> selectReportByID(Integer pageIndex, Integer pageSize, int report_id);
    PageInfo<TreeHoleReport> selectReportByReason(Integer pageIndex, Integer pageSize, String report_reason);

}
