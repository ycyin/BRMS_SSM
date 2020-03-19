package com.yyc.service.impl;

import com.yyc.dao.ISysPermissionMapper;
import com.yyc.entity.SysPermission;
import com.yyc.service.PermissionService;
import com.yyc.vo.RespMsg;
import com.yyc.vo.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: SSM
 * @description: 权限
 * @author: yyc
 * @create: 2020-03-19 17:00
 **/
@Service("permissionServiceImpl")
public class PermissionServiceImpl implements PermissionService {


    @Autowired
    ISysPermissionMapper iSysPermissionMapper;
    /**
     * 获取所有权限信息，
     * @return
     */
    @Override
    public RespMsg getAllPermission() {
        List<SysPermission> allPermission = this.iSysPermissionMapper.findAllPermission();
        return new RespMsg(ResultEnum.SELECT_SUCCESS,allPermission);
    }
}
