package com.atjiandong.blog.service.impl;

import com.atjiandong.blog.mapper.UserMapper;
import com.atjiandong.blog.pojo.User;
import com.atjiandong.blog.service.UserService;
import com.atjiandong.blog.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author atjiandong
 * @create 2022-08-30 8:29
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {

        return userMapper.checkUser(username, MD5Util.md5(password));
    }
}
