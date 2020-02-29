package com.hust.software.wishbottle.service.message;

public interface WxSendMessageService {

    public int checkReplyRecord(int userId);

    public int UpdateReplyStatus(int userId);
}
