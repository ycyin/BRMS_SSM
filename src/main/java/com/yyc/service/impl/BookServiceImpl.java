package com.yyc.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yyc.dao.BookDTOMapper;
import com.yyc.dao.BookMapper;
import com.yyc.dto.BookDTO;
import com.yyc.entity.Book;
import com.yyc.service.BookService;
import com.yyc.vo.PageVo;
import com.yyc.vo.RespMsg;
import com.yyc.vo.ResultEnum;
import com.yyc.vo.request.BookListVo;
import com.yyc.vo.request.SearchAndPageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("bookService")
public class BookServiceImpl implements BookService {
    private static Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookDTOMapper bookDTOMapper;

    @Override
    public RespMsg getBookList(SearchAndPageVo searchAndPageVo) {
        logger.info("SearchAndPageVo-->>>"+searchAndPageVo);
        Page<Object> page = PageHelper.startPage(searchAndPageVo.getCurrentPage(), searchAndPageVo.getPageSize());
//        List<Book> books = this.bookMapper.selectAllBook(searchAndPageVo);
        List<BookDTO> books = this.bookDTOMapper.selectAllBook(searchAndPageVo);
        Map<String,Object> res = new HashMap<>();
        res.put("total",page.getTotal());
        res.put("result",books);
        return new RespMsg(ResultEnum.SELECT_SUCCESS,res);
    }

    @Transactional
    @Override
    public RespMsg addBook(Book book) {
        Integer res = 0;
        try{
             res = this.bookMapper.insertBook(book);
            return res >= 1 ? new RespMsg(ResultEnum.ADD_SUCCESS,res):
                    new RespMsg(ResultEnum.ADD_FAILD,res);
        }catch (Exception e){
            if(res > 0){
                return new RespMsg(ResultEnum.ADD_SUCCESS,res);
            }
            if(e.getCause().toString().contains("Duplicate")){
                return new RespMsg(ResultEnum.ADD_FAILD_HAS_BOOK_DUPLICATE,res);
            }else{
                return new RespMsg(ResultEnum.ADD_FAILD_UNKNOW,res);
            }
        }

    }

    @Transactional
    @Override
    public RespMsg modifyBookByPrimaryKey(Book book) {
        Integer res = 0;
        try{
            res = this.bookMapper.updateBook(book);
            return res >= 1 ? new RespMsg(ResultEnum.UPDATE_SUCCESS,res):
                    new RespMsg(ResultEnum.UPDATE_FAILD,res);
        }catch (Exception e){
            if(res > 0){
                return new RespMsg(ResultEnum.UPDATE_SUCCESS,res);
            }
            if(e.getCause().toString().contains("Duplicate")){
                return new RespMsg(ResultEnum.UPDATE_FAILD_HAS_BOOK_DUPLICATE,res);
            }else{
                return new RespMsg(ResultEnum.UPDATE_FAILD_UNKNOW,res);
            }
        }
    }

    @Override
    public RespMsg removeBookByPrimaryKey(Integer id) {
        Integer res = this.bookMapper.deleteBookByPrimaryKey(id);
        return res >= 1 ? new RespMsg(ResultEnum.DELETE_SUCCESS,res):
                new RespMsg(ResultEnum.DELETE_FAILD,res);
    }

    @Override
    public RespMsg getBookPressData() {
        List<Map<String,Object>> bookPressData = this.bookMapper.selectBookPressData();
        return new RespMsg(ResultEnum.SELECT_SUCCESS,bookPressData);
    }

    @Override
    public RespMsg getBookCategoryData() {
        List<Map<String, Object>> maps = this.bookMapper.selectBookNumberGroupByCategory();
        return new RespMsg(ResultEnum.SELECT_SUCCESS,maps);
    }

    @Transactional
    @Override
    public RespMsg addBookes(BookListVo bookListVo) {
        Integer res = 0;
        try{
            List<Book> bookList = new ArrayList<>( );
            bookListVo.getBookList().forEach(jsonStr -> bookList.add(JSON.parseObject(jsonStr, Book.class)));
            res = this.bookMapper.insertBookForeach(bookList);
            return new RespMsg(ResultEnum.IMPORT_SUCCESS,res);
        }catch (Exception e){
            if(res > 0){
                return new RespMsg(ResultEnum.IMPORT_SUCCESS,res);
            }
            if(e.getCause().toString().contains("Duplicate")){
                return new RespMsg(ResultEnum.IMPORT_FAILD_HAS_DUPLICATE,res);
            }else{
                return new RespMsg(ResultEnum.IMPORT_FAILD_UNKNOW,res);
            }

        }

    }

}
