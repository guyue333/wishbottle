package com.hust.software.wishbottle.service.manager;


import com.github.pagehelper.PageInfo;
import com.hust.software.wishbottle.pojo.user.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserService {
    User selestOneByID(int user_id);
    List<User> selectAll();
    Integer deleteUser(int user_id);
    List<HashMap> classifyProvince();
    List<HashMap> classifyAge();

    //分页
    PageInfo<User> selectPage(Integer pageIndex, Integer pageSize);
    PageInfo<User> selectByID(Integer pageIndex, Integer pageSize, int user_id);
    PageInfo<User> selectByName(Integer pageIndex, Integer pageSize, String user_name);
    PageInfo<User> selectByAge(Integer pageIndex, Integer pageSize, int user_age);
    PageInfo<User> selectByGender(Integer pageIndex, Integer pageSize, int user_gender);
    PageInfo<User> selectByProvince(Integer pageIndex, Integer pageSize, String user_province);
}
