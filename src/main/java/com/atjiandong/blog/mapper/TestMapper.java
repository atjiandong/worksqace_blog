package com.atjiandong.blog.mapper;

import com.atjiandong.blog.pojo.Type;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author atjiandong
 * @create 2022-09-01 12:07
 */
// 在对应的Mapper上面继承基本的接口 BaseMapper
@Repository // 代表持久层
public interface TestMapper extends BaseMapper<Type> {
    // 所有的CRUD操作都已经基本完成了
    // 你不需要像以前的配置一大堆文件了
}
