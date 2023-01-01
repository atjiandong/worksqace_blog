package com.atjiandong.blog.controller.admin;

import com.atjiandong.blog.pojo.User;
import com.atjiandong.blog.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author atjiandong
 * @create 2022-08-30 8:42
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserServiceImpl userService;


    @RequestMapping
    public String loginPage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String loginPage(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession session,
                            RedirectAttributes attributes){
        User user = userService.checkUser(username, password);
        System.out.println("进入方法执行！！！！");
        //判断是否存在这个用户，如果存在的话就需要将密码改为null，再将数据传入区域中
        if (user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else{
            attributes.addFlashAttribute("message","用户名和密码错误");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

//    @GetMapping("/index")
//    public String getBlogs(){
//        return "admin/index";
//    }
}
