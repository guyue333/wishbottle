package com.hust.software.wishbottle.controller.user;

import com.hust.software.wishbottle.pojo.user.TreeHole;
import com.hust.software.wishbottle.pojo.user.TreeholeInfo;
import com.hust.software.wishbottle.pojo.user.User;
import com.hust.software.wishbottle.service.user.UserTreeholeService;
import com.hust.software.wishbottle.service.user.UserUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserTreeholeController {
    @Autowired
    UserTreeholeService userTreeholeService;

    @Autowired
    UserUserService userUserService;

    /**
     * 添加一条树洞信息
     * @param treehole
     * @return
     * @throws Exception
     */
    @PostMapping("/treeholes")
    public String addTreehole(@RequestBody TreeHole treehole) throws Exception{
        userTreeholeService.addTreehole(treehole);
        return "add treehole";
    }

    /**
     * 查询所有树洞信息，封装成TreeholeInfo以后返回到前端
     * @return
     * @throws Exception
     */
    @GetMapping("/treeholes")
    public List<TreeholeInfo> findAll() throws Exception{
        List<TreeholeInfo> treeholeInfoList = new ArrayList<>();

        //1、查询数据库中的所有数据
        List<TreeHole> treeHoles = userTreeholeService.findAll();

        //2、遍历树洞信息
        for (TreeHole treeHole : treeHoles) {
            //2、1 查询每一条树洞的相关用户信息
            User user = userUserService.getUserInfoById(treeHole.getWriterId());

            //2、2 将用户、树洞的信息封装到TreeholeInfo实体类中
            TreeholeInfo treeholeInfo = new TreeholeInfo();

            //2、2、1 封装用户的信息
            treeholeInfo.setUserId(user.getUserId());
            treeholeInfo.setUserName(user.getUserName());
            treeholeInfo.setUserAvatar(user.getUserAvatar());

            //2、2、2 封装树洞的信息
            treeholeInfo.setTreeholeId(treeHole.getTreeholeId());
            treeholeInfo.setCreateTime(treeHole.getCreateTime());
            treeholeInfo.setTreeholeContent(treeHole.getTreeholeContent());
            treeholeInfo.setTreeholeStatus(treeHole.getTreeholeStatus());
            treeholeInfo.setLikedNumber(treeHole.getLikedNumber());

            //2、2、3 封装树洞的评论数
            treeholeInfo.setReplyNumber(userTreeholeService.getReplyNumberById(treeHole.getTreeholeId()));

            //2、3 添加到list中
            treeholeInfoList.add(treeholeInfo);
        }

        return treeholeInfoList;
    }

    /**
     * 根据用户id查询用户发布过的所有树洞信息
     * @param userId
     * @return
     * @throws Exception
     */
    @GetMapping("/treeholes/{id}")
    public List<TreeholeInfo> findAllByUserId(@PathVariable("id") int userId) throws Exception{
        List<TreeholeInfo> treeholeInfoList = new ArrayList<>();

        List<TreeHole> treeHoles = userTreeholeService.findAllByUserId(userId);
        for (TreeHole treeHole : treeHoles) {
            //1 查询每一条树洞的相关用户信息
            User user = userUserService.getUserInfoById(treeHole.getWriterId());

            //2 将用户、树洞的信息封装到TreeholeInfo实体类中
            TreeholeInfo treeholeInfo = new TreeholeInfo();

            //2、1 封装用户的信息
            treeholeInfo.setUserId(user.getUserId());
            treeholeInfo.setUserName(user.getUserName());
            treeholeInfo.setUserAvatar(user.getUserAvatar());

            //2、2 封装树洞的信息
            treeholeInfo.setTreeholeId(treeHole.getTreeholeId());
            treeholeInfo.setCreateTime(treeHole.getCreateTime());
            treeholeInfo.setTreeholeContent(treeHole.getTreeholeContent());
            treeholeInfo.setTreeholeStatus(treeHole.getTreeholeStatus());
            treeholeInfo.setLikedNumber(treeHole.getLikedNumber());

            //2、3 添加到list中
            treeholeInfoList.add(treeholeInfo);
        }

        return treeholeInfoList;
    }

    /**
     * 根据树洞id，将对应树洞的点赞数加 1
     * @param treeholeId
     * @return
     * @throws Exception
     */
    @GetMapping("/addlikedNumer")
    public String addLikedNumber(@RequestParam("treeholeId") int treeholeId,@Param("userId") int userId) throws Exception{
        //把数据库中的点赞数+1
        userTreeholeService.addLikedNumberById(treeholeId);

        //查询数据库中是否有用户的操作记录
        int i = userTreeholeService.checkRecord(treeholeId, userId);
        if(i == 0){     //数据库中没有记录，则新增一条记录
            userTreeholeService.addLikeRecord(treeholeId,userId);
        }else{          //如果有记录，则修改状态即可
            userTreeholeService.changeStatusToLike(treeholeId,userId);
        }

        return "点赞成功";
    }

    /**
     * 根据树洞id，将对应树洞的点赞数加 1
     * @param treeholeId
     * @return
     * @throws Exception
     */
    @GetMapping("/minuslikedNumer")
    public String minuslikedNumer(@Param("treeholeId") int treeholeId,@Param("userId") int userId) throws Exception{
        userTreeholeService.minusLikedNumberById(treeholeId);

        //修改点赞状态
        userTreeholeService.changeStatusToUnlike(treeholeId,userId);
        return "取消点赞成功";
    }

    /**
     * 根据树洞id获取树洞的详情
     * @param treeholeId
     * @return
     * @throws Exception
     */
    @GetMapping("/treehole/{id}")
    public TreeholeInfo findOneByTreeholeId(@PathVariable("id") int treeholeId) throws Exception{
        //根据树洞id查询树洞信息
        TreeHole treeHole = userTreeholeService.findOneById(treeholeId);
        //查询这条树洞对应的用户信息
        User user = userUserService.getUserInfoById(treeHole.getWriterId());

        //将树洞信息和用户信息封装到TreeHoleInfo上
        TreeholeInfo treeholeInfo = new TreeholeInfo();
        //1 封装用户的信息
        treeholeInfo.setUserId(user.getUserId());
        treeholeInfo.setUserName(user.getUserName());
        treeholeInfo.setUserAvatar(user.getUserAvatar());

        //2 封装树洞的信息
        treeholeInfo.setTreeholeId(treeHole.getTreeholeId());
        treeholeInfo.setCreateTime(treeHole.getCreateTime());
        treeholeInfo.setTreeholeContent(treeHole.getTreeholeContent());
        treeholeInfo.setTreeholeStatus(treeHole.getTreeholeStatus());
        treeholeInfo.setLikedNumber(treeHole.getLikedNumber());

        return treeholeInfo;
    }

    /**
     * 根据树洞id删除树洞，本质上是修改数据库中树洞的状态
     * @param treeholeId
     * @return
     * @throws Exception
     */
    @DeleteMapping("/treehole/{id}")
    public String deletdOneByTreeholeId(@PathVariable("id") int treeholeId) throws Exception{
        userTreeholeService.deleteOneById(treeholeId);

        return "success";
    }

    /**
     * 根据树洞id和用户id查询用户的点赞状态（1-点赞，0-未点赞）
     * @param treeholeId
     * @param userId
     * @return
     * @throws Exception
     */
    @GetMapping("/getLikeStatus")
    public int getUserLikeStatus(@RequestParam("treeholeId") int treeholeId,@RequestParam("userId") int userId) throws Exception{
        int likeStatus = userTreeholeService.getLikeStatus(treeholeId, userId);
        return likeStatus;
    }
}
