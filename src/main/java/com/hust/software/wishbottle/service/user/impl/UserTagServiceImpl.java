package com.hust.software.wishbottle.service.user.impl;

import com.hust.software.wishbottle.mapper.user.UserTagMapper;
import com.hust.software.wishbottle.pojo.user.Tag;
import com.hust.software.wishbottle.service.user.UserTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTagServiceImpl implements UserTagService {
    @Autowired
    UserTagMapper userTagMapper;

    /**
     * 根据标签id查询标签内容
     * @param id
     * @return
     */
    @Override
    public Tag selectTagById(int id) {
        return userTagMapper.selectTagById(id);
    }

    @Override
    public List<Tag> findAll() {
        return userTagMapper.findAll();
    }
}
