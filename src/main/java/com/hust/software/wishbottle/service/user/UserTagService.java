package com.hust.software.wishbottle.service.user;


import com.hust.software.wishbottle.pojo.user.Tag;

import java.util.List;

public interface UserTagService {

    public Tag selectTagById(int id);

    public List<Tag> findAll();
}
