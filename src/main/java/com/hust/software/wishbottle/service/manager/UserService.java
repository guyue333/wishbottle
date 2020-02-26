package com.hust.software.wishbottle.service.manager;

import com.hust.software.wishbottle.pojo.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserService {
    User selestOneByID(int user_id);
    List<User> selectByID(int user_id);
    List<User> selectByName(String user_name);
    List<User> selectByAge(int user_age);
    List<User> selectByGender(int user_gender);
    List<User> selectByProvince(String user_province);
    List<User> selectAll();
    Integer deleteUser(int user_id);
    List<HashMap> classifyProvince();
    List<HashMap> classifyAge();
}
