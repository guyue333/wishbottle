package com.hust.software.wishbottle.pojo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {     //实体类，对应数据库中的user表

    //用户id
    private Integer userId;

    //用户openid
    private String userOpenid;

    //用户名
    private String userName;

    //用户年龄
    private Integer userAge;

    //用户性别，0-女，1-男
    private Integer userGender;

    //用户头像地址
    private String userAvatar;

    //用户所在城市（如：武汉）
    private String userCity;

    //用户所在省份（如：湖北）
    private String userProvince;

    //用户生日
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date userBirthday;
}
