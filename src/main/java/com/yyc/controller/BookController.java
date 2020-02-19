package com.yyc.controller;

import com.yyc.entity.Book;
import com.yyc.service.BookService;
import com.yyc.vo.RespMsg;
import com.yyc.vo.request.BookListVo;
import com.yyc.vo.request.SearchAndPageVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
    private static Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @RequiresPermissions("book:getBookList")
    @RequestMapping("/getBookList")
    @ResponseBody
    public RespMsg getBookList(SearchAndPageVo searchAndPageVo){
//        logger.info("getBookList...>>>>>>>>>"+searchAndPageVo);

        return  this.bookService.getBookList(searchAndPageVo);
    }

    @RequiresPermissions("book:addBook")
    @RequestMapping("/addBook")
    @ResponseBody
    public RespMsg addBook(Book book){
        logger.info("addBook...");
        return  this.bookService.addBook(book);
    }

    @RequiresPermissions("book:removeBook")
    @RequestMapping("/removeBook")
    @ResponseBody
    public RespMsg removeBook(Integer id){
        logger.info("removeBook...");
        return  this.bookService.removeBookByPrimaryKey(id);
    }

    @RequiresPermissions("book:modifyBook")
    @RequestMapping("/modifyBook")
    @ResponseBody
    public RespMsg modifyBook(Book book){
        logger.info("modifyBook...");
        return  this.bookService.modifyBookByPrimaryKey(book);
    }

    @RequiresPermissions("book:getBookPressData")
    @RequestMapping("/getBookPressData")
    @ResponseBody
    public RespMsg getBookPressData(){
        logger.info("getBookPressData...");
        return  this.bookService.getBookPressData();
    }

    @RequiresPermissions("book:getBookCategoryData")
    @RequestMapping("/getBookCategoryData")
    @ResponseBody
    public RespMsg getBookCategoryData(){
        logger.info("getBookCategoryData...");
        return  this.bookService.getBookCategoryData();
    }


    @RequiresPermissions("book:importBookListData")
    @RequestMapping("/importBookListData")
    @ResponseBody
    public RespMsg importBookListData(BookListVo datas){
        logger.info("importBookListData--->"+datas.toString());
        return this.bookService.addBookes(datas);
    }
}
