package com.atjiandong.blog.controller;



import com.atjiandong.blog.pojo.Blog;
import com.atjiandong.blog.pojo.Page;
import com.atjiandong.blog.pojo.Type;
import com.atjiandong.blog.service.BlogService;
import com.atjiandong.blog.service.CommentService;
import com.atjiandong.blog.service.TagService;
import com.atjiandong.blog.service.TypeService;
import com.atjiandong.blog.util.StringTransformIntegerUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author atjiandong
 * @create 2022-08-28 8:26
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;


    /**
     *   获取博客的用户信息，博客详情，
     * @param model
     * @param page
     * @return
     */
    @GetMapping("/{id}")
    public String index(@PathVariable("id") String id,Model model,com.atjiandong.blog.pojo.Page page){

        System.out.println(id);
        model.addAttribute("numBlog",blogService.selectBlogIdMax());
        model.addAttribute("types",typeService.getTypesByLimt(6));
        model.addAttribute("tags",tagService.foundTagsPage(10));
        model.addAttribute("recommendBlogs",blogService.selectRecommend(8));

        //使用自己创建的page类中定义分页之后显示第几页
        Integer pageNum = StringTransformIntegerUtil.caseInteger(id);
        page.setPageNum(pageNum);

        com.github.pagehelper.Page<Type> pages = PageHelper.startPage(page.getPageNum(),page.getPageSize());
        //内部使用了分页插件进行分页查询
        List<Blog> blog = blogService.selectAllBlog(page);
        System.out.println(blog);
        model.addAttribute("blogs",blog);
        model.addAttribute("pageBean",page);
        //用来进行判断是否Z是最后和第一页
        PageInfo pageInfo = new PageInfo(blog);
        model.addAttribute("pageMsg",pageInfo);


//        int i = 9 /0;
//        String blog = null;
//        if(blog == null){
//            throw new NotFoundException("博客不存在....");
//        }
       return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam Integer id, Model model, @RequestParam String query, Page page){
        page.setPageNum(id);
        model.addAttribute("blogs",blogService.queryBlogByTitleAndContent(query,page));
        model.addAttribute("query",query);
        return "search";
    }


    @GetMapping("/blog/{id}")
    public String blog(@PathVariable("id") Long id,Model model){
        Blog blog = blogService.selectAllBlogMsg(id);
        blogService.updateBlogView(blog.getId());
        model.addAttribute("blog",blog);
        model.addAttribute("comments",commentService.listCommentByBlogId(id));

        return "blog";

    }


    //为了给底部的数据进行放入值
    @GetMapping("/footer/newblog")
    public String newBlogs(Model model){
        List<Blog> blogs = blogService.selectRecommend(3);
        model.addAttribute("newblogs",blogs);
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println(blogs);
        return "_fragments :: newblogList";

    }


//    @GetMapping("/blog/{id}")
//    public String blog(){
//        return "blog";
//    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }

}
