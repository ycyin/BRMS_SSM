package com.yyc.dao;

import com.yyc.entity.DisOrder;

import java.util.List;

/**
 * @program: SSM
 * @description: 分销订单
 * @author: yyc
 * @create: 2019-11-22 16:50
 **/
public interface DisOrderMapper {
    Integer insertDisOrder(DisOrder disOrder);
    DisOrder selectOrderById(Integer id);
    Integer updateCancelOrder(Integer id);
    Integer updateOrderStatus(Integer id);
}
