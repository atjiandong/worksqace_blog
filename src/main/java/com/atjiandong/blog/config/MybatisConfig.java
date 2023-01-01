package com.atjiandong.blog.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author atjiandong
 * @create 2022-08-31 13:39
 */
@Configuration //标注的类就相当于XML 配置文件
@MapperScan("com.atjiandong.blog.mapper")
public class MybatisConfig {
//    /**
//     在Configuration标识的注解中
//     返回值为java对象 , 该对象放到Spring容器中 使用@Bean 来修饰*/
//    @Bean
//    public MybatisPlusInterceptor mybatisPlusInterceptor() {
//        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
//        return interceptor;
//    }

    // 分页插件
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        return new PaginationInterceptor();
//    }
}
