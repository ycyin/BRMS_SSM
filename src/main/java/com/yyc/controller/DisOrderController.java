package com.yyc.controller;

import com.yyc.entity.DisOrder;
import com.yyc.service.DisOrderService;
import com.yyc.vo.RespMsg;
import com.yyc.vo.request.OrderVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: SSM
 * @description: 分销订单
 * @author: yyc
 * @create: 2019-11-22 17:05
 **/

@Controller
public class DisOrderController {
    private static Logger logger = LoggerFactory.getLogger(DisOrderController.class);

    @Autowired
    private DisOrderService disOrderService;

    @RequestMapping("/addOrder")
    @ResponseBody
    public RespMsg addOrder(OrderVo disOrder){
        logger.info("getDisOrderInfo...>>>>>>>>>>>"+disOrder);
        return  this.disOrderService.addOrder(disOrder);
    }
}
