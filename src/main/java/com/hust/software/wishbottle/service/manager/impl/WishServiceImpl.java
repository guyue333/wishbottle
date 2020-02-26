package com.hust.software.wishbottle.service.manager.impl;

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
    public List<Wish> selectAll(){
        return wishMapper.selectAll();
    }
    @Override
    public List<Wish> selectAllByID(int wish_id){
        return wishMapper.selectAllByID(wish_id);
    }
    @Override
    public List<Wish> selectAllByContent(String wish_content){
        return wishMapper.selectAllByContent(wish_content);
    }
    @Override
    public List<Wish> selectAllByStatus(int wish_status){
        return wishMapper.selectAllByStatus(wish_status);
    }
//------------------------------------
    @Override
    public Integer deleteReportWish(int report_id){
        return wishMapper.deleteReportWish(report_id);
    }
    @Override
    public List<WishReport> selectReportByID(int report_id){
        return wishMapper.selectReportByID(report_id);
    }
    @Override
    public List<WishReport> selectReportByReason(String report_reason){ return wishMapper.selectReportByReason(report_reason);}
    @Override
    public List<WishReport> selectReportAll(){
        return wishMapper.selectReportAll();
    }
    @Override
    public List<HashMap> classifyTag(){return wishMapper.classifyTag();}
}
