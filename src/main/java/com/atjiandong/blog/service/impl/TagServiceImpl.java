package com.atjiandong.blog.service.impl;

import com.atjiandong.blog.mapper.TagMapper;
import com.atjiandong.blog.mapper.TypeMapper;
import com.atjiandong.blog.pojo.Blog;
import com.atjiandong.blog.pojo.Tag;
import com.atjiandong.blog.pojo.Type;
import com.atjiandong.blog.service.TagService;
import com.atjiandong.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author atjiandong
 * @create 2022-08-31 9:43
 */
@Service
public class TagServiceImpl implements TagService {


    @Autowired
    private TagMapper tagMapper;


    @Override
    public Long selectMax() {
        return tagMapper.selectMax();
    }

    @Override
    public Tag getTagName(String name) {
        return tagMapper.getTagName(name);
    }

    @Override
    public int save(Tag tag) {

        return tagMapper.save(tag);
    }



    @Override
    public Tag getTagById(Long id) {
        return tagMapper.getTagById(id);
    }

//    @Override
//    public List<Tag> listTag(Integer num) {
//       return tagMapper.listTag(num);
//    }

    @Override
    public List<Tag> listAllTag() {
        return tagMapper.listAllTag();
    }

    @Override
    public List<Tag> selectTags(Long index, Long num) {
        return tagMapper.selectTags(index,num);
    }

    @Override
    public void updateTag(Tag tag) {
        tagMapper.updateTag(tag);
    }

    @Override
    public void deleteById(Long id) {
        tagMapper.deleteTagById(id);

    }

    @Override
    public List<Tag> listTagAndString(List<Long> ids) {
        return tagMapper.listTagAndString(ids);
    }

    @Override
    public List<Blog> foundTagByBlogs(Long id) {

        return tagMapper.foundTagByBlogs(id);


    }

    @Override
    public List<Tag> foundTagsPage(Integer num) {
        List<Tag> tags = tagMapper.foundTagsPage(num);
        for (Tag tag :tags) {
            List<Blog> blogs = tagMapper.foundTagByBlogs(tag.getId());
            tag.setBlogs(blogs);
        }
        return tags;
    }

//    @Override
//    public List<Tag> getTagsGroup(Integer num) {
//        return tagMapper.getTagsGroup(num);
//    }

}
