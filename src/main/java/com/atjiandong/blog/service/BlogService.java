package com.atjiandong.blog.service;

import com.atjiandong.blog.pojo.Archives;
import com.atjiandong.blog.pojo.Blog;
import com.atjiandong.blog.pojo.Page;

import java.util.List;
import java.util.Map;

/**
 * @author atjiandong
 * @create 2022-09-11 9:03
 */
public interface BlogService {

    //查询所有数据
    List<Blog> selectAllBlog(Page page);
    //查询根据时间排序之后的分页数据
    List<Blog> selectRecommend(Integer num);


    //根据条件进行查询数据和 类型
    List<Blog> selectBlogAndType(Blog blog);
    //添加
    int saveBlog(Blog blog);
    //查询最大值
    Long selectBlogIdMax();

    //根据id进行查找
    Blog  selectBlogById(Long id);
    //修改
    void updateBlog(Long id, Blog blog);
    //删除
    void deleteBlog(Long id);


    //全局查询使用的方法，需要进行判断是否存在值判断查询
    List<Blog> queryBlogByTitleAndContent(String query,Page page);

    //可以查询出一个blog中的所有数据
    Blog selectAllBlogMsg(Long id);

    List<Blog> selectAllBlogMsgByTypeId(Long typeId);


    //前端进行获取content并进行转换为html
    Blog getAndConvert(Long id);

    //修改次数
    int updateBlogView(Long id);


    //将归档的数据进行搜索于设置
    public Map<String,List<Archives>> archiveBlog();


    //查询出blog的总数
    int selectBlogCount();
}
