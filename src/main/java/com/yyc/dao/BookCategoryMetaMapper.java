package com.yyc.dao;

import com.yyc.entity.BookCategoryMeta;

import java.util.List;

/**
 * @program: SSM
 * @description: 图书类别元信息Mapper
 * @author: yyc
 * @create: 2019-12-05 21:16
 **/
public interface BookCategoryMetaMapper {
    List<BookCategoryMeta> selectAllBookCategoryMeta();
}
