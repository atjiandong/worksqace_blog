package com.atjiandong.blog.controller;

import com.atjiandong.blog.pojo.Comment;
import com.atjiandong.blog.pojo.User;
import com.atjiandong.blog.service.BlogService;
import com.atjiandong.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author atjiandong
 * @create 2022-10-01 10:19
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        List<Comment> comments = commentService.listCommentByBlogIdAndParentNull(blogId);
        //拿到评论的所有信息（创建的list方法）
        model.addAttribute("comments",comments);

        return "blog :: commentList";
    }


    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        //获取评论中的blogid
        Long blogId = comment.getBlog().getId();
        //根据blogid设置评论中对应的blog对象
        comment.setBlog(blogService.selectAllBlogMsg(blogId));

        User user = (User) session.getAttribute("user");
        if (user != null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
//            comment.setNickname(user.getNickname());
        }else{
            //设置评论时的头像
            comment.setAvatar(avatar);
        }

        //之后将数据进行保存
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }


}
