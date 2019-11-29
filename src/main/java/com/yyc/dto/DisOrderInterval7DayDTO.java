package com.yyc.dto;

import java.util.Date;

/**
 * @program: SSM
 * @description: 近7天每天订单数量和每天的书本量
 * @author: yyc
 * @create: 2019-11-28 16:13
 **/
public class DisOrderInterval7DayDTO {
    private Date ordDate;
    private Integer count;

    public Date getOrdDate() {
        return ordDate;
    }

    public void setOrdDate(Date ordDate) {
        this.ordDate = ordDate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
