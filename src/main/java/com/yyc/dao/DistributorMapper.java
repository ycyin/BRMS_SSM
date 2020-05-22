package com.yyc.dao;

import com.yyc.entity.Distributor;

import java.util.List;


/**************************************
 * @author 尹以操 E-mail:34782655@qq.com
 * @version 创建/修改时间：
 * 类说明: 分销商Mapper
 ***************************************
 */
public interface DistributorMapper {
    /**
     * 查询所有的分销商信息
     * @return 所有分销商信息列表
     */
    List<Distributor> selectAllDistributor();

    /**
     * 统计分销商总数
     * @return 分销商总数
     */
    Integer selectCountAll();
}
