package com.yyc.service;

import com.yyc.vo.RespMsg;
import com.yyc.vo.request.OrderVo;

/**
 * @program: SSM
 * @description: 分销订单
 * @author: yyc
 * @create: 2019-11-22 17:16
 **/
public interface DisOrderService {
    RespMsg addOrder(OrderVo book);
}
