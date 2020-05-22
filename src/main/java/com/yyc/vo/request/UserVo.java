package com.yyc.vo.request;

/**
 * @description: 用户信息
 * @author: yyc
 **/
public class UserVo {
     /** 用户ID */
     private Integer id;
     /** 登录名 */
     private String username;
     /** 用户名 */
     private String nickname;
     /** 密码 */
     private String password;
     /** 角色 */
     private Integer role;
     /** 用户状态 */
     private Integer state;

     public UserVo() {
     }

     public UserVo(String username, String nickname, int role, int state) {
          this.username = username;
          this.nickname = nickname;
          this.role = role;
          this.state = state;
     }


     /**
      * 获取 用户ID
      *
      * @return id 用户ID
      */
     public Integer getId() {
          return this.id;
     }

     /**
      * 设置 用户ID
      *
      * @param id 用户ID
      */
     public void setId(Integer id) {
          this.id = id;
     }

     /**
      * 获取 登录名
      *
      * @return username 登录名
      */
     public String getUsername() {
          return this.username;
     }

     /**
      * 设置 登录名
      *
      * @param username 登录名
      */
     public void setUsername(String username) {
          this.username = username;
     }

     /**
      * 获取 用户名
      *
      * @return nickname 用户名
      */
     public String getNickname() {
          return this.nickname;
     }

     /**
      * 设置 用户名
      *
      * @param nickname 用户名
      */
     public void setNickname(String nickname) {
          this.nickname = nickname;
     }

     /**
      * 获取 密码
      *
      * @return password 密码
      */
     public String getPassword() {
          return this.password;
     }

     /**
      * 设置 密码
      *
      * @param password 密码
      */
     public void setPassword(String password) {
          this.password = password;
     }

     /**
      * 获取 角色
      *
      * @return role 角色
      */
     public Integer getRole() {
          return this.role;
     }

     /**
      * 设置 角色
      *
      * @param role 角色
      */
     public void setRole(Integer role) {
          this.role = role;
     }

     /**
      * 获取 用户状态
      *
      * @return state 用户状态
      */
     public Integer getState() {
          return this.state;
     }

     /**
      * 设置 用户状态
      *
      * @param state 用户状态
      */
     public void setState(Integer state) {
          this.state = state;
     }
}
