package com.hust.software.wishbottle.pojo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishReply {   //实体类，对应数据库中的wishreply表

    //心愿回复id
    private Integer replyId;

    //心愿id
    private Integer wishId;

    //评论者id
    private Integer replyerId;

    //评论内容
    private String replyContent;

    //评论时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}
