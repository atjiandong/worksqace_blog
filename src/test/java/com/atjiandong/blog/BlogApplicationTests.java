package com.atjiandong.blog;


import com.atjiandong.blog.mapper.*;
import com.atjiandong.blog.pojo.*;
import com.atjiandong.blog.service.*;
import com.atjiandong.blog.util.MD5Util;
import com.atjiandong.blog.util.MarkdownUtils;
import com.atjiandong.blog.util.StringReLongUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.hamcrest.core.Is;
import org.jcp.xml.dsig.internal.SignerOutputStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.yaml.snakeyaml.tokens.CommentToken;

import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private TypeService typeService;
    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;
    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private BlogAndTagsService blogAndTagsService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentMapper commentMapper;



    @Test
    void contextLoads() {


//        Page<Tag> types = PageHelper.startPage(2, 5);
        List<Tag> list = tagService.listAllTag();
//        Iterator<Tag> iterator = types.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
//        List<Type> list = service.selectTypes(types.getStartRow(), types.getEndRow());
//        PageInfo info = new PageInfo(list);
//        System.out.println();
        System.out.println(list);
//        System.out.println(info);


    }

    @Test
    public void test1(){

//     service.updateType(new Type(new Long(5), "da"));


//        service.deleteById(new Long(2));


    }

    @Test
    public void testTagMethod(){

        //???????????????
//        Long l = tagService.selectMax();
//        System.out.println(l);

        //??????????????????????????????
//        Tag tag = tagService.getTagName("??????");
//        System.out.println(tag);
//    ??????
//        int asd = tagService.save(new Tag(new Long(4), "asd"));

//    ????????????

        //????????????
//        List<Tag> tags = tagService.listAllTag();
//        System.out.println(tags);
        //????????????
//        List<Tag> tagList = tagService.selectTags(new Long(1), new Long(2));
        //    ??????id ????????????
//        Tag tag = tagService.getTagById((long) 2);
//        System.out.println(tag);


//    ??????
//    ??????

//        List<Long> str = new ArrayList<Long>();
//        str.add((long) 0);
//        str.add((long) 2);
//        str.add((long) 3);
////        str.add((long) 4);
//        str.add((long) 5);

        String str = "1,4,3";
        List<Long> list = StringReLongUtil.StringReLong(str);
        System.out.println(list);

        List<Tag> tags = tagMapper.listTagAndString(list);
        System.out.println(tags);

    }

    @Test
    public void testBlog(){

//        Blog blog = new Blog();
//        blog.setType(new Type((long)1,"??????"));



//        List<Blog> blogs = blogService.selectBlogAndType(null);
//        System.out.println(blogs);
////
//        Blog blog1 = blogs.get(0);
//        System.out.println(blog1.getType());
//        List<Type> list = typeService.listAllType();
//        System.out.println(list);

        //????????????
//        Blog blog = new Blog();
//        blog.setTitle("aaaaaaaaaa");
//        blog.setId((long)7);
//        blog.setAppreciation(false);
//        blog.setCommentable(false);
//        blog.setPublished(false);
//        blog.setRecommend(false);
//        blog.setShareStatement(false);
//        blogMapper.updateBlog(blog.getId(),blog);

        Blog blog = blogMapper.selectBlogById((long) 1);
        System.out.println(blog);

        //?????????????????????
//        Long aLong = blogMapper.selectBlogIdMax();
//        System.out.println(aLong);
    }

    @Test
    public void test2(){

//        List<Long> list = new ArrayList<>();
//        list.add((long)1);
//        list.add((long)2);
//        list.add((long)3);
//        blogMapper.saveBlogIdAndTagId((long) 4,list);

//        List<Long> list = blogMapper.selectTagsIdByBlogId((long) 4);
//        System.out.println(list);
        String ids = "1,2,3";
        List<Long> list = StringReLongUtil.StringReLong(ids);
        List<Tag> tags = tagService.listTagAndString(null);
//        blogMapper.saveBlogIdAndTagId((long)5,list);
        System.out.println(tags);


//        User atjiandong = userService.checkUser("atjiandong", MD5Util.md5("123456"));
//        System.out.println(atjiandong);

    }

    @Test
    public void test4(){
//        com.atjiandong.blog.pojo.Page page = new com.atjiandong.blog.pojo.Page();
//        page.setPageSize(2);
//        List<Blog> blogs = blogMapper.selectAllBlog(page);
//        for (int i = 0; i < blogs.size(); i++) {
//            Type type = typeMapper.foundBlogType(blogs.get(i).getId());
//            blogs.get(i).setType(type);
//        }
//        System.out.println(blogs);

        //?????????????????????????????????
//        List<Type> typeGroup = typeService.getTypeGroup(2);
//        System.out.println(typeGroup.get(0).getBlogs().size());

        //????????????
//        List<Blog> blogs = blogMapper.selectRecommend(2);
//        System.out.println(blogs);

//        List<Tag> tagsGroup = tagService.getTagsGroup(null);
//        System.out.println(tagsGroup);


        //????????????????????????????????????/blog
//        List<Tag> tags = tagMapper.foundTagsPage(null);
//        for (int i = 0; i < tags.size(); i++) {
//            List<Blog> blogs = tagMapper.foundTagByBlogs(tags.get(i).getId());
//            tags.get(i).setBlogs(blogs);
//        }
//        System.out.println(tags);

        //????????????tag????????????blog
//        System.out.println(blogs);

//        List<Blog> blogs = blogService.selectRecommend(8);
//        System.out.println(blogs);


//        Page page =new Page();
//        page.setPageNum(1);
//        List<Blog> blog = blogService.queryBlogByTitleAndContent("???", page);
//        System.out.println(blog);

//        ??????????????????blog???????????????
//        Blog blog = blogMapper.selectAllBlogMsg((long) 1);
//        System.out.println(blog);

//        Page page =new Page();
//        page.setPageNum(1);
//        List<Blog> blog = blogService.selectAllBlog(page);
//        System.out.println(blog);


//        Blog blog = blogService.selectBlogById((long) 1);
//        System.out.println(blog);

//        int i = Integer.parseInt("1");
//        System.out.println(i);
    }


    @Test
    public void test3(){

//        ??????????????????blog???????????????
        Blog blog = blogMapper.selectAllBlogMsg((long) 1);
        System.out.println(blog);
        String content = blog.getContent();
        String s = MarkdownUtils.markdownToHtml(content);
        System.out.println(s);
    }

    @Test
    public void commentTest(){
        //??????????????????id?????????????????????


        //?????????????????????  ?????????????????????????????????????????????id??????blog??????????????????
//        Blog blog = blogMapper.selectBlogById(new Long(1));
//        Comment parentComment = new Comment(new Long(11));
//        Comment comment = new Comment(null,"????????????","null","null",null,null,parentComment);
//
//        System.out.println(comment.getParentComment().getId());
//        System.out.println(comment.getParentComment().getId());
//       Comment comments = commentService.selectCommentByParentId(comment.getParentComment().getId());
//       comment.setParentComment(comments);

//        commentService.saveComment(comment);
//
//
//        System.out.println(comment);

//        List<Comment> comments1 = new ArrayList<>();
////        ???????????????????????????
//        List<Comment> comments = commentService.listCommentByBlogIdAndParentNull(new Long(1));

//        for (int i = 0; i < comments.size(); i++) {
//            Comment comment1 = commentService.selectCommentByParentId(comments.get(i).getId());
//            comments.get(i).setParentComment(comment1);
//        }

//        for (int i = 0; i < comments.size(); i++) {
////            comments1  = commentMapper.listCommentByParentId(comments.get(i).getId());
////           comments.get(i).setReplyComments(comments1);
////        }


        List<Comment> comments = commentService.listCommentByBlogIdAndParentNull(new Long(1));
        System.out.println(comments);
//        for (int i = 0; i < comments2.size(); i++) {
//            comments2.get(i).getParentComment().setId(new Long(41));
//        }

//        List<Comment> comments = commentService.listCommentByParentId(new Long(41));


//        System.out.println(comments);

    }


    @Test
    public void testss(){

        List<Blog> blogs = blogAndTagsService.selectBlogIdByTagsId(new Long(1));

        //????????????????????????
//        List<Comment> parentComments = commentService.listCommentByBlogIdAndParentNull(new Long(1));


//
//        List<Comment>  allReplyComment= new ArrayList<>();

//        List<Comment> parentComments = commentService.listCommentByBlogIdAndParentNull(new Long(1));
//        System.out.println("---------------------------");
//        for (Comment comment :  parentComments){
//            List<Comment> reply = commentMapper.listCommentByParentId(comment.getId());
//            List<Comment> comments = allMsg(reply);
//            for (Comment oneReply : reply){
//                allReplyComment.add(oneReply);
//            }
//            for (Comment last : comments){
//                allReplyComment.add(last);
//            }
//            comment.setReplyComments(allReplyComment);
//        }


        System.out.println(blogs);

//        List<Comment> comment = new ArrayList<>();
//        //???????????????????????????
//        for (int i = 0; i < parentComments.size(); i++) {
//            //??????????????????id????????????????????????
//            List<Comment> comment = commentMapper.listCommentByParentId(parentComments.get(i).getId());
//            parentComments.get(i).setReplyComments(comment);
//        }

//

        //???????????????????????????
//        for (int i = 0; i < parentComments.size(); i++) {
//            //??????????????????id????????????????????????
//            List<Comment> comment = commentMapper.listCommentByParentId(parentComments.get(i).getId());
            //?????????????????????????????????????????????
//            for (int j = 0; j < comment.size(); j++) {
//                //????????????id????????? ???????????????????????????
//                Comment replyComment = commentMapper.selectCommentById(parentComments.get(i).getId());
//                comment.get(j).setParentComment(replyComment);
//            }




//            System.out.println("--------------------------------");
//            parentComments.get(i).setReplyComments(comment);
            //????????????id???????????????????????????????????????
//                    comment.get(j).setParentComment(replyComment);
//                    oneRelyComment.add(replyComment);
            //????????????????????????

//    }


    }

    //???????????????????????????????????????
    private List<Comment> tempReplys = new ArrayList<>();


    public List<Comment> allMsg(List<Comment>  replyComments) {

            for (Comment reply : replyComments) {
                //????????????id ????????????????????????????????????
                List<Comment> parent = commentMapper.listCommentByParentId(reply.getId());
                for (Comment parents : parent) {
                    parents.setParentComment(reply);
                    tempReplys.add(parents);
                }
                allMsg(parent);
            }
        return tempReplys;
    }


    @Test
    public void test(){

        List<String> strings = blogMapper.selectAllYear();
        System.out.println(strings);

        List<Archives> archives = blogMapper.selectArchive(strings.get(1));
        System.out.println(archives);


    }
}
