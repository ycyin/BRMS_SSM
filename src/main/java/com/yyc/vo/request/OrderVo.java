package com.yyc.vo.request;

/**
 * @program: SSM
 * @description: 添加订单
 * @author: yyc
 * @create: 2019-11-22 15:58
 **/
public class OrderVo {
    private int id;//图书id
    private int disNo;//分销商id
    private int orderNumber;//订单数量（本）
    private double bookPrice;//图书单价

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDisNo() {
        return disNo;
    }

    public void setDisNo(int disNo) {
        this.disNo = disNo;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    @Override
    public String toString() {
        return "OrderVo{" +
                "id=" + id +
                ", disNo=" + disNo +
                ", orderNumber=" + orderNumber +
                ", bookPrice=" + bookPrice +
                '}';
    }
}
