package com.hust.software.wishbottle.mapper;

import com.hust.software.wishbottle.pojo.WishReply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WishReplyMapper {

    /**
     * 向数据库中插入一条心愿评论
     * @param wishReply
     * @return
     */
    @Insert("insert into wishreply(wish_id,replyer_id,reply_content,create_time) " +
            "values(#{wishId},#{replyerId},#{replyContent},#{createTime})")
    public int addReply(WishReply wishReply);

    /**
     * 根据心愿id，查询对应的评论
     * @param wishId
     * @return
     */
    @Select("select * from wishreply where wish_id = #{wishId} order by create_time asc")
    public List<WishReply> findAllReplyById(int wishId);
}
