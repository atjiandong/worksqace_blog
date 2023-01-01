package com.atjiandong.blog.service.impl;

import com.atjiandong.blog.mapper.TypeMapper;
import com.atjiandong.blog.pojo.Type;
import com.atjiandong.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author atjiandong
 * @create 2022-08-31 9:43
 */
@Service
public class TypeServiceImpl implements TypeService{


    @Autowired
    private TypeMapper typeMapper;


    @Override
    public Long selectMax() {
        return typeMapper.selectMax();
    }

    @Override
    public Type getTypeName(String name) {
        return typeMapper.getTypeName(name);
    }

    @Override
    public int save(Type type) {

        return typeMapper.save(type);
    }



    @Override
    public Type getTypeById(Long id) {
        return typeMapper.getTypeById(id);
    }

    @Override
    public List<Type> listType(Integer num) {
        return typeMapper.listType(num);
    }

//    @Override
//    public List<Type> listType(Integer num) {
//       return typeMapper.listType(num);
//    }

    @Override
    public List<Type> listAllType() {
        return typeMapper.listAllType();
    }

    @Override
    public List<Type> getTypesByLimt(Integer num) {
        return typeMapper.getTypesByLimt(num);
    }

    @Override
    public List<Type> selectTypes(Long index, Long num) {
        return typeMapper.selectTypes(index,num);
    }

    @Override
    public void updateType(Type type) {
         typeMapper.updateType(type);
    }

    @Override
    public void deleteById(Long id) {
        typeMapper.deleteTypeById(id);

    }

}
