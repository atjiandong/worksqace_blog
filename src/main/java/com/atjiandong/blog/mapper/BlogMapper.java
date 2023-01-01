package com.atjiandong.blog.mapper;

import com.atjiandong.blog.pojo.Archives;
import com.atjiandong.blog.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author atjiandong
 * @create 2022-09-11 8:46
 */
@Mapper
@Repository
public interface BlogMapper {


    //根据传入的对象是否符合 条件进行查询数据和 类型
    List<Blog> selectBlogAndType(Blog blog);
    //直接查询所有的数据（进行分页）
    List<Blog> selectAllBlog();
    //查询根据创建时间排序之后的分页数据
    List<Blog> selectRecommend(Integer num);




    //添加博客
    int saveBlog(@Param("blog")Blog blog);
    //查询最大值
    Long selectBlogIdMax();

    //根据id进行查找博客（包含type进行连表查询）
    Blog  selectBlogById(Long id);


    //修改
    void updateBlog(Long id, @Param("blog") Blog blog);
    //删除
    void deleteBlog(Long id);


    //全局查询使用的方法，需要进行判断是否存在值判断查询
    List<Blog> queryBlogByTitleAndContent(String queryName);


    //可以查询出一个blog中的所有数据
    Blog selectAllBlogMsg(Long id);
    //根据一个type进行查询所有博客信息
    List<Blog> selectAllBlogMsgByTypeId(Long typeId);


    //修改次数
    int updateBlogView(Long id);

    //查询归档页面需要显示的内容
    List<Archives>  selectArchive(String year);

    //查询所有的年份，根据
    List<String> selectAllYear();

    //查询出blog的总数
    int selectBlogCount();


}
