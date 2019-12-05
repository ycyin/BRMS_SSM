package com.yyc.service.impl;

import com.yyc.dao.PublisherMapper;
import com.yyc.entity.Publisher;
import com.yyc.service.PublisherService;
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
 * @description: 出版社
 * @author: yyc
 * @create: 2019-12-05 21:28
 **/
@Service("publisherService")
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    private PublisherMapper publisherMapper;
    @Override
    public RespMsg getSelectValueAndLabel() {
        List<Publisher> publishers = this.publisherMapper.selectAllPublisher();
        Map<String,Object> map = null;
        List<Map<String,Object>> res = new ArrayList<>();
        for (int i = 0; i < publishers.size() ; i++) {
            map = new HashMap<>();
            map.put("value",publishers.get(i).getId());
            map.put("label",publishers.get(i).getPubName());
            res.add(map);
        }
        return new RespMsg(ResultEnum.SELECT_SUCCESS,res);
    }
}
