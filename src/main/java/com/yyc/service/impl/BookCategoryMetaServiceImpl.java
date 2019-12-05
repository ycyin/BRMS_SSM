package com.yyc.service.impl;

import com.yyc.dao.BookCategoryMetaMapper;
import com.yyc.entity.BookCategoryMeta;
import com.yyc.service.BookCategoryMetaService;
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
 * @description: 图书类别元信息
 * @author: yyc
 * @create: 2019-12-05 21:29
 **/
@Service("bookCategoryMetaService")
public class BookCategoryMetaServiceImpl implements BookCategoryMetaService {

    @Autowired
    private BookCategoryMetaMapper bookCategoryMetaMapper;
    @Override
    public RespMsg getSelectValueAndLabel() {
        List<BookCategoryMeta> bookCategoryMeta = this.bookCategoryMetaMapper.selectAllBookCategoryMeta();
        Map<String,Object> map = null;
        List<Map<String,Object>> res = new ArrayList<>();
        for (int i = 0; i < bookCategoryMeta.size() ; i++) {
            map = new HashMap<>();
            map.put("value",bookCategoryMeta.get(i).getId());
            map.put("label",bookCategoryMeta.get(i).getCategoryName());
            res.add(map);
        }
        return new RespMsg(ResultEnum.SELECT_SUCCESS,res);
    }
}
