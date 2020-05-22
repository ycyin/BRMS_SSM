package com.yyc.dao;

import com.yyc.dto.DisOrderDTO;
import com.yyc.dto.DisOrderInterval7DayDTO;

import java.util.List;

/**************************************
 * @author 尹以操 E-mail:34782655@qq.com
 * @version 创建/修改时间：
 * 类说明: 分销订单视图
 ***************************************
 */
public interface DisOrderDTOMapper {
    /**
     * 查询所有的订单列表
     * @return 订单列表
     */
    List<DisOrderDTO> selectAllOrder();

    /**
     * 查询近7天订单数量
     * @return  近7天订单数量
     */
    List<DisOrderInterval7DayDTO> selectDisOrderInterval7DayByOrderCount();

    /**
     * 查询近7天订单图书数量合
     * @return 7天订单图书数量合
     */
    List<DisOrderInterval7DayDTO> selectDisOrderInterval7DayByOrderSumBook();
}
