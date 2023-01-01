package com.atjiandong.blog.mapper;

import com.atjiandong.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author atjiandong
 * @create 2022-08-30 9:03
 */
@Mapper
@Repository
public interface UserMapper{

   //登录检查
    User checkUser( String username, String password);

}
