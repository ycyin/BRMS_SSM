package com.yyc.controller;

import com.alibaba.fastjson.JSON;
import com.yyc.entity.Book;
import com.yyc.service.BookService;
import com.yyc.vo.PageVo;
import com.yyc.vo.RespMsg;
import com.yyc.vo.request.BookListVo;
import com.yyc.vo.request.SearchAndPageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BookController {
    private static Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @RequestMapping("/getBookList")
    @ResponseBody
    public RespMsg getBookList(SearchAndPageVo searchAndPageVo){
//        logger.info("getBookList...>>>>>>>>>"+searchAndPageVo);
        return  this.bookService.getBookList2(searchAndPageVo);
    }

    @RequestMapping("/addBook")
    @ResponseBody
    public RespMsg addBook(Book book){
        logger.info("addBook...");
        return  this.bookService.addBook(book);
    }

    @RequestMapping("/removeBook")
    @ResponseBody
    public RespMsg removeBook(Integer id){
        logger.info("removeBook...");
        return  this.bookService.removeBookByPrimaryKey(id);
    }

    @RequestMapping("/modifyBook")
    @ResponseBody
    public RespMsg modifyBook(Book book){
        logger.info("modifyBook...");
        return  this.bookService.modifyBookByPrimaryKey(book);
    }

    @RequestMapping("/getBookPressData")
    @ResponseBody
    public RespMsg getBookPressData(){
        logger.info("getBookPressData...");
        return  this.bookService.getBookPressData();
    }

    @RequestMapping("/getBookCategoryData")
    @ResponseBody
    public RespMsg getBookCategoryData(){
        logger.info("getBookCategoryData...");
        return  this.bookService.getBookCategoryData();
    }


    @RequestMapping("/importBookListData")
    @ResponseBody
    public RespMsg importBookListData(BookListVo datas){
        logger.info("importBookListData--->"+datas.toString());
        return this.bookService.addBookes(datas);
    }
}
