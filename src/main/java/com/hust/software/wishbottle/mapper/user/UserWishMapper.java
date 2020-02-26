package com.hust.software.wishbottle.mapper.user;

import com.hust.software.wishbottle.pojo.user.Wish;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserWishMapper {

    /**
     * 根据userId查询用户发布的 状态为0,1,3的 所有心愿（按照时间降序排列）
     * @param userId
     * @return
     */
    @Select("select * from wish where writer_id = #{userId} and wish_status in (0,1,3) order by create_time desc")
    public List<Wish> findAllByUserId(int userId);

    /**
     * 根据pickerId查询用户捞到的  状态为1,2的 所有心愿（按照时间降序排列）
     * @param pickerId
     * @return
     */
    @Select("select * from wish where picker_id = #{pickerId} and wish_status in (1,2) order by create_time desc")
    public List<Wish> findAllByPickerId(int pickerId);

    /**
     * 增加一条心愿
     * @param wish
     * @return
     */
    @Insert("insert into wish(writer_id,tag_id,create_time,wish_content,wish_status,picker_id) " +
            "values (#{writerId},#{tagId},#{createTime},#{wishContent},#{wishStatus},#{pickerId})")
    public int addWish(Wish wish);

    /**
     * 根据心愿id查询心愿信息
     * @param wishId
     * @return
     */
    @Select("select * from wish where wish_id = #{wishId}")
    public Wish findOneById(int wishId);

    /**
     * 查询数据库中状态为0的非本人发布的最新一条心愿信息
     * @param userId
     * @return
     */
    @Select("select * from wish where wish_status = 0 and writer_id != #{userId} order by create_time desc limit 1")
    public Wish digWish(int userId);

    /**
     * 根据心愿id修改心愿的状态
     * @param wishId
     * @return
     */
    @Update("update wish set wish_status = 1 where wish_id = #{wishId} ")
    public int updateStatus(int wishId);

    /**
     * 根据心愿id，修改心愿拾取者的id
     * @param wishId
     * @param pickerId
     * @return
     */
    @Update("update wish set picker_id = #{pickerId} where wish_id = #{wishId}")
    public int updatePicker(int wishId,int pickerId);

    /**
     * 如果心愿被作者删除，那么修改心愿的状态为2
     * @param wishId
     * @return
     */
    @Update("update wish set wish_status = 2 where wish_id = #{wishId}")
    public int deleteBySelf(int wishId);

    /**
     * 如果心愿被捞到的人删除，那么修改心愿的状态为3
     * @param wishId
     * @return
     */
    @Update("update wish set wish_status = 3 where wish_id = #{wishId}")
    public int deleteByPicker(int wishId);
}
