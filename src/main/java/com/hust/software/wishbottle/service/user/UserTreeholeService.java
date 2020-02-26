package com.hust.software.wishbottle.service.user;


import com.hust.software.wishbottle.pojo.user.TreeHole;

import java.util.List;

public interface UserTreeholeService {

    public int addTreehole(TreeHole treeHole);

    public List<TreeHole> findAll();

    public List<TreeHole> findAllByUserId(int userId);

    public int addLikedNumberById(int treeholeId);

    public int minusLikedNumberById(int treeholeId);

    public TreeHole findOneById(int treeholeId);

    public int deleteOneById(int treeholeId);

}
