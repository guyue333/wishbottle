package com.hust.software.wishbottle.service.user.impl;

import com.hust.software.wishbottle.mapper.user.UserTreeholeMapper;
import com.hust.software.wishbottle.pojo.user.TreeHole;
import com.hust.software.wishbottle.service.user.UserTreeholeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTreeholeServiceImpl implements UserTreeholeService {
    @Autowired
    UserTreeholeMapper userTreeholeMapper;

    /**
     * 添加一条树洞信息
     * @param treeHole
     * @return
     */
    @Override
    public int addTreehole(TreeHole treeHole) {
        return userTreeholeMapper.addTreehole(treeHole);
    }

    /**
     * 查询数据库中的所有树洞信息，按照时间降序排列
     * @return
     */
    @Override
    public List<TreeHole> findAll() {
        return userTreeholeMapper.findAll();
    }

    /**
     * 根据用户id查询用户发布过的所有树洞信息
     * @param userId
     * @return
     */
    @Override
    public List<TreeHole> findAllByUserId(int userId) {
        return userTreeholeMapper.findAllByUserId(userId);
    }

    /**
     * 根据树洞id，将对应树洞的点赞数加 1
     * @param treeholeId
     * @return
     */
    @Override
    public int addLikedNumberById(int treeholeId) {
        return userTreeholeMapper.addLikedNumberById(treeholeId);
    }

    /**
     * 根据树洞id，将对应树洞的点赞数减 1
     * @param treeholeId
     * @return
     */
    @Override
    public int minusLikedNumberById(int treeholeId) {
        return userTreeholeMapper.minusLikedNumberById(treeholeId);
    }

    /**
     * 跟据树洞id查询树洞的信息
     * @param treeholeId
     * @return
     */
    @Override
    public TreeHole findOneById(int treeholeId) {
        return userTreeholeMapper.findOneById(treeholeId);
    }

    /**
     * 根据树洞id删除树洞，本质上是修改数据库中树洞的状态
     * @param treeholeId
     * @return
     */
    @Override
    public int deleteOneById(int treeholeId) {
        return userTreeholeMapper.deleteOneById(treeholeId);
    }

    /**
     * 根据树洞id查询树洞的评论数
     * @param treeholeId
     * @return
     */
    @Override
    public int getReplyNumberById(int treeholeId) {
        return userTreeholeMapper.getReplyNumberById(treeholeId);
    }

    /**
     * 根据用户id和树洞id查询用户的点赞状态（1-点赞，0-未点赞）
     * @param treeholeId
     * @param userId
     * @return
     */
    @Override
    public int getLikeStatus(int treeholeId, int userId) {
        return userTreeholeMapper.getLikeStatus(treeholeId,userId);
    }

    /**
     * 插入一条用户点赞记录
     * @param treeholeId
     * @param userId
     * @return
     */
    @Override
    public int addLikeRecord(int treeholeId, int userId) {
        return userTreeholeMapper.addLikeRecord(treeholeId,userId);
    }

    /**
     * 修改点赞状态为1，表示用户点赞
     * @param treeholeId
     * @param userId
     * @return
     */
    @Override
    public int changeStatusToLike(int treeholeId, int userId) {
        return userTreeholeMapper.changeStatusToLike(treeholeId,userId);
    }

    /**
     * 修改点赞状态为0，表示用户取消点赞
     * @param treeholeId
     * @param userId
     * @return
     */
    @Override
    public int changeStatusToUnlike(int treeholeId, int userId) {
        return userTreeholeMapper.changeStatusToUnlike(treeholeId,userId);
    }

    /**
     * 查询数据库中是否有用户的点赞记录
     * @param treeholeId
     * @param userId
     * @return
     */
    @Override
    public int checkRecord(int treeholeId, int userId) {
        return userTreeholeMapper.checkRecord(treeholeId,userId);
    }


}
