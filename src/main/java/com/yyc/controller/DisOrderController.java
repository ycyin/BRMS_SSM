package com.yyc.controller;

import com.yyc.service.DisOrderService;
import com.yyc.vo.PageVo;
import com.yyc.vo.RespMsg;
import com.yyc.vo.request.OrderVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**************************************
 * @author 尹以操 E-mail:34782655@qq.com
 * @version 创建/修改时间：
 * 类说明: 分销订单
 ***************************************
 */
@Controller
@RequestMapping("/disOrder")
public class DisOrderController {
    private static final Logger logger = LoggerFactory.getLogger(DisOrderController.class);
    private final DisOrderService disOrderService;

    @Autowired
    public DisOrderController(DisOrderService disOrderService) {
        this.disOrderService = disOrderService;
    }

    @RequiresPermissions("disOrder:add")
    @RequestMapping("/addOrder")
    @ResponseBody
    public RespMsg addOrder(OrderVo disOrder){
        logger.info("addOrder...>>>>>>>>>>>"+disOrder);
        return  this.disOrderService.addOrder(disOrder);
    }

    @RequiresPermissions("disOrder:get")
    @RequestMapping("/getOrderList")
    @ResponseBody
    public RespMsg getOrderList(PageVo pageVo){
        logger.info("getOrderList...>>>>>>>>>>>"+pageVo);
        return  this.disOrderService.getOrderList(pageVo);
    }

    @RequiresPermissions("disOrder:cancel")
    @RequestMapping("/cancelOrder")
    @ResponseBody
    public RespMsg cancelOrder(Integer id){
        logger.info("cancelOrder...>>>>>>>>>>>"+id);
        return  this.disOrderService.cancelOrder(id);
    }

    @RequiresPermissions("disOrder:modify")
    @RequestMapping("/modifyOrderStatus")
    @ResponseBody
    public RespMsg modifyOrderStatus(Integer id){
        logger.info("modifyOrderStatus...>>>>>>>>>>>"+id);
        return  this.disOrderService.modifyOrderStatus(id);
    }

    @RequiresPermissions("disOrder:get")
    @RequestMapping("/getDisOrderInterval7DayData")
    @ResponseBody
    public RespMsg getDisOrderInterval7DayData(){
        return  this.disOrderService.getDisOrderInterval7DayData();
    }
}
