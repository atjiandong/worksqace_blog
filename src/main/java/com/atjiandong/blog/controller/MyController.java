package com.atjiandong.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author atjiandong
 * @create 2022-08-28 17:10
 */
@Controller
public class MyController {

//    @RequestMapping("/index")
//    public String getIndex(){
//        return "index";
//    }
//     @Autowired
//     private TypeSerive serive;

    @GetMapping("/findUser")
    public String findUser(){
//        PageHelper.startPage(pageNum,5);
//        List<Type> list = service.listAllType();
//        PageInfo<Type> pageInfo = new PageInfo<Type>(list);
//        model.addAttribute("pageInfo",pageInfo);
        return "test";
    }


}
