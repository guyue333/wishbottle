package com.hust.software.wishbottle.controller.user;

import com.hust.software.wishbottle.pojo.user.Tag;
import com.hust.software.wishbottle.service.user.UserTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserTagController {

    @Autowired
    UserTagService userTagService;

    /**
     * 返回所有标签信息
     * @return
     * @throws Exception
     */
    @GetMapping("/tags")
    public List<Tag> findAllTags() throws Exception{
        List<Tag> tags = userTagService.findAll();
        return tags;
    }
}
