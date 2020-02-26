package com.hust.software.wishbottle.mapper.user;

import com.hust.software.wishbottle.pojo.user.TreeHoleReport;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserTreeholeReportMapper {

    /**
     * 新增一条举报记录
     * @param treeholeReport
     * @return
     */
    @Insert("insert into treeholereport(treehole_id,report_reason,create_time) " +
            "values(#{treeholeId},#{reportReason},#{createTime})")
    public int addTreeholeReport(TreeHoleReport treeholeReport);
}
