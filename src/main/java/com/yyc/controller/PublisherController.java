package com.yyc.controller;

import com.yyc.service.PublisherService;
import com.yyc.vo.RespMsg;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**************************************
 * @author 尹以操 E-mail:34782655@qq.com
 * @version 创建/修改时间：
 * 类说明: 出版社
 ***************************************
 */
@Controller
@RequestMapping("/publisher")
public class PublisherController {
    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @RequiresPermissions("publisher:get")
    @RequestMapping("/getPublisherServiceSelectValueAndLabel")
    @ResponseBody
    public RespMsg getSelectValueAndLabel(){
        return publisherService.getSelectValueAndLabel();
    }
}
