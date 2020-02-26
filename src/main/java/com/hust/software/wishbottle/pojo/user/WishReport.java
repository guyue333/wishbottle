package com.hust.software.wishbottle.pojo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishReport {       //实体类，对应数据库中的wishreport表

    //举报记录id
    private Integer reportId;

    //举报的心愿id
    private Integer wishId;

    //举报原因
    private String reportReason;

    //举报时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}
