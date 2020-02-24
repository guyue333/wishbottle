package com.hust.software.wishbottle.mapper;

import com.hust.software.wishbottle.pojo.WishReport;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface WishReportMapper {

    /**
     * 新增一条心愿举报记录
     * @param wishReport
     * @return
     */
    @Insert("insert into wishreport(wish_id,report_reason,create_time) " +
            "values(#{wishId},#{reportReason},#{createTime})")
    public int addWishReport(WishReport wishReport);
}
