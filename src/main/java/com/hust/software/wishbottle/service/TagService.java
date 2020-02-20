package com.hust.software.wishbottle.service;

import com.hust.software.wishbottle.pojo.Tag;

import java.util.List;

public interface TagService {

    public Tag selectTagById(int id);

    public List<Tag> findAll();
}
