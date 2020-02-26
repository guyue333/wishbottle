package com.hust.software.wishbottle.mapper.user;

import com.hust.software.wishbottle.pojo.user.TreeHole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserTreeholeMapper {

    /**
     * 新增一条树洞信息
     * @param treeHole
     * @return
     */
    @Insert("insert into treehole(writer_id,create_time,treehole_content,treehole_status,liked_number) " +
            "values (#{writerId},#{createTime},#{treeholeContent},#{treeholeStatus},#{likedNumber})")
    public int addTreehole(TreeHole treeHole);

    /**
     * 查询数据库中的所有 状态为0的 树洞信息，按照时间降序排列
     * @return
     */
    @Select("select * from treehole where treehole_status = 0 order by create_time desc")
    public List<TreeHole> findAll();

    /**
     * 根据用户id查询用户发布过的所有 状态为0的 树洞
     * @param userId
     * @return
     */
    @Select("select * from treehole where writer_id = #{userId} and treehole_status = 0 order by create_time desc")
    public List<TreeHole> findAllByUserId(int userId);

    /**
     * 根据树洞id，将对应树洞的点赞数加 1
     * @param treeholeId
     * @return
     */
    @Update("update treehole set liked_number = liked_number + 1 where treehole_id = #{treeholeId}")
    public int addLikedNumberById(int treeholeId);

    /**
     * 根据树洞id，将对应树洞的点赞数减 1
     * @param treeholeId
     * @return
     */
    @Update("update treehole set liked_number = liked_number - 1 where treehole_id = #{treeholeId}")
    public int minusLikedNumberById(int treeholeId);

    /**
     * 跟据树洞id查询树洞的信息
     * @param treeholeId
     * @return
     */
    @Select("select * from treehole where treehole_id = #{treeholeId}")
    public TreeHole findOneById(int treeholeId);

    /**
     * 根据树洞id删除树洞，本质上是修改数据库中树洞的状态
     * @param treeholeId
     * @return
     */
    @Update("update treehole set treehole_status = 1 where treehole_id = #{treeholeId}")
    public int deleteOneById(int treeholeId);
}
