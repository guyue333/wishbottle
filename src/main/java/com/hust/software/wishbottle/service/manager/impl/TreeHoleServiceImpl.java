package com.hust.software.wishbottle.service.manager.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public PageInfo<TreeHole> selectAllTreeHole(Integer pageIndex, Integer pageSize){
        PageHelper.startPage(pageIndex,pageSize);
        List<TreeHole> treeHoles = treeHoleMapper.selectAllTreeHole();
        PageInfo<TreeHole> info = new PageInfo<TreeHole>(treeHoles);
        return info;
    }
    @Override
    public TreeHole selectOneByID(int treehole_id){return treeHoleMapper.selectOneByID(treehole_id);}
    @Override
    public PageInfo<TreeHole> selectAllByID(Integer pageIndex, Integer pageSize,int treehole_id){
        PageHelper.startPage(pageIndex,pageSize);
        List<TreeHole> treeHoles = treeHoleMapper.selectAllByID(treehole_id);
        PageInfo<TreeHole> info = new PageInfo<TreeHole>(treeHoles);
        return info;
    }
    @Override
    public PageInfo<TreeHole> selectAllByContent(Integer pageIndex, Integer pageSize,String treehole_content){
        PageHelper.startPage(pageIndex,pageSize);
        List<TreeHole> treeHoles = treeHoleMapper.selectAllByContent(treehole_content);
        PageInfo<TreeHole> info = new PageInfo<TreeHole>(treeHoles);
        return info;
    }
    @Override
    public PageInfo<TreeHole> selectAllByStatus(Integer pageIndex, Integer pageSize,int treehole_status){
        PageHelper.startPage(pageIndex,pageSize);
        List<TreeHole> treeHoles = treeHoleMapper.selectAllByStatus(treehole_status);
        PageInfo<TreeHole> info = new PageInfo<TreeHole>(treeHoles);
        return info;
    }
    @Override
    public Integer deleteReportTreeHole(int report_id){return treeHoleMapper.deleteReportTreeHole(report_id);}
    @Override
    public PageInfo<TreeHoleReport> selectAllTreeHoleReport(Integer pageIndex, Integer pageSize){
        PageHelper.startPage(pageIndex,pageSize);
        List<TreeHoleReport> treeHoleReports = treeHoleMapper.selectAllTreeHoleReport();
        PageInfo<TreeHoleReport> info = new PageInfo<TreeHoleReport>(treeHoleReports);
        return info;
    }
    @Override
    public PageInfo<TreeHoleReport> selectReportByID(Integer pageIndex, Integer pageSize,int report_id){
        PageHelper.startPage(pageIndex,pageSize);
        List<TreeHoleReport> treeHoleReports = treeHoleMapper.selectReportByID(report_id);
        PageInfo<TreeHoleReport> info = new PageInfo<TreeHoleReport>(treeHoleReports);
        return info;
    }
    @Override
    public PageInfo<TreeHoleReport> selectReportByReason(Integer pageIndex, Integer pageSize,String report_reason){
        PageHelper.startPage(pageIndex,pageSize);
        List<TreeHoleReport> treeHoleReports = treeHoleMapper.selectReportByReason(report_reason);
        PageInfo<TreeHoleReport> info = new PageInfo<TreeHoleReport>(treeHoleReports);
        return info;
    }
}
