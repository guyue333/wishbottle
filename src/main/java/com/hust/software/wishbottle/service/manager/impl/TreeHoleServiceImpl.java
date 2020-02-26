package com.hust.software.wishbottle.service.manager.impl;


import com.hust.software.wishbottle.mapper.manager.TreeHoleMapper;
import com.hust.software.wishbottle.pojo.user.TreeHole;
import com.hust.software.wishbottle.pojo.user.TreeHoleReport;
import com.hust.software.wishbottle.service.manager.TreeHoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeHoleServiceImpl implements TreeHoleService {

    @Autowired
    private TreeHoleMapper treeHoleMapper;

    @Override
    public Integer deleteTreeHole(int treehole_id){return treeHoleMapper.deleteTreeHole(treehole_id); }
    @Override
    public List<TreeHole> selectAllTreeHole(){return treeHoleMapper.selectAllTreeHole(); }
    @Override
    public TreeHole selectOneByID(int treehole_id){return treeHoleMapper.selectOneByID(treehole_id);}
    @Override
    public List<TreeHole> selectAllByID(int treehole_id){return treeHoleMapper.selectAllByID(treehole_id);}
    @Override
    public List<TreeHole> selectAllByContent(String treehole_content){return treeHoleMapper.selectAllByContent(treehole_content);}
    @Override
    public List<TreeHole> selectAllByStatus(int treehole_status){return treeHoleMapper.selectAllByStatus(treehole_status);}

    @Override
    public Integer deleteReportTreeHole(int report_id){return treeHoleMapper.deleteReportTreeHole(report_id);}
    @Override
    public List<TreeHoleReport> selectAllTreeHoleReport(){return treeHoleMapper.selectAllTreeHoleReport();}
    @Override
    public List<TreeHoleReport> selectReportByID(int report_id){return treeHoleMapper.selectReportByID(report_id);}
    @Override
    public List<TreeHoleReport> selectReportByReason(String report_reason){return treeHoleMapper.selectReportByReason(report_reason);}
}
