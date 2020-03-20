package com.yyc.service.impl;

import com.yyc.dao.ISysRoleMapper;
import com.yyc.entity.SysRole;
import com.yyc.service.RoleService;
import com.yyc.vo.RespMsg;
import com.yyc.vo.ResultEnum;
import com.yyc.vo.request.RoleAndPermissionVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * @program: SSM
 * @description: 角色、角色权限
 * @author: yyc
 * @create: 2020-03-20 14:51
 **/
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private ISysRoleMapper roleMapper;



    @Transactional
    @Override
    public RespMsg addRoleAndPermission(RoleAndPermissionVo rp) {
        if (null ==rp.getRoleName() || null == rp.getRoleDesc() || rp.getExistingPermissions().isEmpty())
            return new RespMsg(ResultEnum.HAS_NULL);
        try {

        String roleName = rp.getRoleName();
        SysRole sysRole = this.roleMapper.selectRoleByRoleName(roleName);
        if (sysRole != null) { //说明是修改


        }else{//添加
            SysRole sr = new SysRole();
            sr.setAvailable((byte) 1);
            sr.setRole(rp.getRoleName());
            sr.setDescription(rp.getRoleDesc());
            Integer res1 = this.roleMapper.insertRole(sr);
            //使用mybatis的selectKey标签可以返回添加后的ID
            Integer roleId = sr.getId();
            System.out.println("----------"+sr.getId());
            List<Integer> permissions = rp.getExistingPermissions();
            permissions.stream().forEach(permissionId ->{
                this.roleMapper.insertRoleAndPermission(roleId,permissionId);
            });
            return  new RespMsg(ResultEnum.ADD_SUCCESS);
            }
        }catch (Exception e){
            e.printStackTrace();
//            if(res > 0 && res2 > 0){
//                return new RespMsg(ResultEnum.ADD_SUCCESS,res);
//            }
            //手动回滚事务，由于已经被捕获异常，故事务不会自动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            if(e.getCause().toString().contains("Duplicate")){
                return new RespMsg(ResultEnum.OPTIONS_FAILD_HAS_ROLE_DUPLICATE);
            }else{
                return new RespMsg(ResultEnum.ADD_FAILD_UNKNOW);
            }
        }
        return null;
    }

    @Override
    public RespMsg findAllRole() {
        List<SysRole> sysRoles = this.roleMapper.selectAllRole();
        return new RespMsg(ResultEnum.SELECT_SUCCESS,sysRoles);
    }
}
