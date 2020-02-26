package com.hust.software.wishbottle.mapper.user;

import com.hust.software.wishbottle.pojo.user.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserUserMapper {

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    List<User> findAll();

    /**
     * 根据openid查询该用户是否存在
     * @param openid
     * @return
     */
    @Select("select * from user where user_openid = #{openid}")
    public User isRegister(String openid);

    /**
     * 新增用户
     * @param user
     * @return
     */
    @Insert("insert into user(user_openid,user_name,user_age,user_gender,user_avatar,user_city,user_province,user_birthday) values(#{userOpenid},#{userName},#{userAge},#{userGender},#{userAvatar},#{userCity},#{userProvince},#{userBirthday})")
    public int add(User user);


    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @Update("update user set user_name = #{userName},user_age = #{userAge}," +
            "user_gender = #{userGender},user_avatar = #{userAvatar},user_city = #{userCity}," +
            "user_province = #{userProvince},user_birthday = #{userBirthday}" +
            " where user_id = #{userId}")
    public int update(User user);

    /**
     * 根据userid查询对应的用户信息
     * @param userId
     * @return
     */
    @Select("select * from user where user_id = #{userId}")
    public User getUserInfoById(int userId);

}
