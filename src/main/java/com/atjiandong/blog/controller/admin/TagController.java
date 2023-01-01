package com.atjiandong.blog.controller.admin;

import com.atjiandong.blog.pojo.Tag;
import com.atjiandong.blog.service.TagService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author atjiandong
 * @create 2022-08-31 10:31
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags/{id}")
    public String tags(@PathVariable("id") Integer id, Model model,com.atjiandong.blog.pojo.Page page) {

        //使用自己创建的page类中定义分页之后显示第几页
        page.setPageNum(id);
        //使用分页插件，
        Page<Tag> tags = PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<Tag> list = tagService.listAllTag();
//        List<tag> list = tagService.selecttags(tags.getStartRow(), tags.getEndRow());
        model.addAttribute("page",list);
        model.addAttribute("pageBean",page);

        //用来进行判断是否是最后和第一页
        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("pageMsg",pageInfo);

//        System.out.println(pageInfo.isIsLastPage());
//        System.out.println(pageInfo.isIsFirstPage());
//        System.out.println(pageInfo.getPages());
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String tagInput(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tag-input";
    }

    @GetMapping("/tags/input/{id}")
    public String editInput(@PathVariable("id") Long id,Model model ){
        model.addAttribute("tag",tagService.getTagById(id));
        return "admin/tag-input";
    }


    @PostMapping("/tags")
    public String post( @Valid Tag tag, BindingResult result, RedirectAttributes attributes){

        Tag tagName = tagService.getTagName(tag.getName());
        if (tagName != null){
            result.rejectValue("name","nameError","不能重复添加分类...");
        }
        if (result.hasErrors()){
            return "admin/tag-input";
        }
        System.out.println(tag);
        //先判断传过来的数据是否为 long类型的0
        Long max = tagService.selectMax();

        if (max != null){
            //进入id+1的添加流程
            if (Strings.isEmpty(tag.getName())){
                attributes.addFlashAttribute("message","添加失败");
            }else{
                //将id+1的添加操作，
                tag.setId(max +1);
                int save = tagService.save(tag);
                attributes.addFlashAttribute("message","添加成功");
            }
        }else{
            //进入没有数据时的id归为1的添加
            if (Strings.isEmpty(tag.getName())){
                attributes.addFlashAttribute("message","添加失败");
            }else{
                //将id设置为1进行添加操作
                tag.setId(new Long(1));
                tagService.save(tag);
                System.out.println(tag);
                attributes.addFlashAttribute("message","添加成功");
            }
        }
        return "redirect:/admin/tags/1";
    }


    @PostMapping("/tags/update/{id}")
    public String editPost( @Valid Tag tag, BindingResult result,@PathVariable Long id, RedirectAttributes attributes){
        Tag tagName = tagService.getTagName(tag.getName());
        if (tagName != null){
            result.rejectValue("name","nameError","不能重复添加分类...");
        }
        if (result.hasErrors()){
            return "admin/tag-input";
        }
        System.out.println(tag);

        if (Strings.isEmpty(tag.getName())){
            attributes.addFlashAttribute("message","更新失败");
        }else{
//            int save = tagService.save(tag);
            //使用修改方法进行保存
            tag.setId(id);
            tagService.updateTag(tag);
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tags/1";
    }


    @GetMapping("/tags/delete/{id}")
    public String delete(@PathVariable Long id ,RedirectAttributes attributes){

        try {
            tagService.deleteById(id);
        } catch (Exception e) {
            attributes.addFlashAttribute("message","删除失败，删除的分类中存在博客");
            return "redirect:/admin/tags/1";
        }
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags/1";
    }


}
