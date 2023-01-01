package com.atjiandong.blog.controller.admin;

import com.atjiandong.blog.mapper.BlogMapper;
import com.atjiandong.blog.mapper.TagMapper;
import com.atjiandong.blog.pojo.Blog;
import com.atjiandong.blog.pojo.Tag;
import com.atjiandong.blog.pojo.Type;
import com.atjiandong.blog.pojo.User;
import com.atjiandong.blog.service.BlogAndTagsService;
import com.atjiandong.blog.service.BlogService;
import com.atjiandong.blog.service.TagService;
import com.atjiandong.blog.service.TypeService;
import com.atjiandong.blog.util.StringReLongUtil;
import com.atjiandong.blog.util.StringTransformIntegerUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author atjiandong
 * @create 2022-08-30 17:06
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    private  static final  String INPUT="admin/blogs-input";
    private  static final  String LIST="admin/blogs";
    private  static final  String REDIRECT="redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogAndTagsService blogAndTagsService;


    @GetMapping("/blogs/{id}")
    public String list(@PathVariable("id") Integer id, Model model, com.atjiandong.blog.pojo.Page page, Blog blog){
        //使用自己创建的page类中定义分页之后显示第几页
        page.setPageNum(id);


        //查询所有分类
        List<Type> types = typeService.listAllType();
        model.addAttribute("type",types);
        System.out.println(types);
        //使用分页插件，

        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<Blog> blogs = blogService.selectBlogAndType(blog);
        model.addAttribute("page",blogs);
        System.out.println(blogs);


        //用来进行判断是否是最后和第一页
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("pageMsg",pageInfo);

        return LIST;
    }
    @InitBinder
    public void init(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    @PostMapping("/blogs/search")
    public String search(@RequestParam(value = "id") Integer Id,@RequestParam("typeId")String typeId, Model model, com.atjiandong.blog.pojo.Page page, @Valid Blog blog){

        //页面传过来的数据时为字符串类型的
       if (typeId != null && typeId != ""){
           System.out.println("进入设置typeId");
           blog.setType(new Type(Long.parseLong(typeId)));
        }

        System.out.println(typeId);

        //使用自己创建的page类中定义分页之后显示第几页
        page.setPageNum(Id);
        //使用分页插件，
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<Blog> blogs = blogService.selectBlogAndType(blog);
        model.addAttribute("page",blogs);
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("blog",new Blog());
        setTypeAndTag(model);
        System.out.println("进行新增跳转");
        return "admin/blogs-input";

    }

    public void setTypeAndTag(Model model){
        model.addAttribute("type",typeService.listAllType());
        model.addAttribute("tags",tagService.listAllTag());
    }

    @GetMapping("/blogs/input/{blogId}")
    public String editInput(@PathVariable("blogId") Long id,Model model ){
        Blog blog = blogService.selectBlogById(id);

        setTypeAndTag(model);


        //根据id查询可能还没有标签

        List<Long> BlogTags = blogAndTagsService.selectTagsIdByBlogId(blog.getId());
        List<Tag> tags = tagService.listTagAndString(BlogTags);
        blog.setTags(tags);
        System.out.println(BlogTags);


        blog.init();
        System.out.println(blog);
        model.addAttribute("blog",blog);
        System.out.println("进行修改跳转");
        return "admin/blogs-input";

    }



    @PostMapping(value = "/blogs/update")
    public String update(Blog blog, RedirectAttributes attributes, HttpSession session){


        System.out.println(blog.getTagIds());
        //设置blog中相应的对象属性
        blog.setUser((User)session.getAttribute("user"));
        blog.setType(typeService.getTypeById(blog.getType().getId()));



        System.out.println(blog.isPublished());
        System.out.println(blog);

            if (Strings.isEmpty(blog.getTitle())){
                attributes.addFlashAttribute("message","更新失败");
            }else{
                System.out.println(blog);
                blogService.updateBlog(blog.getId(),blog);
                attributes.addFlashAttribute("message","更新成功");
            }

        return "redirect:/admin/blogs/1";
    }

    @PostMapping(value = "/blogs/save")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){

        System.out.println(blog.getViews());
        System.out.println(blog.getTagIds());
        //设置blog中相应的对象属性
        blog.setUser((User)session.getAttribute("user"));
        blog.setType(typeService.getTypeById(blog.getType().getId()));

        System.out.println(blog.isPublished());
        System.out.println(blog);
        //先判断传过来的数据是否为 long类型的0
        Long max = blogService.selectBlogIdMax();

        if (max != null){
            //进入id+1的添加流程
            if (Strings.isEmpty(blog.getTitle())){
                attributes.addFlashAttribute("message","添加失败");
            }else{
                //将id+1的添加操作，
                blog.setId(max +1);
                int save = blogService.saveBlog(blog);
                //将接受的字符串标签id转化为long 类型 进行保存至主键表中
                List<Long> list = StringReLongUtil.StringReLong(blog.getTagIds());
                blogAndTagsService.saveBlogIdAndTagId(blog.getId(),list);
                attributes.addFlashAttribute("message","添加成功");
            }
        }else{
            //进入没有数据时的id归为1的添加
            if (Strings.isEmpty(blog.getTitle())){
                attributes.addFlashAttribute("message","添加失败");
            }else{
                //将id设置为1进行添加操作
                blog.setId(new Long(1));
                blogService.saveBlog(blog);
                //将接受的字符串标签id转化为long 类型 进行保存至主键表中
                List<Long> list = StringReLongUtil.StringReLong(blog.getTagIds());
                blogAndTagsService.saveBlogIdAndTagId(blog.getId(),list);
                System.out.println(blog);
                attributes.addFlashAttribute("message","添加成功");
            }
        }

        return "redirect:/admin/blogs/1";
    }

    @GetMapping("/blogs/delete/{blogId}")
    public String delete(@PathVariable("blogId") Long id,RedirectAttributes attributes){

            blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/blogs/1";
    }




}
