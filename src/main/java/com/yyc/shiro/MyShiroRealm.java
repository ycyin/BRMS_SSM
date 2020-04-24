package com.yyc.shiro;

import com.yyc.dao.ISysPermissionMapper;
import com.yyc.dao.ISysRoleMapper;
import com.yyc.entity.UserInfo;
import com.yyc.service.UserService;
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
        UserInfo userInfo  = (UserInfo)principal.getPrimaryPrincipal();
        logger.info("用户名--->"+userInfo.getUsername());
        sysRoleMapper.findRoleByUsername(userInfo.getUsername()).stream().forEach(
                sysRole -> {
                    logger.info("角色ID--->"+sysRole.getId());
                    authorizationInfo.addRole(sysRole.getRole());
                    sysPermissionMapper.findPermissionByRoleId(sysRole.getId()).stream().forEach(
                            sysPermission -> {
                                authorizationInfo.addStringPermission(sysPermission.getPermission());
                            }
                    );
                }
        );

        logger.info("当前登录用户" + userInfo.getUsername() + "具有的角色:" + authorizationInfo.getRoles());
        logger.info("当前登录用户" + userInfo.getUsername() + "具有的权限：" + authorizationInfo.getStringPermissions());
        
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("开始验证身份-->method:doGetAuthenticationInfo");
    	//获取用户的输入的账号.
//        String username = (String)token.getPrincipal();
//        System.out.println(token.getCredentials());
    	
    	// 将token转换成UsernamePasswordToken
    	UsernamePasswordToken upToken = (UsernamePasswordToken) token;
    	// 从转换后的token中获取登录名
    	String username = upToken.getUsername();
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserInfo userInfo = userService.findByUsername(username);
        logger.info("----->>userInfo="+userInfo);
        if(userInfo == null){
            throw new UnknownAccountException();// 用户不存在
        }else if(userInfo.getState() == 0) {
            throw  new LockedAccountException();// 账户锁定
        }

        
        ByteSource salt = ByteSource.Util.bytes(userInfo.getCredentialsSalt());
        
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户
                userInfo.getPassword(), //密码
                salt,//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }
}
