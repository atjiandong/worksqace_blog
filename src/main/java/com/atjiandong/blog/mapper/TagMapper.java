package com.atjiandong.blog.mapper;

import com.atjiandong.blog.pojo.Blog;
import com.atjiandong.blog.pojo.Tag;
import com.atjiandong.blog.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author atjiandong
 * @create 2022-08-31 8:38
 */
@Mapper
@Repository
public interface TagMapper {

    //查询最大值
    Long selectMax();

    //查询是否已经存在分类
    Tag getTagName(String name);

//    保存
    int save(Tag tag);
//    查询多个
    List<Tag> listTag(Integer num);

    List<Tag> listAllTag();

    //分页操作
    List<Tag> selectTags(Long index, Long num);

    //    根据id 查询单个
    Tag getTagById(@Param("id") Long id);

//    修改
    void updateTag(Tag tag);
//    删除
    void deleteTagById(Long id);

    //使用一组Long类型的值进行查询
    List<Tag> listTagAndString(List<Long> ids);

//    //进行分组查询之后进行排序(手动进行分页)
//    List<Tag> getTagsGroup(Integer num);

    //根据id即可查询到相应的blog条目
    List<Blog> foundTagByBlogs(Long id);

    //对于上面的普通的分页查询
    List<Tag> foundTagsPage(Integer num);


}
