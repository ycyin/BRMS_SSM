package com.yyc.controller;

import com.yyc.service.BookCategoryMetaService;
import com.yyc.vo.RespMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: SSM
 * @description: 图书类别元信息
 * @author: yyc
 * @create: 2019-12-05 21:34
 **/

@Controller
public class BookCategoryMetaController {

    @Autowired
    private BookCategoryMetaService bookCategoryMetaService;

    @RequestMapping("/getBookCategoryMetaValueAndLabel")
    @ResponseBody
    public RespMsg getSelectValueAndLabel(){
        return bookCategoryMetaService.getSelectValueAndLabel();
    }
}
