package com.yyc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yyc.dao.BookMapper;
import com.yyc.dao.DisOrderDTOMapper;
import com.yyc.dao.DisOrderMapper;
import com.yyc.dto.DisOrderDTO;
import com.yyc.entity.Book;
import com.yyc.entity.DisOrder;
import com.yyc.service.DisOrderService;
import com.yyc.vo.PageVo;
import com.yyc.vo.RespMsg;
import com.yyc.vo.ResultEnum;
import com.yyc.vo.request.OrderVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @program: SSM
 * @description: 分销订单
 * @author: yyc
 * @create: 2019-11-22 17:17
 **/

@Service("disOrderService")
public class DisOrderServiceImpl implements DisOrderService {

    private static Logger logger = LoggerFactory.getLogger(DisOrderServiceImpl.class);

    @Autowired
    private DisOrderMapper disOrderMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private DisOrderDTOMapper disOrderDTOMapper;

    @Transactional
    @Override
    public RespMsg addOrder(OrderVo order) {
        int bookId = order.getId(); //图书ID
        int disNo = order.getDisNo();//分销商id
        double bookPrice = order.getBookPrice();//图书单价
        int orderNumber = order.getOrderNumber();//图书数量
        Date ordDateTime = new Date();//订单时间；
        double ordTotalPrice = bookPrice * orderNumber;//订单总价
        DisOrder disOrder = new DisOrder(disNo,bookId,ordDateTime,orderNumber,
                1,bookPrice,ordTotalPrice,"进行中");

        Book updateBook = new Book();
        updateBook.setId(bookId);
        updateBook.setBookRepertorySize(order.getBookRepertorySize() - orderNumber);

        Integer res = 0;
        Integer res1 = 0;
        try{
            res = this.disOrderMapper.insertDisOrder(disOrder);//插入订单表数据
            res1 = this.bookMapper.updateBook(updateBook); //更新图书库存
            return (res >= 1 && res1>=1) ? new RespMsg(ResultEnum.DISORDER_ADD_SUCCESS,ordTotalPrice):
                    new RespMsg(ResultEnum.ADD_FAILD,res);
        }catch (Exception e){
            if(res > 0){
                return new RespMsg(ResultEnum.DISORDER_ADD_SUCCESS,ordTotalPrice);
            }else{
                return new RespMsg(ResultEnum.ADD_FAILD_UNKNOW,res);
            }
        }
    }

    @Override
    public RespMsg getOrderList(PageVo pageVo) {
        logger.info("PageVO-->>>"+pageVo);
        Page<Object> page = PageHelper.startPage(pageVo.getCurrentPage(), pageVo.getPageSize());
        List<DisOrderDTO> orderes = this.disOrderDTOMapper.selectAllOrder();
        Map<String,Object> res = new HashMap<>();
        res.put("total",page.getTotal());
        res.put("result",orderes);
        return new RespMsg(ResultEnum.SELECT_SUCCESS,res);
    }

    /**
     * 取消订单的逻辑：修改t_book表中的库存，修改t_disorder表中的时间和取消订单标识
     * SQL实现
     * @param id
     * @return
     */
    @Transactional
    @Override
    public RespMsg cancelOrder(Integer id) {
        if (id != null) {
            DisOrder disOrder = this.disOrderMapper.selectOrderById(id);
            if (disOrder.getOrdEndDateTime() != null) { //说明这个订单已经完成或者已经标记
                return  new RespMsg(ResultEnum.THIS_ORDER_NOT_CANCEL,disOrder);
            }else{
                Integer res = this.disOrderMapper.updateCancelOrder(id);
                return res >= 1 ? new RespMsg(ResultEnum.DISORDER_CANCEL_SUCCESS,res):
                        new RespMsg(ResultEnum.DISORDER_CANCEL_FAILED,res);
            }
        }
        return new RespMsg(ResultEnum.HAS_NULL,null);
    }

    @Override
    public RespMsg modifyOrderStatus(Integer id) {
        if(id != null){
            DisOrder disOrder = this.disOrderMapper.selectOrderById(id);
            if (disOrder.getOrdEndDateTime() != null) { //说明这个订单已经完成或者已经取消
                return  new RespMsg(ResultEnum.THIS_ORDER_NOT_MODIFY,disOrder);
            }else {
                Integer res = this.disOrderMapper.updateOrderStatus(id);
                return res >= 1 ? new RespMsg(ResultEnum.DISORDER_MODIFY_STATUS_SUCCESS, res) :
                        new RespMsg(ResultEnum.DISORDER_MODIFY_STATUS_FAILED, res);
            }
        }
        return new RespMsg(ResultEnum.HAS_NULL,null);
    }
}
