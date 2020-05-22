package com.yyc.dao;

import com.yyc.dto.BookDTO;
import com.yyc.vo.request.SearchAndPageVo;
import java.util.List;

/**************************************
 * @author 尹以操 E-mail:34782655@qq.com
 * @version 创建/修改时间：
 * 类说明: 图书DTO
 ***************************************
 */
public interface BookDTOMapper {
    /**
     * 分页查询所有的图书
     * @param searchAndPageVo 分页信息
     * @return 分页后的图书信息列表
     */
    List<BookDTO> selectAllBook(SearchAndPageVo searchAndPageVo);
}
