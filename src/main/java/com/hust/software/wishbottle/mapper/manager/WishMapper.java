package com.hust.software.wishbottle.mapper.manager;


import com.hust.software.wishbottle.pojo.user.Tag;
import com.hust.software.wishbottle.pojo.user.Wish;
import com.hust.software.wishbottle.pojo.user.WishReport;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface WishMapper {
    //删除心愿信息,以及所有相关信息
    @Update("update wish set wish_status=4 where wish_id=#{wish_id}")
    Integer deleteWish(@Param("wish_id") int wish_id);

    //-------------------------根据标签id查询标签内容
    @Select("select * from tags where tag_id=#{tag_id}")
    Tag selectTagByID(@Param("tag_id") int tag_id);

    //根据心愿id查询心愿信息
    @Select("select * from wish where wish_id=#{wish_id}")
    Wish selectByID(@Param("wish_id") int wish_id);

    //查询所有心愿信息,根据状态，正常、管理员删除
    @Select("select * from wish")
    List<Wish> selectAll();

    //条件查询 id 内容 状态
    @Select("select * from wish where wish_id like '%${wish_id}%'")
    List<Wish> selectAllByID(@Param("wish_id") int wish_id);

    @Select("select * from wish where wish_content like '%${wish_content}%'")
    List<Wish> selectAllByContent(@Param("wish_content") String wish_content);

    @Select("select * from wish where wish_status=#{wish_status}")
    List<Wish> selectAllByStatus(@Param("wish_status") int wish_status);

    //-----------------------------------------------------------------

    //删除举报的心愿信息，以及所有相关信息
    @Delete("delete from wishreport where report_id=#{report_id}")
    Integer deleteReportWish(@Param("report_id") int report_id);

    //根据举报id、举报原因查询举报信息
    @Select("select * from wishreport where report_id like '%${report_id}%'")
    List<WishReport> selectReportByID(@Param("report_id") int report_id);

    @Select("select * from wishreport where report_reason like '%${report_reason}%'")
    List<WishReport> selectReportByReason(@Param("report_reason") String report_reason);

    //查询所有举报信息
    @Select("select * from wishreport")
    List<WishReport> selectReportAll();

    //根据标签生成图表
    @Select("SELECT tags.`tag_meaning`,COUNT(*) FROM wish,tags WHERE wish.`tag_id` = tags.`tag_id` GROUP BY tags.`tag_meaning`")
    List<HashMap> classifyTag();
}
