package com.yyc.service;


import com.yyc.entity.Book;
import com.yyc.vo.PageVo;
import com.yyc.vo.RespMsg;
import com.yyc.vo.request.BookCameraVo;
import com.yyc.vo.request.BookListVo;
import com.yyc.vo.request.SearchAndPageVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface BookService {
    RespMsg getBookList(SearchAndPageVo searchAndPageVo);
    RespMsg addBook(Book book);
    RespMsg addBookByCamera(BookCameraVo book);
    RespMsg modifyBookByPrimaryKey(Book book);
    RespMsg removeBookByPrimaryKey(Integer id);
    RespMsg getBookPressData();
    RespMsg getBookCategoryData();
    RespMsg addBookes(BookListVo bookListVo);
    void exportAllBooksExcel(HttpServletResponse response) throws IOException;
}
