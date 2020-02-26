package com.hust.software.wishbottle.service.manager;

import com.hust.software.wishbottle.pojo.user.Tag;
import com.hust.software.wishbottle.pojo.user.Wish;
import com.hust.software.wishbottle.pojo.user.WishReport;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface WishService {
    Integer deleteWish(int wish_id);
    Tag selectTagByID(int tag_id);
    Wish selectByID(int wish_id);
    List<Wish> selectAll();
    List<Wish> selectAllByID(int wish_id);
    List<Wish> selectAllByContent(String wish_content);
    List<Wish> selectAllByStatus(int wish_status);

    Integer deleteReportWish(int report_id);
    List<WishReport> selectReportByID(int report_id);
    List<WishReport> selectReportByReason(String report_reason);
    List<WishReport> selectReportAll();
    List<HashMap> classifyTag();
}
