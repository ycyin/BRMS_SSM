package com.yyc.dao;

import com.yyc.dto.BookDTO;
import com.yyc.vo.request.SearchAndPageVo;

import java.util.List;

/**
 * @program: SSM
 * @description: BookDTO
 * @author: yyc
 * @create: 2019-12-03 15:02
 **/
public interface BookDTOMapper {
    List<BookDTO> selectAllBook(SearchAndPageVo searchAndPageVo);
}
