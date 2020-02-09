package com.yyc.service;


import com.yyc.entity.Book;
import com.yyc.vo.PageVo;
import com.yyc.vo.RespMsg;
import com.yyc.vo.request.BookListVo;
import com.yyc.vo.request.SearchAndPageVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;

public interface BookService {
    RespMsg getBookList(SearchAndPageVo searchAndPageVo);
    RespMsg addBook(Book book);
    RespMsg modifyBookByPrimaryKey(Book book);
    RespMsg removeBookByPrimaryKey(Integer id);
    RespMsg getBookPressData();
    RespMsg getBookCategoryData();
    RespMsg addBookes(BookListVo bookListVo);
}
