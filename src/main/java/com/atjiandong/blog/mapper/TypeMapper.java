package com.atjiandong.blog.mapper;

import com.atjiandong.blog.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author atjiandong
 * @create 2022-08-31 8:38
 */
@Mapper
@Repository
public interface TypeMapper {

    //查询总数最大值
    Long selectMax();

    //根据名称查询 Type
    Type getTypeName(String name);

//    保存
    int save(Type type);

//    查询所哟Type并进行 0 - num的分页
    List<Type> listType(Integer num);

//查询所有数据之后根据id进行排序
    List<Type> listAllType();

    //查询所有数据，并自己进行分页操作， index-num
    List<Type> selectTypes(Long index, Long num);

    //    根据id 查询单个
    Type getTypeById(@Param("id") Long id);

    //分组查询每个type中包含的blog，并按照数量进行排序(进行了连表查询)
    List<Type> getTypesByLimt(Integer num);

    //进行查询后分组，只有类型信息 博客id 数量


//    修改
    void updateType(Type type);
//    删除
    void deleteTypeById(Long id);


    //根据blogid查询相应的type
    Type foundBlogType(Long blogId);



}
