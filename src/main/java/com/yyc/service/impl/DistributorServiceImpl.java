package com.yyc.service.impl;

import com.yyc.dao.DistributorMapper;
import com.yyc.entity.Distributor;
import com.yyc.service.DistributorService;
import com.yyc.vo.RespMsg;
import com.yyc.vo.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: SSM
 * @description: 分销商
 * @author: yyc
 * @create: 2019-12-05 20:06
 **/

@Service("distributorService")
public class DistributorServiceImpl implements DistributorService {

    @Autowired
    private DistributorMapper distributorMapper;

    @Override
    public RespMsg getSelectValueAndLabel() {
        List<Distributor> distributors = this.distributorMapper.selectAllDistributor();
        Map<String,Object>  map = null;
        List<Map<String,Object>> res = new ArrayList<>();
        for (int i = 0; i < distributors.size() ; i++) {
            map = new HashMap<>();
            map.put("value",distributors.get(i).getId());
            map.put("label",distributors.get(i).getDisName());
            res.add(map);
        }
        return new RespMsg(ResultEnum.SELECT_SUCCESS,res);
    }
}
