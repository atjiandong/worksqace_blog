package com.atjiandong.blog.service.impl;

import com.atjiandong.blog.mapper.BlogAndTagsMapper;
import com.atjiandong.blog.mapper.BlogMapper;
import com.atjiandong.blog.pojo.Blog;
import com.atjiandong.blog.pojo.Tag;
import com.atjiandong.blog.service.BlogAndTagsService;
import com.atjiandong.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author atjiandong
 * @create 2022-09-19 12:14
 */
@Service
public class BlogAndTagsServiceImpl implements BlogAndTagsService {

    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogAndTagsMapper blogAndTagsMapper;

    @Override
    public int saveBlogIdAndTagId(Long blogId, List<Long> tagsId) {
        return blogAndTagsMapper.saveBlogIdAndTagId(blogId,tagsId);
    }

    @Override
    public List<Long> selectTagsIdByBlogId(Long blogId) {
        return blogAndTagsMapper.selectTagsIdByBlogId(blogId);
    }

    @Override
    public void deleteTagsId(Long blogId) {
        blogAndTagsMapper.deleteTagsId(blogId);
    }

    @Override
    public List<Tag> selectTagsByBlogId(Long blogId) {
        return blogAndTagsMapper.selectTagsByBlogId(blogId);
 }

    @Override
    public List<Blog> selectBlogIdByTagsId(Long TagsId) {
        List<Blog>  blogs = new ArrayList<>();
        //先根据tagsid查询blogid 再使用博客id查询信息
        List<Long> list = blogAndTagsMapper.selectBlogIdByTagsId(TagsId);
        for (Long l : list){
            Blog blog = blogService.selectAllBlogMsg(l);
            blogs.add(blog);
        }
        return blogs;
    }
}
