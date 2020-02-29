package com.hust.software.wishbottle.pojo.message;

import com.hust.software.wishbottle.pojo.message.TemplateData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 小程序推送所需要的数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxMessVo {

    private String touser;   //用户openid

    private String template_id;     //订阅消息模板id

    private String page = "pages/myself/myself/home";     //默认跳到用户主页面

    private Map<String, TemplateData> data;   //推送的文字
}
