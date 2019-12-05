package com.yyc.controller;

import com.yyc.service.DistributorService;
import com.yyc.vo.RespMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: SSM
 * @description: 分销商
 * @author: yyc
 * @create: 2019-12-05 20:11
 **/
@Controller
public class DistributorController {

    @Autowired
    private DistributorService distributorService;

    @RequestMapping("/getSelectValueAndLabel")
    @ResponseBody
    public RespMsg getSelectValueAndLabel(){
        return distributorService.getSelectValueAndLabel();
    }
}
