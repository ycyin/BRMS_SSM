package com.yyc.shiro;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yyc.dao.ISysPermissionMapper;
import com.yyc.dao.ISysRoleMapper;
import com.yyc.entity.UserInfo;
import com.yyc.service.UserService;
import com.yyc.utils.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class MyShiroRealm extends AuthorizingRealm {
	
	private final static Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);
	
    @Autowired
    UserService userService;
    @Autowired
    ISysRoleMapper sysRoleMapper;
    @Autowired
    ISysPermissionMapper sysPermissionMapper;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        logger.info("开始权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        logger.info("正在鉴权的用户信息：{}",principal.toString());
        UserInfo userInfo  = (UserInfo)principal.getPrimaryPrincipal();
        String username = userInfo.getUsername();
        sysRoleMapper.findRoleByUsername(username).stream().forEach(
                sysRole -> {
                    logger.info("正在鉴权的用户角色ID：{}",sysRole.getId());
                    authorizationInfo.addRole(sysRole.getRole());
                    sysPermissionMapper.findPermissionByRoleId(sysRole.getId()).stream().forEach(
                            sysPermission -> {
                                authorizationInfo.addStringPermission(sysPermission.getPermission());
                            }
                    );
                }
        );

        logger.info("当前登录用户：{},具有的角色:{},具有的权限：{}",userInfo.getUsername(),authorizationInfo.getRoles(),authorizationInfo.getStringPermissions());
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("开始验证身份-->method:doGetAuthenticationInfo");
    	// 将token转换成UsernamePasswordToken
//    	UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        // 从转换后的token中获取登录名
//        String username = upToken.getUsername();
        // 转换为自定义Token
        MyAuthenticationToken mat = (MyAuthenticationToken) token;
        String username = (String) mat.getPrincipal();
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserInfo userInfo = userService.findByUsername(username);
        logger.info("正在验证身份的用户信息：{}",userInfo);
        if(userInfo == null){
            // 用户不存在异常
            throw new UnknownAccountException();
        }else if(userInfo.getState() == 0) {
            // 账户锁定异常
            throw  new LockedAccountException();
        }

        
        ByteSource salt = ByteSource.Util.bytes(userInfo.getCredentialsSalt());
        
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                // 用户信息
                userInfo,
                // 密码
                userInfo.getPassword(),
                // 盐值salt=username+salt
                salt,
                // realm name
                getName()
        );
        return authenticationInfo;
    }

    /**
     * 覆写这个方法以支持自定义的AuthenticationToken
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && token instanceof MyAuthenticationToken;
    }
}
