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

    /**
     * 根据类别名查询
     * @param categoryName
     * @return
     */
    BookCategoryMeta selectByCategoryName(String categoryName);

    Integer insertBookCategoryMeta(BookCategoryMeta bcm);
}
