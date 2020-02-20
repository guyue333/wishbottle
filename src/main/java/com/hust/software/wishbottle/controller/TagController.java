package com.hust.software.wishbottle.controller;

import com.hust.software.wishbottle.pojo.Tag;
import com.hust.software.wishbottle.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TagController {

    @Autowired
    TagService tagService;

    /**
     * 返回所有标签信息
     * @return
     * @throws Exception
     */
    @GetMapping("/tags")
    public List<Tag> findAllTags() throws Exception{
        List<Tag> tags = tagService.findAll();
        return tags;
    }
}
