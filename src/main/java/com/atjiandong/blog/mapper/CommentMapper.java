package com.atjiandong.blog.mapper;

import com.atjiandong.blog.pojo.Blog;
import com.atjiandong.blog.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author atjiandong
 * @create 2022-10-01 10:24
 */
@Mapper
@Repository
public interface CommentMapper {

    //根据blogId 查询评论
    List<Comment> listCommentByBlogId(Long blogId);

    //根据blogId和判断parentComment是否有值 查询评论
    List<Comment> listCommentByBlogIdAndParentNull(Long blogId);

    //保存评论对象
    int saveComment(@Param("comment")Comment comment);

    //根据commentParentId 查找相应的多个对象数据
    List<Comment> listCommentByParentId(Long parentId);

    //根据父级id查询子级数据
    Comment selectParentComment(Long parentCommentId);

    //使用id 进行查找相应的评论
    Comment selectCommentById(Long commentId);
}
