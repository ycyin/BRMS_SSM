package com.yyc.dao;

import com.yyc.entity.Publisher;

import java.util.List;

/**
 * @program: SSM
 * @description: 出版社Mapper
 * @author: yyc
 * @create: 2019-12-05 21:17
 **/
public interface PublisherMapper {
    List<Publisher> selectAllPublisher();
}
