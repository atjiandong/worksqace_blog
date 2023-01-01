package com.atjiandong.blog.mapper;

import com.atjiandong.blog.pojo.Blog;
import com.atjiandong.blog.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author atjiandong
 * @create 2022-09-19 12:10
 */
@Mapper
@Repository
public interface BlogAndTagsMapper {

    //进行添加blogId和tagId
    int saveBlogIdAndTagId(Long blogId, List<Long> tagsId);
    //根据id查询所有的tagsId
    List<Long> selectTagsIdByBlogId(Long blogId);

    //根据id删除tag
    void deleteTagsId(Long blogId);

    //根据博客id查询所有的tags数据
    List<Tag> selectTagsByBlogId(Long blogId);

    //根据类型id进行查找相应的blog
    List<Long> selectBlogIdByTagsId(Long TagsId);

}
