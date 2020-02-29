package com.hust.software.wishbottle.service.manager.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hust.software.wishbottle.mapper.manager.ManageMapper;
import com.hust.software.wishbottle.pojo.manage.Manage;
import com.hust.software.wishbottle.service.manager.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    private ManageMapper manageMapper;

    @Override
    public Manage verifyLogin(String account, String password){
        return manageMapper.verifyLogin(account,password);
    }
    @Override
    public Integer isSuperManager(int id){
        return manageMapper.isSuperManager(id);
    }
    @Override
    public Integer addManager(Manage manage){
        return manageMapper.addManager(manage);
    }
    @Override
    public Integer deleteManager(int id){
        return manageMapper.deleteManager(id);
    }
    @Override
    public Integer updateManager(Manage manage){
        return manageMapper.updateManager(manage);
    }
    @Override
    public Manage selectOne(int id){
        return manageMapper.selectOne(id);
    }
    @Override
    public PageInfo<Manage> selectAll(Integer pageIndex, Integer pageSize){
        PageHelper.startPage(pageIndex,pageSize);
        List<Manage> manages = manageMapper.selectAll();
        PageInfo<Manage> info = new PageInfo<Manage>(manages);
        return info;
    }
    @Override
    public Manage selectOneByName(String manager_account){
        return manageMapper.selectOneByName(manager_account);
    }
    @Override
    public PageInfo<Manage> selectListByID(Integer pageIndex, Integer pageSize,int id){
        PageHelper.startPage(pageIndex,pageSize);
        List<Manage> manages = manageMapper.selectListByID(id);
        PageInfo<Manage> info = new PageInfo<Manage>(manages);
        return info;
    }
    @Override
    public PageInfo<Manage> selectListByName(Integer pageIndex, Integer pageSize,String manager_account){
        PageHelper.startPage(pageIndex,pageSize);
        List<Manage> manages = manageMapper.selectListByName(manager_account);
        PageInfo<Manage> info = new PageInfo<Manage>(manages);
        return info;
    }
    @Override
    public PageInfo<Manage> selectListByType(Integer pageIndex, Integer pageSize,int manager_type){
        PageHelper.startPage(pageIndex,pageSize);
        List<Manage> manages = manageMapper.selectListByType(manager_type);
        PageInfo<Manage> info = new PageInfo<Manage>(manages);
        return info;
    }
}
