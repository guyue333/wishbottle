package com.hust.software.wishbottle.service;

import com.hust.software.wishbottle.pojo.TreeHole;

import java.util.List;

public interface TreeholeService {

    public int addTreehole(TreeHole treeHole);

    public List<TreeHole> findAll();

    public List<TreeHole> findAllByUserId(int userId);

    public int addLikedNumberById(int treeholeId);

    public int minusLikedNumberById(int treeholeId);

    public TreeHole findOneById(int treeholeId);

    public int deleteOneById(int treeholeId);

}
