package com.hust.software.wishbottle.mapper;

import com.hust.software.wishbottle.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagMapper {

    /**
     * 根据标签id查询标签
     * @param id
     * @return
     */
    @Select("select * from tags where tag_id = #{id}")
    public Tag selectTagById(int id);

    /**
     * 查询所有标签内容
     * @return
     */
    @Select("select * from tags")
    public List<Tag> findAll();
}
