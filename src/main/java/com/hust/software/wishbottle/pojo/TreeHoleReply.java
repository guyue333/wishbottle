package com.hust.software.wishbottle.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeHoleReply {    //实体类，对应数据库中的treeholereply表

    //树洞回复id
    private Integer replyId;

    //树洞id
    private Integer treeholeId;

    //回复内容
    private String replyContent;

    //回复时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    //评论的点赞数（备选功能）
    private Integer likedNumber;
}
