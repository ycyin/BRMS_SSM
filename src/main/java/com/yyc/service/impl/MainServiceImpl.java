package com.yyc.service.impl;

import com.yyc.dao.BookMapper;
import com.yyc.dao.DistributorMapper;
import com.yyc.dao.IUserInfoMapper;
import com.yyc.service.MainService;
import com.yyc.vo.RespMsg;
import com.yyc.vo.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**************************************
 * @author 尹以操 E-mail:34782655@qq.com
 * @version 创建/修改时间：
 * 类说明:
 ***************************************
 */
@Service("mainService")
public class MainServiceImpl implements MainService {
    private BookMapper bookMapper;
    private DistributorMapper distributorMapper;
    private IUserInfoMapper userInfoMapper;

    @Autowired
    public MainServiceImpl(BookMapper bookMapper, DistributorMapper distributorMapper, IUserInfoMapper userInfoMapper) {
        this.bookMapper = bookMapper;
        this.distributorMapper = distributorMapper;
        this.userInfoMapper = userInfoMapper;
    }

    /**
     * 返回站点信息，包括：
     * 本站图书总数
     * 本站分销点总数
     * 本站店长总数
     * 本站分销员总数
     * 本站普通店员总数
     * @return
     */
    @Override
    public RespMsg getSiteData() {
        Integer bookNumber = bookMapper.selectCountAll();
        Integer distributorNumber = distributorMapper.selectCountAll();
        Integer shopKeeperNumber = userInfoMapper.selectCountShopKeeper();
        Integer distributorsNumber = userInfoMapper.selectCountDistributor();
        Integer shopBoyNumber = userInfoMapper.selectCountShopBoy();
        Map<String,Integer> map = new HashMap<>(5);
        map.put("本站图书总数",bookNumber);
        map.put("本站分销点总数",distributorNumber);
        map.put("本站店长总数",shopKeeperNumber);
        map.put("本站分销员总数",distributorsNumber);
        map.put("本站普通店员总数",shopBoyNumber);
        return new RespMsg(ResultEnum.SELECT_SUCCESS,map);
    }
}
