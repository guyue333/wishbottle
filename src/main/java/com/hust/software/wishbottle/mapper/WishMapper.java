package com.hust.software.wishbottle.mapper;

import com.hust.software.wishbottle.pojo.Wish;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WishMapper {

    /**
     * 根据userId查询用户发布的 状态为0的 所有心愿（按照时间降序排列）
     * @param userId
     * @return
     */
    @Select("select * from wish where writer_id = #{userId} and wish_status = 0 order by create_time desc")
    public List<Wish> findAllByUserId(int userId);

    /**
     * 根据pickerId查询用户捞到的  状态为0的 所有心愿（按照时间降序排列）
     * @param pickerId
     * @return
     */
    @Select("select * from wish where picker_id = #{pickerId} and wish_status = 0 order by create_time desc")
    public List<Wish> findAllByPickerId(int pickerId);

    /**
     * 增加一条心愿
     * @param wish
     * @return
     */
    @Insert("insert into wish(writer_id,tag_id,create_time,wish_content,wish_status,picker_id) " +
            "values (#{writerId},#{tagId},#{createTime},#{wishContent},#{wishStatus},#{pickerId})")
    public int addWish(Wish wish);
}
