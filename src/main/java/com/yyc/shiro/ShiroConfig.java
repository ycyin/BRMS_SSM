//package com.yyc.shiro;
//
//import java.io.IOException;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//import javax.servlet.Filter;
//
//import com.yyc.dao.ISysPermissionMapper;
//import com.yyc.entity.SysPermission;
//import com.yyc.filter.ShiroLoginFilter;
//import com.yyc.filter.ShiroPermsFilter;
//
//import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
//import org.apache.shiro.cache.ehcache.EhCacheManager;
//import org.apache.shiro.io.ResourceUtils;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.CookieRememberMeManager;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.web.servlet.SimpleCookie;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.util.StringUtils;
//import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
//
//import net.sf.ehcache.CacheManager;
//
////@Configuration
//public class ShiroConfig {
//
//	private static final Logger log = LoggerFactory.getLogger(ShiroConfig.class);
//
//
//	@Autowired
//	private ISysPermissionMapper sysPermissionMapper;
//
//    //因为我们的密码是加过密的，所以，如果要Shiro验证用户身份的话，需要告诉它我们用的是md5加密的，并且是加密了两次。同时我们在自己的Realm中也通过SimpleAuthenticationInfo返回了加密时使用的盐。这样Shiro就能顺利的解密密码并验证用户名和密码是否正确了。
//    @Bean(name = "hashedCredentialsMatcher")
//    public HashedCredentialsMatcher hashedCredentialsMatcher() {
//    	log.info("进入1---------->>>hashedCredentialsMatcher()方法");
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
//        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
//        return hashedCredentialsMatcher;
//    }
//
//    /**
//     *
//     * @Title: ehCacheManager
//     * @Description: TODO(缓存管理器)
//     * @param: @return
//     * @return: EhCacheManager
//     * @throws
//     */
//    @Bean(name = "cacheManager")
//    public CacheManager cacheManager() {
//    	log.info("进入2---------->>>cacheManager()方法");
//    	//"myEhcache"是我创建的ehcache-shiro.xml中定义的ehcache名称
//    	CacheManager cacheManager = CacheManager.getCacheManager("myEhcache");
//    	if(cacheManager == null){
//            try {
//                cacheManager = CacheManager.create(ResourceUtils.getInputStreamForPath("classpath:ehcache-shiro.xml"));
//            } catch (IOException e) {
//                throw new RuntimeException("initialize cacheManager failed");
//            }
//        }
//    	return cacheManager;
//    }
//
//    @Bean(name = "shiroCacheManager")
//    public EhCacheManager ehCacheManager(
//    		@Qualifier("cacheManager") CacheManager cacheManager) {
//    	log.info("进入3---------->>>ehCacheManager()方法");
//		/*//使用这种方法直接返回会由Spring boot热部署导致CacheManager重名
//    	EhCacheManager ehCacheManager = new EhCacheManager();
//    	ehCacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
//        return ehCacheManager;
//        */
//    	EhCacheManager bean=new EhCacheManager();
//        bean.setCacheManager(cacheManager);
//        return bean;
//    }
//
//
//
//    /**
//     *
//     * @Title: myShiroRealm
//     * @Description: TODO(MyShiroRealm)
//     * @param: @param matcher
//     * @param: @param ehCacheManager
//     * @param: @return
//     * @return: MyShiroRealm
//     * @throws
//     */
//    @Bean(name = "authRealm")
//    public MyShiroRealm myShiroRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher,
//			@Qualifier("shiroCacheManager") EhCacheManager ehCacheManager) {
//    	log.info("进入4---------->>>myShiroRealm()方法");
//        MyShiroRealm myShiroRealm = new MyShiroRealm();
//        // 设置解密规则(密码凭证匹配器)
//        myShiroRealm.setCredentialsMatcher(matcher); //// myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
//        // 设置缓存管理器
//        myShiroRealm.setCacheManager(ehCacheManager);
//        return myShiroRealm;
//    }
//
//
//    /**
//     *
//     * @Title: rememberMeCookie
//     * @Description: TODO(cookie对象)
//     * @param: @return
//     * @return: SimpleCookie
//     * @throws
//     */
//    @Bean(name = "rememberMeCookie")
//    public SimpleCookie rememberMeCookie() {
//    	log.info("进入5---------->>>rememberMeCookie()方法");
//        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
//        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
//
//        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
//        simpleCookie.setMaxAge(259200);
//        return simpleCookie;
//    }
//
//    /**
//     *
//     * @Title: cookieRememberMeManager
//     * @Description: TODO(记住我管理器 cookie管理对象)
//     * @param: @param simpleCookie
//     * @param: @return
//     * @return: CookieRememberMeManager
//     * @throws
//     */
//    @Bean(name = "cookieRememberMeManager")
//    public CookieRememberMeManager cookieRememberMeManager(@Qualifier("rememberMeCookie") SimpleCookie simpleCookie) {
//    	log.info("进入6---------->>>cookieRememberMeManager()方法");
//        CookieRememberMeManager manager = new CookieRememberMeManager();
//        manager.setCookie(simpleCookie);
//        return manager;
//    }
//
//    //SecurityManager 是 Shiro 架构的核心，通过它来链接Realm和用户(文档中称之为Subject.)
//    @Bean(name = "securityManager")
//    public SecurityManager securityManager(@Qualifier("authRealm") MyShiroRealm authRealm,
//			@Qualifier("cookieRememberMeManager") CookieRememberMeManager cookieRememberMeManager) {
//    	log.info("进入7---------->>>securityManager()方法");
//    	DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRememberMeManager(cookieRememberMeManager); //注入rememberMeManager;
//        securityManager.setRealm(authRealm); //将Realm注入到SecurityManager中。
//        return securityManager;
//    }
//
//
//    /**
//     *
//     * @Title: authorizationAttributeSourceAdvisor
//     * @Description: TODO(开启shiro aop注解支持.
//             * 使用代理方式;所以需要开启代码支持; Controller才能使用@RequiresPermissions)
//     * @param: @param securityManager
//     * @param: @return
//     * @return: AuthorizationAttributeSourceAdvisor
//     * @throws
//     */
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager){
//    	log.info("进入8---------->>>authorizationAttributeSourceAdvisor()方法");
//    	AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//        return authorizationAttributeSourceAdvisor;
//    }
//
//
//    @Bean
//    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager,
//    		@Qualifier("shiroLoginFilter") ShiroLoginFilter shiroLoginFilter,
//    		@Qualifier("shiroPermsFilter") ShiroPermsFilter shiroPermsFilter) {
//    	log.info("进入9---------->>>shiroFilterFactoryBean()方法");
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//
//        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//
//        // 从数据库获取权限信息,数据库中的url必须以"/"开头
//        // 需要把url中包含“/*”的放到后面再put(本项目的处理方式是用sql查询时排序)
//        List<SysPermission> allPermission = sysPermissionMapper.findAllPermission();
//        allPermission.forEach(sysPer -> {
//        	if (!StringUtils.isEmpty(sysPer.getUrl())) {// url为空 ， 不是路径权限
//                filterChainDefinitionMap.put(sysPer.getUrl(), "authc,perms[" + sysPer.getPermission() + "]");
//            }
//        });
//
//        // shiro默认过滤器
//        filterChainDefinitionMap.put("/logout", "logout");
//        filterChainDefinitionMap.put("/index", "user");//配置记住我或认证通过可以访问的地址
//        filterChainDefinitionMap.put("/", "user");
//        filterChainDefinitionMap.put("/favicon.ico", "anon");
//        filterChainDefinitionMap.put("/login", "anon");
//
////        filterChainDefinitionMap.put("/nologin", "anon");
//        filterChainDefinitionMap.put("/checkLogin", "anon");
////        filterChainDefinitionMap.put("/unauthorized", "anon");
//        // 未登录页面 ，如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
//        shiroFilterFactoryBean.setLoginUrl("/nologin");
//        //无权限界面
////		shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
//		// 登录成功后要跳转的链接
//		// shiroFilterFactoryBean.setSuccessUrl("/index");
//        filterChainDefinitionMap.put("/**", "authc");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        Map<String, Filter> filtersMap=new LinkedHashMap<>();
//        filtersMap. put("shirologinFilter", shiroLoginFilter);
//        filtersMap. put("perms", shiroPermsFilter);
////        filtersMap. put("roles", new ShiroRolesFilter());
//        shiroFilterFactoryBean.setFilters(filtersMap);
//
//        return shiroFilterFactoryBean;
//    }
//
//
//    @Bean
//    public SimpleMappingExceptionResolver resolver() {
//    	log.info("进入10---------->>>resolver()方法");
//        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
//        Properties properties = new Properties();
//        properties.setProperty("org.apache.shiro.authz.UnauthorizedException", "/403");
//        resolver.setExceptionMappings(properties);
//        return resolver;
//    }
//
//    @Bean(name = "shiroLoginFilter")
//    public ShiroLoginFilter shiroLoginFilter(){
//    	log.info("进入11---------->>>shiroLoginFilter()方法");
//        return new ShiroLoginFilter();
//    }
//
//    @Bean(name = "shiroPermsFilter")
//    public ShiroPermsFilter shiroPermsFilter(){
//    	log.info("进入12---------->>>shiroPermsFilter()方法");
//        return new ShiroPermsFilter();
//    }
//
//
//}