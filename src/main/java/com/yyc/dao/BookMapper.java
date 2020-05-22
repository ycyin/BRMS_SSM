package com.yyc.dao;

import com.yyc.entity.Book;
import java.util.List;
import java.util.Map;

/**************************************
 * @author 尹以操 E-mail:34782655@qq.com
 * @version 创建/修改时间：
 * 类说明: 图书信息
 ***************************************
 */
public interface BookMapper {

    /**
     * 插入图书
     * @param book 需要插入的图书信息
     * @return SQL执行受影响的条数
     */
    Integer insertBook(Book book);

    /**
     * 更新图书信息
     * @param book 需要更新的图书信息
     * @return SQL执行受影响的条数
     */
    Integer updateBook(Book book);

    /**
     * 根据图书表ID删除图书
     * @param id 图书表ID
     * @return SQL执行受影响的条数
     */
    Integer deleteBookByPrimaryKey(Integer id);

    /**
     * 查询出版社分析的信息
     * @return 出版社，图书数量，图书种类
     */
    List<Map<String,Object>> selectBookPressData();

    /**
     * 根据类别分组查询图书数量
     * @return 返回根据类别分组的图书类别：数量
     */
    List<Map<String,Object>> selectBookNumberGroupByCategory();

    /**
     * 批量插入数据
     * @param books 批量插入的图书数据
     * @return SQL执行成功的条数
     */
    int insertBookForeach(List<Book> books);

    /**
     * 根据图书主键查询图书信息
     * @param id 图书表ID
     * @return 图书信息
     */
    Book selectBookByPrimaryKey(Integer id);

    /**
     * 统计图书总数
     * @return 图书总数
     */
    Integer selectCountAll();
}
