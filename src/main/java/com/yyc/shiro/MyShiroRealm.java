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


/**
 * 自定义realm，实现登录认证和鉴权
 * @author yyc
 */
public class MyShiroRealm extends AuthorizingRealm {
	
	private final static Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);
	
    @Autowired
    UserService userService;
    @Autowired
    ISysRoleMapper sysRoleMapper;
    @Autowired
    ISysPermissionMapper sysPermissionMapper;

    /**
     * 权限鉴权：验证是否有权限
     * 触发条件：1.当遇到使用@RequiresPermissions、@RequiresRoles等注解时
     *         2.当在ShiroAuthcFilter过滤器中调用executeLogin(request, response)时
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        logger.info("开始权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        logger.info("正在鉴权的用户信息：{}",principal.toString());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo  = (UserInfo)principal.getPrimaryPrincipal();
        String username = userInfo.getUsername();
        sysRoleMapper.findRoleByUsername(username).forEach(
                sysRole -> {
                    authorizationInfo.addRole(sysRole.getRole());
                    sysPermissionMapper.findPermissionByRoleId(sysRole.getId()).forEach(
                            sysPermission -> authorizationInfo.addStringPermission(sysPermission.getPermission())
                    );
                }
        );

        logger.info("鉴权完毕！当前登录用户：{},具有的角色:{},具有的权限：{}",userInfo.getUsername(),authorizationInfo.getRoles(),authorizationInfo.getStringPermissions());
        return authorizationInfo;
    }

    /**
     * 登录认证：验证用户是否登录
     * 触发条件：当调用SecurityUtils.getSubject().login(token)时
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("开始验证身份-->method:doGetAuthenticationInfo");
        // 转换为自定义MyAuthenticationToken对象
        MyAuthenticationToken mat = (MyAuthenticationToken) token;
        // 从自定义的Token对象中获取用户名
        String username = (String) mat.getPrincipal();
        //通过username从数据库中查找 User对象
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

        return new SimpleAuthenticationInfo(
                // 用户信息
                userInfo,
                // 密码
                userInfo.getPassword(),
                // 盐值salt=username+salt
                salt,
                // realm name
                getName()
        );
    }

    /**
     * 覆写这个方法以支持自定义的AuthenticationToken
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof MyAuthenticationToken;
    }
}
