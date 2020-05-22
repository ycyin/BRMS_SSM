package com.yyc.dao;

import com.yyc.entity.DisOrder;


/**************************************
 * @author 尹以操 E-mail:34782655@qq.com
 * @version 创建/修改时间：
 * 类说明: 分销订单
 ***************************************
 */
public interface DisOrderMapper {
    /**
     * 插入分销订单
     * @param disOrder 需要插入的分销订单信息
     * @return SQL执行受影响的条数
     */
    Integer insertDisOrder(DisOrder disOrder);

    /**
     * 根据订单ID查询订单信息
     * @param id 订单表ID
     * @return 订单信息
     */
    DisOrder selectOrderById(Integer id);

    /**
     * 取消订单
     * @param id 订单ID
     * @return SQL执行受影响的条数
     */
    Integer updateCancelOrder(Integer id);

    /**
     * 修改订单状态
     * @param id 订单ID
     * @return SQL执行受影响的条数
     */
    Integer updateOrderStatus(Integer id);
}
