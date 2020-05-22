package com.yyc.controller;

import com.yyc.service.DistributorService;
import com.yyc.vo.RespMsg;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**************************************
 * @author 尹以操 E-mail:34782655@qq.com
 * @version 创建/修改时间：
 * 类说明: 分销商
 ***************************************
 */
@Controller
@RequestMapping("/distributor")
public class DistributorController {
    private final DistributorService distributorService;

    @Autowired
    public DistributorController(DistributorService distributorService) {
        this.distributorService = distributorService;
    }

    @RequiresPermissions("distributor:get")
    @RequestMapping("/getDistributorSelectValueAndLabel")
    @ResponseBody
    public RespMsg getSelectValueAndLabel(){
        return distributorService.getSelectValueAndLabel();
    }
}
