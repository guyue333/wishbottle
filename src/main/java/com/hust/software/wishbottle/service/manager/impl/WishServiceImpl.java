package com.hust.software.wishbottle.service.manager.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hust.software.wishbottle.mapper.manager.WishMapper;
import com.hust.software.wishbottle.pojo.user.Tag;
import com.hust.software.wishbottle.pojo.user.Wish;
import com.hust.software.wishbottle.pojo.user.WishReport;
import com.hust.software.wishbottle.service.manager.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class WishServiceImpl implements WishService {
    @Autowired
    private WishMapper wishMapper;

    @Override
    public Integer deleteWish(int wish_id){
        return wishMapper.deleteWish(wish_id);
    }
    @Override
    public Tag selectTagByID(int tag_id){
        return wishMapper.selectTagByID(tag_id);
    }
    @Override
    public Wish selectByID(int wish_id){
        return wishMapper.selectByID(wish_id);
    }

    @Override
    public PageInfo<Wish> selectAll(Integer pageIndex, Integer pageSize){
        PageHelper.startPage(pageIndex,pageSize);
        List<Wish> wishes = wishMapper.selectAll();
        PageInfo<Wish> info = new PageInfo<Wish>(wishes);
        return info;
    }
    @Override
    public PageInfo<Wish> selectAllByID(Integer pageIndex, Integer pageSize,int wish_id){
        PageHelper.startPage(pageIndex,pageSize);
        List<Wish> wishes = wishMapper.selectAllByID(wish_id);
        PageInfo<Wish> info = new PageInfo<Wish>(wishes);
        return info;
    }
    @Override
    public PageInfo<Wish> selectAllByContent(Integer pageIndex, Integer pageSize,String wish_content){
        PageHelper.startPage(pageIndex,pageSize);
        List<Wish> wishes = wishMapper.selectAllByContent(wish_content);
        PageInfo<Wish> info = new PageInfo<Wish>(wishes);
        return info;
    }
    @Override
    public PageInfo<Wish> selectAllByStatus(Integer pageIndex, Integer pageSize,int wish_status){
        PageHelper.startPage(pageIndex,pageSize);
        List<Wish> wishes = wishMapper.selectAllByStatus(wish_status);
        PageInfo<Wish> info = new PageInfo<Wish>(wishes);
        return info;
    }
//------------------------------------
    @Override
    public Integer deleteReportWish(int report_id){
        return wishMapper.deleteReportWish(report_id);
    }
    @Override
    public PageInfo<WishReport> selectReportByID(Integer pageIndex, Integer pageSize, int report_id){
        PageHelper.startPage(pageIndex,pageSize);
        List<WishReport> wishReports = wishMapper.selectReportByID(report_id);
        PageInfo<WishReport> info = new PageInfo<WishReport>(wishReports);
        return info;
    }
    @Override
    public PageInfo<WishReport> selectReportByReason(Integer pageIndex, Integer pageSize,String report_reason){
        PageHelper.startPage(pageIndex,pageSize);
        List<WishReport> wishReports = wishMapper.selectReportByReason(report_reason);
        PageInfo<WishReport> info = new PageInfo<WishReport>(wishReports);
        return info;
    }
    @Override
    public PageInfo<WishReport> selectReportAll(Integer pageIndex, Integer pageSize){
        PageHelper.startPage(pageIndex,pageSize);
        List<WishReport> wishReports = wishMapper.selectReportAll();
        PageInfo<WishReport> info = new PageInfo<WishReport>(wishReports);
        return info;
    }
    @Override
    public List<HashMap> classifyTag(){return wishMapper.classifyTag();}
}
