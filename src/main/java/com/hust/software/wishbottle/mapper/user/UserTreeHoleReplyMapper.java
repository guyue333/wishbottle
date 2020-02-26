package com.hust.software.wishbottle.mapper.user;

import com.hust.software.wishbottle.pojo.user.TreeHoleReply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserTreeHoleReplyMapper {

    /**
     * 向数据库中插入一条树洞评论
     * @param treeHoleReply
     * @return
     */
    @Insert("insert into treeholereply(treehole_id,reply_content,create_time,liked_number) " +
            "values(#{treeholeId},#{replyContent},#{createTime},#{likedNumber}) ")
    public int addReply(TreeHoleReply treeHoleReply);

    /**
     * 根据树洞id，查询对应的评论
     * @param treeholeId
     * @return
     */
    @Select("select * from treeholereply where treehole_id = #{treeholeId} order by create_time desc")
    public List<TreeHoleReply> findAllReplyById(int treeholeId);

}
