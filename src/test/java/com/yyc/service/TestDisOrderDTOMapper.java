package com.yyc.service;

import com.yyc.dao.DisOrderDTOMapper;
import com.yyc.dto.DisOrderInterval7DayDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @program: SSM
 * @description: 测试
 * @author: yyc
 * @create: 2019-11-28 16:33
 **/
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestDisOrderDTOMapper {

    @Autowired
    private DisOrderDTOMapper disOrderDTOMapper;
    @Test
    public void test() {
        List<DisOrderInterval7DayDTO> disOrderInterval7DayDTOS =
                disOrderDTOMapper.selectDisOrderInterval7DayByOrderCount();
        disOrderInterval7DayDTOS.forEach(dis ->{
            System.out.println(dis.getOrdDate()+"-->"+dis.getCount());
        });

        System.out.println("__________________分割线____________________________");
        List<DisOrderInterval7DayDTO> disOrderInterval7DayDTOS1 =
                disOrderDTOMapper.selectDisOrderInterval7DayByOrderSumBook();
        disOrderInterval7DayDTOS1.forEach(dis->{
            System.out.println(dis.getOrdDate()+"-->"+dis.getCount());
        });

    }
}
