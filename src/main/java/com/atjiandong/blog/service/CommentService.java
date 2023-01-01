package com.atjiandong.blog.service;

import com.atjiandong.blog.pojo.Blog;
import com.atjiandong.blog.pojo.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author atjiandong
 * @create 2022-10-01 10:28
 */
public interface CommentService {
    //根据blogId 查询评论
    List<Comment> listCommentByBlogId(Long blogId);

    //根据blogId和判断parentComment是否有值 查询评论
    List<Comment> listCommentByBlogIdAndParentNull(Long blogId);

    //保存评论对象
    int saveComment(Comment comment);


     //根据commentParentId 查找相应的多个对象数据
    List<Comment> listCommentByParentId(Long parentId);

    //根据commentParentId 查找相应的对象数据
    Comment selectCommentByParentId(Long parentCommentId);


    //使用id 进行查找相应的评论
    Comment selectCommentById(Long commentId);
 }
