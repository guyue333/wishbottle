package com.hust.software.wishbottle.mapper.manager;

import com.hust.software.wishbottle.pojo.user.TreeHole;
import com.hust.software.wishbottle.pojo.user.TreeHoleReport;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface  TreeHoleMapper {
    //删除树洞信息
    @Update("update treehole set treehole_status=2 where treehole_id=#{treehole_id}")
    Integer deleteTreeHole(@Param("treehole_id") int treehole_id);

    //查询所有树洞信息
    @Select("select * from treehole")
    List<TreeHole> selectAllTreeHole();

    //根据树洞id精准查询树洞信息
    @Select("select * from treehole where treehole_id=#{treehole_id}")
    TreeHole selectOneByID(@Param("treehole_id") int treehole_id);

    //按条件进行模糊查询id 、内容、状态
    @Select("select * from treehole where treehole_id like '%${treehole_id}%'")
    List<TreeHole> selectAllByID(@Param("treehole_id") int treehole_id);

    @Select("select * from treehole where treehole_content like '%${treehole_content}%'")
    List<TreeHole> selectAllByContent(@Param("treehole_content") String treehole_content);

    @Select("select * from treehole where treehole_status = #{treehole_status}")
    List<TreeHole> selectAllByStatus(@Param("treehole_status") int treehole_status);

    //--------------------------------------------------------------------

    //删除举报的树洞信息
    @Delete("delete from treeholereport where report_id=#{report_id}")
    Integer deleteReportTreeHole(@Param("report_id") int report_id);

    //查询所有举报的树洞信息
    @Select("select * from treeholereport")
    List<TreeHoleReport> selectAllTreeHoleReport();

    //按条件进行模糊查询id 、原因
    @Select("select * from treeholereport where report_id like '%${report_id}%'")
    List<TreeHoleReport> selectReportByID(@Param("report_id") int report_id);

    @Select("select * from treeholereport where report_reason like '%${report_reason}%'")
    List<TreeHoleReport> selectReportByReason(@Param("report_reason") String report_reason);
}
