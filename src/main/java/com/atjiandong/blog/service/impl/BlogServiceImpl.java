package com.atjiandong.blog.service.impl;

import com.atjiandong.blog.exception.NotFoundException;
import com.atjiandong.blog.mapper.BlogAndTagsMapper;
import com.atjiandong.blog.mapper.BlogMapper;
import com.atjiandong.blog.mapper.TagMapper;
import com.atjiandong.blog.mapper.TypeMapper;
import com.atjiandong.blog.pojo.*;
import com.atjiandong.blog.service.BlogAndTagsService;
import com.atjiandong.blog.service.BlogService;
import com.atjiandong.blog.util.MarkdownUtils;
import com.atjiandong.blog.util.MyBeanUtils;
import com.atjiandong.blog.util.StringReLongUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author atjiandong
 * @create 2022-09-11 9:03
 */
@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private BlogAndTagsMapper blogAndTagsMapper;


    @Override
    public List<Blog> selectAllBlog(Page page) {
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        //这个返回的是所有博客信息，除了tag
        List<Blog> blog = blogMapper.selectAllBlog();

        return blog;
    }

    @Override
    public List<Blog> selectRecommend(Integer num) {
        return blogMapper.selectRecommend(num);
    }

    @Override
    public List<Blog> selectBlogAndType(Blog blog) {
        return blogMapper.selectBlogAndType(blog);
    }

    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        if (blog.getId() == null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        }else {
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
        }
        return blogMapper.saveBlog(blog);
    }


    @Override
    public Long selectBlogIdMax() {
        return blogMapper.selectBlogIdMax();
    }


    @Override
    public Blog selectBlogById(Long id) {
        return blogMapper.selectBlogById(id);
    }

    @Transactional
    @Override
    public void updateBlog(Long id, Blog blog) {
        Blog blog1 = blogMapper.selectBlogById(id);
        if (blog1 == null){
            throw  new NotFoundException("该博客不存在...");
        }

        blog.setUpdateTime(new Date());

        if (blog.getTagIds() != null && !(blog.getTagIds().isEmpty())){
            blogAndTagsMapper.deleteTagsId(id);
        }

//        将接受的字符串标签id转化为long 类型 进行保存至主键表中
        List<Long> list = StringReLongUtil.StringReLong(blog.getTagIds());
        blogAndTagsMapper.saveBlogIdAndTagId(blog.getId(),list);



        blogMapper.updateBlog(id,blog);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogAndTagsMapper.deleteTagsId(id);
        //先进行删除跟blogid有关的所有标签
        blogMapper.deleteBlog(id);
    }


    @Override
    public List<Blog> queryBlogByTitleAndContent(String queryName,Page page) {
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<Blog> blogs = blogMapper.queryBlogByTitleAndContent(queryName);
        for (int i = 0; i < blogs.size(); i++) {
            Type type = typeMapper.foundBlogType(blogs.get(i).getId());
            blogs.get(i).setType(type);
        }
        return blogs;
    }


    @Transactional
    @Override
    public Blog selectAllBlogMsg(Long id) {
        //根据id查询出blog
        Blog blog = blogMapper.selectAllBlogMsg(id);

        if (blog == null){
            throw new  NotFoundException("博客不存在");
        }

        //设置tag
        List<Tag> tags = blogAndTagsMapper.selectTagsByBlogId(blog.getId());
        blog.setTags(tags);
        //创建一个新的值，不对数据库中的数据进行操作
        Blog b = new Blog();
        BeanUtils.copyProperties(blog,b);
        String content = b.getContent();
        String s = MarkdownUtils.markdownToHtmlExtensions(content);
        b.setContent(s);


        return b;
    }

    @Override
    public List<Blog> selectAllBlogMsgByTypeId(Long typeId) {
        List<Blog> blogs = blogMapper.selectAllBlogMsgByTypeId(typeId);

        for (Blog blog : blogs) {
            if (blog == null) {
                throw new NotFoundException("博客不存在");
            }

            //设置tag
            List<Tag> tags = blogAndTagsMapper.selectTagsByBlogId(blog.getId());
            blog.setTags(tags);
        }
        return blogs;
    }

    @Override
    public Blog getAndConvert(Long id) {
        Blog blog = blogMapper.selectBlogById(id);
        if (blog == null){
            throw new  NotFoundException("博客不存在");
        }
        //创建一个新的值，不对数据库中的数据进行操作
        Blog b = new Blog();
        BeanUtils.copyProperties(blog,b);
        String content = b.getContent();
        MarkdownUtils.markdownToHtmlExtensions(content);

        b.setContent(content);
        return b;
    }

    @Override
    public int updateBlogView(Long id) {
        return blogMapper.updateBlogView(id);
    }

    /**
     * 这样进行保存是为了之后进行展示时直接进行展示年份中的所有数据
     * @return
     */
    @Override
    public Map<String, List<Archives>> archiveBlog() {
        //获取所有年份
        List<String> strings = blogMapper.selectAllYear();
        Map<String,List<Archives>>  map = new HashMap<>();
        //遍历年份。添加年份和根据年份查询到的数据
        for (String year : strings){
            map.put(year,blogMapper.selectArchive(year));
        }

        return map;
    }

    @Override
    public int selectBlogCount() {
        return blogMapper.selectBlogCount();
    }
}
