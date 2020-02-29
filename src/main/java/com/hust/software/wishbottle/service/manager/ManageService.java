package com.hust.software.wishbottle.service.manager;


import com.github.pagehelper.PageInfo;
import com.hust.software.wishbottle.pojo.manage.Manage;

public interface ManageService {
    Manage verifyLogin(String account, String password);
    Integer isSuperManager(int id);
    Integer addManager(Manage manage);
    Integer deleteManager(int id);
    Integer updateManager(Manage manage);
    Manage selectOne(int id);
    Manage selectOneByName(String manager_account);
    //分页
    PageInfo<Manage> selectAll(Integer pageIndex, Integer pageSize);
    PageInfo<Manage> selectListByID(Integer pageIndex, Integer pageSize, int id);
    PageInfo<Manage> selectListByName(Integer pageIndex, Integer pageSize, String manager_account);
    PageInfo<Manage> selectListByType(Integer pageIndex, Integer pageSize, int manager_type);
}
