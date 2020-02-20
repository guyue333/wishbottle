package com.hust.software.wishbottle.service;

import com.hust.software.wishbottle.pojo.Wish;

import java.util.List;

public interface WishService {

    public List<Wish> findAllByUserId(int userId);

    public List<Wish> findAllByPickerId(int pickerId);

    public int addWish(Wish wish);
}
