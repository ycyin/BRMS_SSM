package com.yyc.dao;

import com.yyc.dto.DisOrderDTO;
import com.yyc.dto.DisOrderInterval7DayDTO;

import java.util.List;

/**
 * @program: SSM
 * @description: 分销订单视图
 * @author: yyc
 * @create: 2019-11-26 10:35
 **/
public interface DisOrderDTOMapper {
    List<DisOrderDTO> selectAllOrder();
    List<DisOrderInterval7DayDTO> selectDisOrderInterval7DayByOrderCount();
    List<DisOrderInterval7DayDTO> selectDisOrderInterval7DayByOrderSumBook();
}
