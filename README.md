# BRMS-SSM后端

## 使用技术
Spring+SpringMVC+MyBatis+Shiro+MAVEN+PageHelper

## 开发环境

系统：Windows 10专业版

IDE：IntelliJ IDEA 2019.3 x64

数据库：MySQL 5.7

## 其它(笔记)
- `@Autowired`、`@Resource`注解的区别
1. @Autowired与@Resource都可以用来装配bean. 都可以写在字段上,或写在setter方法上。 
2. @Autowired默认按类型装配（这个注解是属业spring的），默认情况下必须要求依赖对象必须存在，
如果要允许null 值，可以设置它的required属性为false，如：@Autowired(required=false) ，
如果我们想使用名称装配可以结合@Qualifier注解进行使用，如下： 
> @Autowired() @Qualifier("baseDao")     
> private BaseDao baseDao;
    
3. @Resource（这个注解属于J2EE的），默认按照名称进行装配，名称可以通过name属性进行指定， 
如果没有指定name属性，当注解写在字段上时，默认取字段名进行按照名称查找，
如果注解写在setter方法上默认取属性名进行装配。 当找不到与名称匹配的bean时才按照类型进行装配。
但是需要注意的是，如果name属性一旦指定，就只会按照名称进行装配。

