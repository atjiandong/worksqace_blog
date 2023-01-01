package com.atjiandong.blog.controller;

import com.atjiandong.blog.pojo.Blog;
import com.atjiandong.blog.pojo.Page;
import com.atjiandong.blog.pojo.Tag;
import com.atjiandong.blog.service.BlogService;
import com.atjiandong.blog.service.TagService;
import com.atjiandong.blog.service.impl.BlogAndTagsServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 *前端分类展示
 * @author atjiandong
 * @create 2022-10-06 20:55
 */
@Controller
public class TagsShowController {

    @Autowired
    private TagService TagService;

    @Autowired
    private BlogAndTagsServiceImpl blogAndTagsService;

    @GetMapping("/tags/{id}")
    public String tags(Page page, Model model,@PathVariable Long id,Blog blog){

        List<Tag> Tags = TagService.foundTagsPage(10000);
        if (id== -1){
            id = Tags.get(0).getId();
        }


        model.addAttribute("tags",Tags);
//        /使用自己创建的page类中定义分页之后显示第几页

        //放入类型id查询博客id  之后从博客id中查找所有信息

        //使用分页插件，
        com.github.pagehelper.Page<Blog> pages = PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<Blog> blogs = blogAndTagsService.selectBlogIdByTagsId(id);
        model.addAttribute("pages",blogs);

        model.addAttribute("pageBean",page);
        //用来进行判断是否Z是最后和第一页
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("pageMsg",pageInfo);

        model.addAttribute("activeTagId",id);

        return "tags";
    }
    
}
