package com.hust.software.wishbottle.mapper.message;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public interface WxSendMessageMapper {

    //在心愿回复表中，查询replyer_id不等于用户自身id的，并且评论状态为2的记录
    // 如果存在，说明有新的回复记录，给用户发送一条消息通知
    @Select("select count(1) from wishreply where status = 2 and replyer_id != #{userId}")
    public int checkReplyRecord(int userId);

    /**
     * 消息推送完以后，修改数据表中的记录状态为1
     * @param userId
     * @return
     */
    @Update("update wishreply set status = 1 where status = 2 AND replyer_id != #{userId}")
    public int UpdateReplyStatus(int userId);
}
