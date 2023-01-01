package com.atjiandong.blog.controller;


import com.atjiandong.blog.pojo.Blog;
import com.atjiandong.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author atjiandong
 * @create 2022-10-10 22:47
 */
@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String actives(Model model){
        //将所有数据的信息保存到域中
        model.addAttribute("archiveMap",blogService.archiveBlog());
        model.addAttribute("blogCount",blogService.selectBlogCount());
        return "archives";
    }

}
