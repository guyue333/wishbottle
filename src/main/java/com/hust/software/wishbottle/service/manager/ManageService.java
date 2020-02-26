package com.hust.software.wishbottle.service.manager;


import com.hust.software.wishbottle.pojo.manage.Manage;

import java.util.List;

public interface ManageService {
    Manage verifyLogin(String account, String password);
    Integer isSuperManager(int id);
    Integer addManager(Manage manage);
    Integer deleteManager(int id);
    Integer updateManager(Manage manage);
    Manage selectOne(int id);
    List<Manage> selectAll();
    Manage selectOneByName(String manager_account);
    List<Manage> selectListByID(int id);
    List<Manage> selectListByName(String manager_account);
    List<Manage> selectListByType(int manager_type);
}
