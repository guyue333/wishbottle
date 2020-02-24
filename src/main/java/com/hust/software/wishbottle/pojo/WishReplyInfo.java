package com.hust.software.wishbottle.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishReplyInfo {    //心愿回复实体类，不对应数据库中的数据表，用于展示心愿的回复信息

    //心愿评论者id
    private Integer userId;

    //心愿评论者名称
    private String userName;

    //心愿评论者头像
    private String userAvatar;

    //心愿评论id
    private Integer replyId;

    //心愿id
    private Integer wishId;

    //评论内容
    private String replyContent;

    //评论时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}
