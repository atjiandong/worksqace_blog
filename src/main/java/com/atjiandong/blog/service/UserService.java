package com.atjiandong.blog.service;

import com.atjiandong.blog.pojo.User;

/**
 * @author atjiandong
 * @create 2022-08-30 8:26
 */
public interface UserService {

    //登录检查
    User checkUser(String username,String password);

}
