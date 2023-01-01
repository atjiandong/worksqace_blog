package com.atjiandong.blog.service;

import com.atjiandong.blog.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author atjiandong
 * @create 2022-08-31 9:42
 */
public interface TypeService {

    //查询最大id
    Long selectMax();

    //查询是否有相同的分类
    Type getTypeName(String  name);

    //    保存
    int save(Type type);
    //    查询单个
    Type getTypeById(Long id);
    //    查询多个
//    Page<Type> listType(Pageable pageable);
    List<Type> listType(Integer num);

    List<Type> listAllType();

  List<Type> getTypesByLimt(Integer num);

    //分页操作
    List<Type> selectTypes(Long index, Long num);
    //    修改
    void updateType(Type type);
    //    删除
    void deleteById(Long id);

}
