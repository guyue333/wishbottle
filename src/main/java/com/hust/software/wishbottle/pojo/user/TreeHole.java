package com.hust.software.wishbottle.pojo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeHole {      //实体类，对应数据库中的treehole表

    //树洞编号
    private Integer treeholeId;

    //作者编号
    private Integer writerId;

    //树洞创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    //树洞内容
    private String treeholeContent;

    //树洞状态（0-正常显示，1-被作者删除，2-被管理员删除）
    private Integer treeholeStatus;

    //树洞点赞数
    private Integer likedNumber;
}
