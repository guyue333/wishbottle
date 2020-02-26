package com.hust.software.wishbottle.service.user;


import com.hust.software.wishbottle.pojo.user.Wish;

import java.util.List;

public interface UserWishService {

    public List<Wish> findAllByUserId(int userId);

    public List<Wish> findAllByPickerId(int pickerId);

    public int addWish(Wish wish);

    public Wish findOneById(int wishId);

    public Wish digWish(int userId);

    public int updateStatus(int wishId);

    public int updatePicker(int wishId,int pickerId);

    public int deleteBySelf(int wishId);

    public int deleteByPicker(int wishId);
}
