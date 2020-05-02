package com.yyc.service;

import com.yyc.vo.RespMsg;

/**
 * @program: SSM
 * @description:
 * @author: yyc
 * @create: 2020-05-02 19:03
 **/
public interface MainService {

    /**
     * 返回站点信息，包括：
     * 本站图书总数
     * 本站分销点总数
     * 本站店长总数
     * 本站分销员总数
     * 本站普通店员总数
     * @return
     */
    RespMsg getSiteData();
}
