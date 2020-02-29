package com.hust.software.wishbottle.service.message.impl;

import com.hust.software.wishbottle.mapper.message.WxSendMessageMapper;
import com.hust.software.wishbottle.pojo.message.TemplateData;
import com.hust.software.wishbottle.pojo.message.WxMessVo;
import com.hust.software.wishbottle.service.message.WxSendMessageService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
@Service
public class WxSendMessageServiceImpl implements WxSendMessageService {

    @Autowired
    WxSendMessageMapper wxSendMessageMapper;

    //在心愿回复表中，查询replyer_id不等于用户自身id的，并且评论状态为2的记录
    // 如果存在，说明有新的回复记录，给用户发送一条消息通知
    @Override
    public int checkReplyRecord(int userId) {
        return wxSendMessageMapper.checkReplyRecord(userId);
    }

    @Override
    public int UpdateReplyStatus(int userId) {
        return wxSendMessageMapper.UpdateReplyStatus(userId);
    }


}
