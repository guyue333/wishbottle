package com.hust.software.wishbottle.service.impl;

import com.hust.software.wishbottle.mapper.TagMapper;
import com.hust.software.wishbottle.pojo.Tag;
import com.hust.software.wishbottle.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;

    /**
     * 根据标签id查询标签内容
     * @param id
     * @return
     */
    @Override
    public Tag selectTagById(int id) {
        return tagMapper.selectTagById(id);
    }

    @Override
    public List<Tag> findAll() {
        return tagMapper.findAll();
    }
}
