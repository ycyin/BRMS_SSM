package com.yyc.controller;

import com.yyc.service.PublisherService;
import com.yyc.vo.RespMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: SSM
 * @description: 出版社
 * @author: yyc
 * @create: 2019-12-05 21:34
 **/

@Controller
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @RequestMapping("/getPublisherServiceSelectValueAndLabel")
    @ResponseBody
    public RespMsg getSelectValueAndLabel(){
        return publisherService.getSelectValueAndLabel();
    }
}
