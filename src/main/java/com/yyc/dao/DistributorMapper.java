package com.yyc.dao;

import com.yyc.entity.Distributor;

import java.util.List;

/**
 * @program: SSM
 * @description: 分销商Mapper
 * @author: yyc
 * @create: 2019-12-05 19:54
 **/
public interface DistributorMapper {
    List<Distributor> selectAllDistributor();
}
