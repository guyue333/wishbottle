package com.hust.software.wishbottle.pojo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 定义一个异常类，用来封装异常信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Excep {

    //异常的信息
    private String message;

    //异常发生的路径
    private StringBuffer url;
}
