package com.hust.software.wishbottle.service.manager;


import com.github.pagehelper.PageInfo;
import com.hust.software.wishbottle.pojo.user.Tag;
import com.hust.software.wishbottle.pojo.user.Wish;
import com.hust.software.wishbottle.pojo.user.WishReport;

import java.util.HashMap;
import java.util.List;

public interface WishService {
    Integer deleteWish(int wish_id);
    Tag selectTagByID(int tag_id);
    Wish selectByID(int wish_id);
    PageInfo<Wish> selectAll(Integer pageIndex, Integer pageSize);
    PageInfo<Wish> selectAllByID(Integer pageIndex, Integer pageSize, int wish_id);
    PageInfo<Wish> selectAllByContent(Integer pageIndex, Integer pageSize, String wish_content);
    PageInfo<Wish> selectAllByStatus(Integer pageIndex, Integer pageSize, int wish_status);

    Integer deleteReportWish(int report_id);
    PageInfo<WishReport> selectReportByID(Integer pageIndex, Integer pageSize, int report_id);
    PageInfo<WishReport> selectReportByReason(Integer pageIndex, Integer pageSize, String report_reason);
    PageInfo<WishReport> selectReportAll(Integer pageIndex, Integer pageSize);
    List<HashMap> classifyTag();
}
