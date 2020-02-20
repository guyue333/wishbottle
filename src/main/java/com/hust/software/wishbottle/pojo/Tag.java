package com.hust.software.wishbottle.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {    //实体类，对应数据库中的tags表

    //标签id
    private Integer tagId;

    //标签内容
    private String tagMeaning;
}
