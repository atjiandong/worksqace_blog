package com.atjiandong.blog.service.impl;

import com.atjiandong.blog.mapper.CommentMapper;
import com.atjiandong.blog.pojo.Comment;
import com.atjiandong.blog.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.OperationsException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author atjiandong
 * @create 2022-10-01 10:28
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
       return  commentMapper.listCommentByBlogId(blogId);
    }

    @Override
    public List<Comment> listCommentByBlogIdAndParentNull(Long blogId) {

        List<Comment> allReplyAndParent = getAllReplyAndParent(blogId);

        return allReplyAndParent;
    }

    public List<Comment>  getAllReplyAndParent(Long blogId) {
        //存放迭代找出所有子代的集合
        List<Comment> tempReplys = new ArrayList<>();
        List<Comment> allReplyComment = new ArrayList<>();
        //不为null的父级对象
        List<Comment> parentComments = commentMapper.listCommentByBlogIdAndParentNull(blogId);
        for (Comment comment : parentComments) {
            //根据父级id查找用户
            List<Comment> reply = commentMapper.listCommentByParentId(comment.getId());
            System.out.println(reply);
            //判断二级是否存在父级
            if (!(reply.isEmpty())){
                //放入根据父级id查询到的子级
                tempReplys = new ArrayList<>();
                List<Comment> comments = allMsg(reply,tempReplys);
                System.out.println("=================================");
                System.out.println(comments);
                //获取以及评论
                for (Comment oneReply : reply) {
                    oneReply.setParentComment(new Comment(comment.getId(), comment.getNickname(), comment.getEmail(), comment.getAvatar(), comment.getCreateTime(),comment.getAdminComment(), null, null));
                    allReplyComment.add(oneReply);
                }
                //获取往下的评论
                for (Comment last : comments) {
                        allReplyComment.add(last);
                }
                comment.setReplyComments(allReplyComment);
                allReplyComment = new ArrayList<>();
            }
            }

        return parentComments;
    }


    //存放迭代找出所有子代的集合
    List<Comment> tempReplys = new ArrayList<>();




    public List<Comment> allMsg(List<Comment>  replyComments,List<Comment> tempReplys) {

        for (Comment reply : replyComments) {
            //根据子级id 对所有数据的父类进行查询
            List<Comment> parent = commentMapper.listCommentByParentId(reply.getId());
            //判断查询子类评论中是否是别人的父评论
            if (!(parent.isEmpty())) {
                //如果是的话进行循环
                for (Comment parents : parent) {
                    parents.setParentComment(new Comment(reply.getId(), reply.getNickname(), reply.getEmail(), reply.getAvatar(), reply.getCreateTime(),reply.getAdminComment(),null, null));
                    tempReplys.add(parents);
                }
                allMsg(parent,tempReplys);
            }
        }
        return tempReplys;
    }

    /**
     *  根据bolgId获取所有的父评论中的所有数据，
     * @param blogId
     * @return
     */
    public List<Comment>  getCommentMsg(Long blogId){

        List<Comment> parentComments = commentMapper.listCommentByBlogIdAndParentNull(blogId);

        //查找没有父级的评论
        for (int i = 0; i < parentComments.size(); i++) {
            //查询根据父级id查询到的所有子级
            List<Comment> comment = commentMapper.listCommentByParentId(parentComments.get(i).getId());
            //循环将子评论中的子评论进行赋值
            for (int j = 0; j < comment.size(); j++) {
                //根据父级id查询出 父级的数据进行保存
                Comment replyComment = commentMapper.selectCommentById(parentComments.get(i).getId());
                comment.get(j).setParentComment(replyComment);
            }
            System.out.println("--------------------------------");
            parentComments.get(i).setReplyComments(comment);
            //根据父级id进行查找相应的父级进行设置
//                    comment.get(j).setParentComment(replyComment);
//                    oneRelyComment.add(replyComment);
            //进行设置子级评论
        }

        return parentComments;
    }



    @Override
    public int saveComment(Comment comment) {
        //获取comment对象中父评论的id
        Long parentCommentId = comment.getParentComment().getId();
        System.out.println(parentCommentId);
        //判断父级是否不等于 -1 默认的为-1 ，
        if (parentCommentId != -1  && parentCommentId !=null){
            // 不等于的话就进行设置评论的父级id
            comment.getParentComment().setId(parentCommentId);
        }else{
            //否则就把父级id设置为null
            comment.setParentComment(null);
        }
        //需要进行设置创建的时间
        comment.setCreateTime(new Date());

        return commentMapper.saveComment(comment);
    }

    @Override
    public List<Comment> listCommentByParentId(Long parentId) {
        List<Comment> comments = commentMapper.listCommentByParentId(parentId);
        return comments;
    }

    @Override
    public Comment selectCommentByParentId(Long parentId) {
        return commentMapper.selectParentComment(parentId);
    }

    @Override
    public Comment selectCommentById(Long commentId) {

        return commentMapper.selectCommentById(commentId);
    }


    //先查询出顶级的数据，父级id为空时获取

    //获取顶级节点放入一个新的集合中

    //对顶级集合循环遍历，对子代进行循环，找出子代的子代

    //存放迭代的找出的所有子代的集合

    //将顶级的节点放入集合，

    /**
     *
     * @param comments root根节点，blog不为空时的对象集合
     * @return
     */
//    private List<Comment> eachComment(List<Comment> comments){
//   List<Comment> commentsView = new ArrayList<>();
//   for (Comment comment : comments){
//       Comment c = new Comment();
//       BeanUtils.copyProperties(comment,c);
//       commentsView.add(c);
//   }
//        //合并评论的各层子代到第一级集合中
//        combineChildren(commentsView);
//     return commentsView;
//    }



    /**
     *
     * @param comments root根节点，blog不为空时的对象集合
     * @return
     */
//    private void combineChildren(List<Comment> comments){
//
//        for (Comment comment : comments){
//            List<Comment> replys1 = comment.getReplyComments();
//            for (Comment reply1 : replys1){
//                //循环迭代，找出子代，存放在tempReplys中
//                recursively(reply1);//就是循环将子级的评论放入同一个集合汇总
//            }
//            //修改顶级接待的reply集合为迭代处理后的集合
//            comment.setReplyComments(tempReplys);//设置处理之后的所有子节点
//            //清楚临时存放区
//            tempReplys = new ArrayList<>();
//
//
//        }
//
//    }


    //存放迭代找出所有子代的集合
//    private List<Comment> tempReplys = new ArrayList<>();

    /**
     *  剥洋葱，就是将所有父级节点中的子级节点进行获取，之后再次循环判断子级节点中是否还包含子级节点
     * @param comment 被迭代的对象
     */
//    private void recursively(Comment comment){
//        tempReplys.add(comment);//顶节点添加到临时存放集合
//        if (comment.getReplyComments().size()>0){//判断顶点节点中是否有字节存在
//            List<Comment> replys = comment.getReplyComments();//之后将所有的字节点放入同一个集合，
//            for (Comment reply : replys){//进行循环添加
//                tempReplys.add(reply);
//                if(reply.getReplyComments().size()>0){//直到保存子节点的集合中没有任何数据时才进行跳出
//                recursively(reply);
//                  }
//            }
//        }
//    }
}
