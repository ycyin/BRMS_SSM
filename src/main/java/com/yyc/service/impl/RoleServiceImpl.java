package com.yyc.service.impl;

import com.yyc.dao.ISysPermissionMapper;
import com.yyc.dao.ISysRoleMapper;
import com.yyc.entity.RolePermission;
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

import java.util.ArrayList;
import java.util.List;


/**************************************
 * @author 尹以操 E-mail:34782655@qq.com
 * @version 创建/修改时间：
 * 类说明: 角色、角色权限
 ***************************************
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    private final ISysRoleMapper roleMapper;
    private final ISysPermissionMapper permissMapper;

    @Autowired
    public RoleServiceImpl(ISysRoleMapper roleMapper, ISysPermissionMapper permissMapper) {
        this.roleMapper = roleMapper;
        this.permissMapper = permissMapper;
    }


    @Transactional
    @Override
    public RespMsg addRoleAndPermission(RoleAndPermissionVo rpVo) {
        if (null ==rpVo.getRoleName() || null == rpVo.getRoleDesc() || rpVo.getExistingPermissions().isEmpty())
            return new RespMsg(ResultEnum.HAS_NULL);
        try {

        String roleName = rpVo.getRoleName();
        SysRole sysRole = this.roleMapper.selectRoleByRoleName(roleName);
        if (sysRole != null && sysRole.getId() != null) { //说明是修改
            Integer roleId = sysRole.getId();
            //修改角色信息
            SysRole sr = new SysRole();
            sr.setRole(rpVo.getRoleName());
            sr.setDescription(rpVo.getRoleDesc());
            sr.setAvailable((byte) 1);
            Integer res1 = this.roleMapper.updateRoleByRoleName(sr);
            //删除现有角色权限信息
            Integer res2 = this.permissMapper.deletePermissionsByRoleId(roleId);
            //添加修改后的角色权限信息
            List<Integer> permissions = rpVo.getExistingPermissions();
            List<RolePermission> list  =  new ArrayList<>();
            permissions.stream().forEach(permissionId ->{
                RolePermission rp = new RolePermission();
                rp.setRoleId(roleId);
                rp.setPermissionId(permissionId);
                list.add(rp);
            });
            Integer res3 = this.permissMapper.insertPermissionsWithRoleId(list);
            return new RespMsg(ResultEnum.UPDATE_SUCCESS);
        }else{//添加
            SysRole sr = new SysRole();
            sr.setAvailable((byte) 1);
            sr.setRole(rpVo.getRoleName());
            sr.setDescription(rpVo.getRoleDesc());
            Integer res1 = this.roleMapper.insertRole(sr);
            //使用mybatis的selectKey标签可以返回添加后的ID
            Integer roleId = sr.getId();
            System.out.println("----------"+sr.getId());
            List<Integer> permissions = rpVo.getExistingPermissions();
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
    }

    @Override
    public RespMsg findAllRole() {
        List<SysRole> sysRoles = this.roleMapper.selectAllRole();
        return new RespMsg(ResultEnum.SELECT_SUCCESS,sysRoles);
    }
}
