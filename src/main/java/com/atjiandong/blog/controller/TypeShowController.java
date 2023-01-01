package com.atjiandong.blog.controller;

import com.atjiandong.blog.pojo.Blog;
import com.atjiandong.blog.pojo.Page;
import com.atjiandong.blog.pojo.Type;
import com.atjiandong.blog.service.BlogService;
import com.atjiandong.blog.service.TypeService;
import com.atjiandong.blog.util.StringTransformIntegerUtil;
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
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{pageId}/{id}")
    public String tpyes(Page page, Model model,@PathVariable Long id,@PathVariable("pageId") String pageId,Blog blog){
        List<Type> types = typeService.getTypesByLimt(10000);
        if (id== -1){
            id = types.get(0).getId();
        }

        model.addAttribute("types",types);
//        /使用自己创建的page类中定义分页之后显示第几页



        //使用自己创建的page类中定义分页之后显示第几页
        Integer pageNum = StringTransformIntegerUtil.caseInteger(pageId);
        page.setPageNum(pageNum);

        //使用分页插件，
        com.github.pagehelper.Page<Blog> pages = PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<Blog> blogs = blogService.selectAllBlogMsgByTypeId(id);
        model.addAttribute("pages",blogs);

        model.addAttribute("pageBean",page);
        //用来进行判断是否Z是最后和第一页
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("pageMsg",pageInfo);

        model.addAttribute("activeTypeId",id);

        return "types";
    }
    
}
