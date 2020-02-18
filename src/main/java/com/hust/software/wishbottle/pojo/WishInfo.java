package com.hust.software.wishbottle.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishInfo {  //心愿信息实体类，不对应数据库中的数据表，用于在页面上显示信息

    //用户id
    private Integer userId;

    //用户昵称
    private String userName;

    //用户头像地址
    private String userAvatar;

    //心愿id
    private Integer wishId;

    //心愿创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    //心愿内容
    private String wishContent;

    //心愿状态（0-正常，1-被捞取，2-被作者删除，3-被捡到的人仍回去，4-被管理员删除）
    private Integer wishStatus;

    //捡到该心愿的用户id
    private Integer pickerId;

    //标签id
    private Integer tagId;

    //标签内容
    private String tagMeaning;
}
