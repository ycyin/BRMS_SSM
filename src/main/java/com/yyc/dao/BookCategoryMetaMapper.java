package com.yyc.dao;

import com.yyc.entity.BookCategoryMeta;
import java.util.List;

/**************************************
 * @author 尹以操 E-mail:34782655@qq.com
 * @version 创建/修改时间：
 * 类说明: 图书类别元信息Mapper
 ***************************************
 */
public interface BookCategoryMetaMapper {
    /**
     * 查询所有的图书类别元信息列表
     * @return 图书类别元信息列表
     */
    List<BookCategoryMeta> selectAllBookCategoryMeta();

    /**
     * 根据类别名查询
     * @param categoryName 类别名
     * @return 图书类别元信息
     */
    BookCategoryMeta selectByCategoryName(String categoryName);

    /**
     * 插入图书类别元信息
     * @param bcm 图书类别元信息
     * @return SQL返回成功的条数
     */
    Integer insertBookCategoryMeta(BookCategoryMeta bcm);
}
