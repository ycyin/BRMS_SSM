package com.yyc.dao;

import com.yyc.entity.Book;
import com.yyc.vo.request.SearchAndPageVo;

import java.util.List;
import java.util.Map;

public interface BookMapper {
    List<Book> selectAllBook(SearchAndPageVo searchAndPageVo);
    Integer insertBook(Book book);
    Integer updateBook(Book book);
    Integer deleteBookByPrimaryKey(Integer id);
    // 查询出版社分析的图表信息，包括三个字段：出版社，图书数量，图书种类
    List<Map<String,Object>> selectBookPressData();
    // 根据类别分组查询图书数量
    List<Map<String,Object>> selectBookNumberGroupByCategory();

    //批量插入数据
    int insertBookForeach(List<Book> books);

    Book selectBookByPrimaryKey(Integer id);

    /**
     * 统计图书总数
     * @return
     */
    Integer selectCountAll();
}
