package com.hust.software.wishbottle.service.manager.impl;

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
    public List<Manage> selectAll(){
        return manageMapper.selectAll();
    }
    @Override
    public Manage selectOneByName(String manager_account){
        return manageMapper.selectOneByName(manager_account);
    }
    @Override
    public List<Manage> selectListByID(int id){
        return manageMapper.selectListByID(id);
    }
    @Override
    public List<Manage> selectListByName(String manager_account){
        return manageMapper.selectListByName(manager_account);
    }
    @Override
    public List<Manage> selectListByType(int manager_type){
        return manageMapper.selectListByType(manager_type);
    }
}
