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

    public int getReplyNumberById(int treeholeId);

    public int getLikeStatus(int treeholeId,int userId);

    public int addLikeRecord(int treeholeId,int userId);

    public int changeStatusToLike(int treeholeId,int userId);

    public int changeStatusToUnlike(int treeholeId,int userId);

    public int checkRecord(int treeholeId,int userId);
}
