package com.hust.software.wishbottle.controller.user;

import com.hust.software.wishbottle.pojo.user.TreeHole;
import com.hust.software.wishbottle.pojo.user.TreeholeInfo;
import com.hust.software.wishbottle.pojo.user.User;
import com.hust.software.wishbottle.service.user.UserTreeholeService;
import com.hust.software.wishbottle.service.user.UserUserService;
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
    @GetMapping("/addlikedNumer/{id}")
    public String addLikedNumber(@PathVariable("id") int treeholeId) throws Exception{
        userTreeholeService.addLikedNumberById(treeholeId);
        return "点赞成功";
    }

    /**
     * 根据树洞id，将对应树洞的点赞数加 1
     * @param treeholeId
     * @return
     * @throws Exception
     */
    @GetMapping("/minuslikedNumer/{id}")
    public String minuslikedNumer(@PathVariable("id") int treeholeId) throws Exception{
        userTreeholeService.minusLikedNumberById(treeholeId);
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
}
