package com.hust.software.wishbottle.service.impl;

import com.hust.software.wishbottle.mapper.TreeholeMapper;
import com.hust.software.wishbottle.pojo.TreeHole;
import com.hust.software.wishbottle.service.TreeholeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeholeServiceImpl implements TreeholeService {
    @Autowired
    TreeholeMapper treeholeMapper;

    /**
     * 添加一条树洞信息
     * @param treeHole
     * @return
     */
    @Override
    public int addTreehole(TreeHole treeHole) {
        return treeholeMapper.addTreehole(treeHole);
    }

    /**
     * 查询数据库中的所有树洞信息，按照时间降序排列
     * @return
     */
    @Override
    public List<TreeHole> findAll() {
        return treeholeMapper.findAll();
    }

    /**
     * 根据用户id查询用户发布过的所有树洞信息
     * @param userId
     * @return
     */
    @Override
    public List<TreeHole> findAllByUserId(int userId) {
        return treeholeMapper.findAllByUserId(userId);
    }

    /**
     * 根据树洞id，将对应树洞的点赞数加 1
     * @param treeholeId
     * @return
     */
    @Override
    public int addLikedNumberById(int treeholeId) {
        return treeholeMapper.addLikedNumberById(treeholeId);
    }

    /**
     * 根据树洞id，将对应树洞的点赞数减 1
     * @param treeholeId
     * @return
     */
    @Override
    public int minusLikedNumberById(int treeholeId) {
        return treeholeMapper.minusLikedNumberById(treeholeId);
    }

    /**
     * 跟据树洞id查询树洞的信息
     * @param treeholeId
     * @return
     */
    @Override
    public TreeHole findOneById(int treeholeId) {
        return treeholeMapper.findOneById(treeholeId);
    }

    /**
     * 根据树洞id删除树洞，本质上是修改数据库中树洞的状态
     * @param treeholeId
     * @return
     */
    @Override
    public int deleteOntById(int treeholeId) {
        return treeholeMapper.deleteOntById(treeholeId);
    }
}
