package com.yyc.dto;

import java.util.Date;

/**************************************
 * @author 尹以操 E-mail:34782655@qq.com
 * @version 创建/修改时间：
 * 类说明: 近7天每天订单数量和每天的书本量
 ***************************************
 */
public class DisOrderInterval7DayDTO {
    /** 日期 */
    private Date ordDate;
    /** 书本量 */
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
