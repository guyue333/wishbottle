package com.hust.software.wishbottle.pojo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeholeInfo {   //树洞信息实体类，不对应数据库中的数据表，用于在页面上显示信息

    //树洞作者id
    private Integer userId;

    //作者名称
    private String userName;

    //作者头像
    private String userAvatar;

    //树洞id
    private Integer treeholeId;

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    //树洞内容
    private String treeholeContent;

    //树洞状态（0-正常显示，1-被作者删除，2-被管理员删除）
    private Integer treeholeStatus;

    //树洞点赞数
    private Integer likedNumber;

    //树洞评论数
    private Integer replyNumber;
}
