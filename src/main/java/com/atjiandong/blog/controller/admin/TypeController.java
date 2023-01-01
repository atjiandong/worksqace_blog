package com.atjiandong.blog.controller.admin;

import com.atjiandong.blog.pojo.Type;
import com.atjiandong.blog.service.TypeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import javax.validation.Valid;
import java.sql.SQLOutput;
import java.util.Iterator;
import java.util.List;

/**
 * @author atjiandong
 * @create 2022-08-31 10:31
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types/{id}")
    public String types(@PathVariable("id") Integer id, Model model,com.atjiandong.blog.pojo.Page page) {

        //使用自己创建的page类中定义分页之后显示第几页
        page.setPageNum(id);
        //使用分页插件，
        Page<Type> types = PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<Type> list = typeService.listAllType();
//        List<Type> list = typeService.selectTypes(types.getStartRow(), types.getEndRow());
        model.addAttribute("page",list);
        model.addAttribute("pageBean",page);

        //用来进行判断是否是最后和第一页
        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("pageMsg",pageInfo);

//        System.out.println(pageInfo.isIsLastPage());
//        System.out.println(pageInfo.isIsFirstPage());
//        System.out.println(pageInfo.getPages());
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String typeInput(Model model){
        model.addAttribute("type",new Type());
        return "admin/type-input";
    }

    @GetMapping("/types/input/{id}")
    public String editInput(@PathVariable("id") Long id,Model model ){
        model.addAttribute("type",typeService.getTypeById(id));
        return "admin/type-input";
    }


    @PostMapping("/types")
    public String post( @Valid Type type, BindingResult result, RedirectAttributes attributes){

        Type typeName = typeService.getTypeName(type.getName());
        if (typeName != null){
            result.rejectValue("name","nameError","不能重复添加分类...");
        }
        if (result.hasErrors()){
            return "admin/type-input";
        }
        System.out.println(type);

        //先判断传过来的数据是否为 long类型的0
        Long max = typeService.selectMax();

        if (max != null){
            //进入id+1的添加流程
            if (Strings.isEmpty(type.getName())){
                attributes.addFlashAttribute("message","添加失败");
            }else{
                //将id+1的添加操作，
                type.setId(max +1);
                int save = typeService.save(type);
                attributes.addFlashAttribute("message","添加成功");
            }
        }else{
            //进入没有数据时的id归为1的添加
            if (Strings.isEmpty(type.getName())){
                attributes.addFlashAttribute("message","添加失败");
            }else{
                //将id设置为1进行添加操作
                type.setId(new Long(1));
                typeService.save(type);
                System.out.println(type);
                attributes.addFlashAttribute("message","添加成功");
            }
        }
        return "redirect:/admin/types/1";
    }


    @PostMapping("/types/update/{id}")
    public String editPost( @Valid Type type, BindingResult result,@PathVariable Long id, RedirectAttributes attributes){
        Type typeName = typeService.getTypeName(type.getName());
        if (typeName != null){
            result.rejectValue("name","nameError","不能重复添加分类...");
        }
        if (result.hasErrors()){
            return "admin/type-input";
        }
        System.out.println(type);

        if (Strings.isEmpty(type.getName())){
            attributes.addFlashAttribute("message","更新失败");
        }else{
//            int save = typeService.save(type);
            //使用修改方法进行保存
            type.setId(id);
            typeService.updateType(type);
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types/1";
    }


    @GetMapping("/types/delete/{id}")
    public String delete(@PathVariable Long id ,RedirectAttributes attributes){
        try {
            typeService.deleteById(id);
        } catch (Exception e) {
            attributes.addFlashAttribute("message","删除失败，删除的标签中存在博客");
            return "redirect:/admin/types/1";
        }
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types/1";
    }
}
