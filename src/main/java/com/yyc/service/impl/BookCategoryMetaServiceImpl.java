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

/**************************************
 * @author 尹以操 E-mail:34782655@qq.com
 * @version 创建/修改时间：
 * 类说明: 图书类别元信息
 ***************************************
 */
@Service("bookCategoryMetaService")
public class BookCategoryMetaServiceImpl implements BookCategoryMetaService {

    private final BookCategoryMetaMapper bookCategoryMetaMapper;
    @Autowired
    public BookCategoryMetaServiceImpl(BookCategoryMetaMapper bookCategoryMetaMapper) {
        this.bookCategoryMetaMapper = bookCategoryMetaMapper;
    }

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
