package com.hust.software.wishbottle.mapper;

import com.hust.software.wishbottle.pojo.TreeholeReport;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TreeholeReportMapper {

    /**
     * 新增一条举报记录
     * @param treeholeReport
     * @return
     */
    @Insert("insert into treeholereport(treehole_id,report_reason,create_time) " +
            "values(#{treeholeId},#{reportReason},#{createTime})")
    public int addTreeholeReport(TreeholeReport treeholeReport);
}
